import java.awt.Point;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName: MagicCowShoes.java
 * @Description:
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 31, 2015 2:51:34 PM
 */
public class MagicCowShoes {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        b = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
        sc.nextLine();
        c = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
        sc.nextLine();

        map1 = new int[n][m];
        for (int i = 0; i < n; i++) {
            map1[i] = new int[m];
            for (int j = 0; j < m; j++) {
                map1[i][j] = Integer.MAX_VALUE;
            }
        }
        map2 = new int[n][m];
        for (int i = 0; i < n; i++) {
            map2[i] = new int[m];
            for (int j = 0; j < m; j++) {
                map2[i][j] = Integer.MAX_VALUE;
            }
        }

    }

    int n;
    int m;
    Point b;
    Point c;
    int[][] map1;
    int[][] map2;
    private static final Point[] direct1 = new Point[] { new Point(-2, 1),
            new Point(-1, 2), new Point(1, 2), new Point(2, 1),
            new Point(2, -1), new Point(1, -2), new Point(-1, -2),
            new Point(-2, -1) };
    private static final Point[] direct2 = new Point[] { new Point(-2, 0),
            new Point(0, 2), new Point(2, 0), new Point(0, -2) };

    private static class State {
        Point p;
        int depth;

        public State(Point p, int depth) {
            this.p = p;
            this.depth = depth;
        }
    }

    void solve () {
        LinkedList<State> que = new LinkedList<State>();
        map2[b.x][b.y] = 0;
        que.add(new State(b, 0));
        while (!que.isEmpty()) {
            State cur = que.removeFirst();
            if (cur.p.x == c.x && cur.p.y == c.y) {
                System.out.println(cur.depth);
                break;
            } else {
                Point[] dir;
                int[][] map;
                if ((cur.depth + 1) % 2 == 1) { // odd move
                    dir = direct1;
                    map = map1;
                } else {
                    dir = direct2;
                    map = map2;
                }
                for (Point p : dir) {
                    Point np = new Point(p.x + cur.p.x, p.y + cur.p.y);
                    if (np.x >= 0 && np.y >= 0 && np.x < n && np.y < m) {
                        if (map[np.x][np.y] > cur.depth + 1) {
                            map[np.x][np.y] = cur.depth + 1;
                            que.add(new State(np, cur.depth + 1));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map2[i][j] + " ");
            }
            System.out.println();
        }
    }

    void run () {
        read();
        solve();

    }

    public static void main (String[] args) {
        new MagicCowShoes().run();
    }
}
