package gcjpractice;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * FileName: LaserMaze.java
 * @Description: Facebook Hacker Cup 2015 Qualification Round
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 10, 2015 5:10:45 PM
 */
public class LaserMaze {
    final Scanner sc = new Scanner(System.in);
    private static final char E = '.';
    private static final char W = '#';
    private static final char S = 'S';
    private static final char G = 'G';
    private static final List<Character> TURRETS = new ArrayList<Character>();
    static {
        TURRETS.add('^'); // up
        TURRETS.add('>'); // right
        TURRETS.add('v'); // down
        TURRETS.add('<'); // left
    }

    private static final List<Point> DIRECTIONS = new ArrayList<Point>();
    static {
        DIRECTIONS.add(new Point(-1, 0));// up
        DIRECTIONS.add(new Point(0, 1)); // right
        DIRECTIONS.add(new Point(1, 0));// down
        DIRECTIONS.add(new Point(0, -1)); // left
    }

    private static class State {
        int t;
        int x;
        int y;

        public State(int t, int x, int y) {
            super();
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }

    void read () {
        height = sc.nextInt();
        width = sc.nextInt();
        sc.nextLine();

        turrets = new ArrayList<Point>();
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            map[i] = sc.nextLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                final char cur = map[i][j];
                if (cur == S) {
                    s = new Point(i, j);
                } else if (cur == G) {
                    g = new Point(i, j);
                } else if (TURRETS.contains(new Character(cur))) {
                    turrets.add(new Point(i, j));
                }
            }
        }
    }

    int height;
    int width;
    char[][] map;
    Point s;
    Point g;
    List<Point> turrets;
    List<HashSet<Point>> dangerousZones;
    boolean[][][] visited;

    void solve () {
        initDangerousZones();

        visited = new boolean[4][map.length][map[0].length];
        for (int i = 0; i < 4; i++) {
            visited[i] = new boolean[map.length][map[0].length];
            for (int j = 0; j < map.length; j++) {
                visited[i][j] = new boolean[map[0].length];
            }
        }

        final LinkedList<State> que = new LinkedList<State>();
        visited[0][s.x][s.y] = true;
        que.add(new State(0, s.x, s.y));
        int steps = -1;
        while (steps == -1 && !que.isEmpty()) {
            final State cur = que.removeFirst();
            if (cur.x == g.x && cur.y == g.y) {
                steps = cur.t;
            } else {
                final List<State> children = getChildren(cur);
                for (State child : children) {
                    if (!visited[child.t % 4][child.x][child.y]
                            && !isDead(child)) {
                        visited[child.t % 4][child.x][child.y] = true;
                        que.add(child);

                    }
                }
            }
        }
        if (steps != -1) {
            System.out.println(steps);
        } else {
            System.out.println("impossible");
        }
    }

    private void initDangerousZones () {
        dangerousZones = new ArrayList<HashSet<Point>>();
        for (int t = 0; t < 4; t++) {
            dangerousZones.add(new HashSet<Point>());
        }

        for (Point initPos : turrets) {
            final char ch = map[initPos.x][initPos.y];
            final int initDirect = TURRETS.indexOf(new Character(ch));
            for (int t = 0; t < 4; t++) {
                final Point direct = DIRECTIONS.get((initDirect + t) % 4);
                Point cur = new Point(initPos.x, initPos.y);
                boolean noWall = true;
                while (noWall) {
                    cur.x += direct.x;
                    cur.y += direct.y;
                    if (isEmptySpot(cur.x, cur.y)) {
                        // Add a dangerous spot at time t.
                        dangerousZones.get(t).add(new Point(cur.x, cur.y));
                    } else {
                        noWall = false;
                    }
                } // while (noWall) {
            } // for (int i = 0; i < 4; i++) {
        } // for (Point initPos : turrets) {

        for (int t = 0; t < 4; t++) {
            // System.out.println(dangerousZones.get(t).toString());
        }
    }

    private boolean isDead (State c) {
        final int t = c.t % 4;
        final Point pos = new Point(c.x, c.y);
        return dangerousZones.get(t).contains(pos);
    }

    private List<State> getChildren (State cur) {
        final List<State> children = new ArrayList<State>();
        for (Point action : DIRECTIONS) {
            final int x = cur.x + action.x;
            final int y = cur.y + action.y;
            if (isEmptySpot(x, y)) {
                children.add(new State(cur.t + 1, x, y));
            }
        }
        return children;
    }

    private boolean isEmptySpot (int x, int y) {
        if (x >= 0 && x < height && y >= 0 && y < width) {
            final char spot = map[x][y];
            if (spot == E || spot == S || spot == G) {
                return true;
            }
        }
        return false;
    }

    void run () {
        final int cn = sc.nextInt();
        sc.nextLine();
        for (int ci = 1; ci <= cn; ci++) {
            read();
            System.out.printf("Case #%d: ", ci);
            solve();
        }
    }

    public static void main (String[] args) {
        new LaserMaze().run();
    }
}
