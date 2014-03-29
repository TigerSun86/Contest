// Wrong answer, heuristic function is not admissible.
import java.awt.Point;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DragonsCrullerAsiaRegionalContestAizu20131124 {
    private static class Node implements Comparable<Node> {
        int[][] map;
        int cost;
        int fcost;
        Point b;

        public static int heu (int[][] in, int[][] goal, int ch, int cv) {
            int hcost = 0;
            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[0].length; j++) {
                    if (in[i][j] == 0) continue;
                    Point t = find(goal, in[i][j]);
                    assert t != null;
                    int dv;
                    if (t.x == i) {
                        dv = 0;
                    } else {
                        dv = 1;
                    }
                    int dh;
                    if (t.y == j) {
                        dh = 0;
                    } else {
                        dh = 1;
                    }
                    if ((j == 0 && t.y == 2)||(j == 2 && t.y== 0)){
                        dv = 0;
                    }
                    
                    hcost += dh * ch + dv * cv;

                }
            }
            return hcost;
        }

        private static Point find (int[][] map, int num) {

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == num) {
                        return new Point(i, j);
                    }
                }
            }
            return null;
        }

        public Node(Node a) {
            map = new int[3][3];

            for (int i = 0; i < map.length; i++) {
                map[i] = new int[3];
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = a.map[i][j];
                }
            }
            b = new Point(a.b);
            cost = a.cost;
        }

        public Node(int[][] map2) {
            map = new int[3][3];

            for (int i = 0; i < map.length; i++) {
                map[i] = new int[3];
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = map2[i][j];
                    if (map[i][j] == 0) {
                        b = new Point(i, j);
                    }
                }
            }
            cost = 0;
        }

        @Override
        public boolean equals (Object a) {
            if (!(a instanceof Node)) {
                return false;
            }
            Node a2 = (Node) a;
            if (this.sameMap(a2.map)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode () {
            int h = 3;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    h = 7 * h + map[i][j];
                }
            }
            return h;
        }

        public boolean sameMap (int[][] map2) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] != map2[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public int compareTo (Node arg0) {
            // TODO Auto-generated method stub
            return fcost - arg0.fcost;
        }

        @Override
        public String toString () {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int ch = s.nextInt();
            if (ch == 0) {
                break;
            }
            int cv = s.nextInt();
            s.nextLine();
            int[][] init = new int[3][3];
            for (int i = 0; i < 3; i++) {
                init[i] = new int[3];
                for (int j = 0; j < 3; j++) {
                    init[i][j] = s.nextInt();
                }
                s.nextLine();
            }
            int[][] tar = new int[3][3];
            for (int i = 0; i < 3; i++) {
                tar[i] = new int[3];
                for (int j = 0; j < 3; j++) {
                    tar[i][j] = s.nextInt();
                }
                s.nextLine();
            }
            final PriorityQueue<Node> frontier = new PriorityQueue<Node>();
            final HashSet<Node> visited = new HashSet<Node>();
            Node in = new Node(init);
            int hcost = Node.heu(in.map, tar, ch, cv);
            //System.out.println(hcost);
            in.fcost = in.cost + hcost;
            frontier.add(in);
            while (!frontier.isEmpty()) {
                Node temp = frontier.remove();
                //System.out.println(temp + " " + visited.size());
                if (temp.sameMap(tar)) {
                    System.out.println(temp.cost);
                    break;
                }
                visited.add(temp);

                explore(temp, ch, cv, visited, frontier, tar);

            }

        }
        s.close();
    }

    private static final HashSet<Point> DIR = new HashSet<Point>();
    static {
        DIR.add(new Point(-1, 0));// up
        DIR.add(new Point(1, 0));// down
        DIR.add(new Point(0, -1));// left
        DIR.add(new Point(0, 1));// right
    }

    private static void explore (Node temp, int ch, int cv,
            HashSet<Node> visited, PriorityQueue<Node> frontier, int[][] goal) {
        for (Point p : DIR) {
            final Node child = newChild(temp, p, ch, cv, goal);
            if (!visited.contains(child)) {
                if (!frontier.contains(child)) {
                    frontier.add(child);
                } else {
                    Node other = null;
                    for (Node n : frontier) {
                        if (n.equals(child)) {
                            other = n;
                            break;
                        }
                    }
                    assert other != null;
                    if (other.cost > child.cost) {
                        frontier.remove(other);
                        frontier.add(child);
                    }
                }
            }
        }
    }

    private static Node newChild (Node temp, Point p, int ch, int cv,
            int[][] goal) {
        final Node c = new Node(temp);
        int newX = (c.b.x + p.x + 3) % 3;
        int newY = (c.b.y + p.y + 3) % 3;
        if(p.x == 0){ // h move
            if (c.b.y==0 && p.y == -1){// left
                newX = (c.b.x -1 + 3)%3;
            } else if(c.b.y == 2 && p.y == 1){ // right
                newX = (c.b.x +1)%3;
            } else {
                newX = c.b.x;
            }
        }
        Point nb = new Point(newX, newY);

        c.map[c.b.x][c.b.y] = c.map[nb.x][nb.y];
        c.map[nb.x][nb.y] = 0;
        c.b = nb;
        if (p.x == 0) { // h move
            c.cost += ch;
        } else {
            c.cost += cv;
        }
        int hcost = Node.heu(c.map, goal, ch, cv);
        c.fcost = c.cost + hcost;
        return c;
    }

}
