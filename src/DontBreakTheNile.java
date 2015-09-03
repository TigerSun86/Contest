import java.awt.Point;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName: DontBreakTheNile.java
 * @Description:
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 31, 2015 5:49:29 PM
 */
public class DontBreakTheNile {
    final Scanner sc = new Scanner(System.in);

    void read () {
        w = sc.nextInt();
        h = sc.nextInt();
        b = sc.nextInt();
        sc.nextLine();
        map = new int[w][h];
        for (int i = 0; i < w; i++) {
            map[i] = new int[h];
        }
        for (int i = 0; i < b; i++) {
            int x0 = sc.nextInt();
            int y0 = sc.nextInt();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            sc.nextLine();
            for (int j = x0; j <= x1; j++) {
                for (int k = y0; k <= y1; k++) {
                    map[j][k] = 1;
                }
            }
        }
    }

    int w;
    int h;
    int b;
    int[][] map;
    // order, left 0, up 1, right 2,down 3
    private static final Point[] ds = new Point[] { new Point(-1, 0),
            new Point(0, 1), new Point(1, 0), new Point(0, -1) };

    private static class State {
        Point p;
        int in;

        public State(Point p, int in) {
            this.p = p;
            this.in = in;
        }
    }

    void solve () {
        int count = 0;
        while (true) {

//            for (int j = h - 1; j >= 0; j--) {
//                for (int i = 0; i < w; i++) {
//                    System.out.print(map[i][j] + "");
//                }
//                System.out.println();
//            }
//            System.out.println();
            int next = next();
            if (next == -1) {
                break;
            }
            Point init = new Point(next, 0);
            LinkedList<State> stack = new LinkedList<State>();

            stack.push(new State(init, 1));
            while (!stack.isEmpty()) {
                State cur = stack.poll();
                if (map[cur.p.x][cur.p.y] != 0) {
                    continue;
                }
                map[cur.p.x][cur.p.y] = count + 1;
                if (cur.p.y == h - 1) {
                    count++;
                    break;
                } else {
                    LinkedList<State> temp = new LinkedList<State>();
                    int in = left(cur.in);
                    for (int i = 0; i < 4; i++) {
                        Point d = ds[in];
                        Point np = new Point(d.x + cur.p.x, d.y + cur.p.y);
                        if (np.x >= 0 && np.y >= 0 && np.x < w && np.y < h) {
                            if (map[np.x][np.y] == 0) {
                                temp.add(new State(np, in));
                            }
                        }
                        in = right(in);
                    }
                    for (int i = temp.size() - 1; i >= 0; i--) {
                        stack.push(temp.get(i));
                    }
                }
            }
        }

        System.out.println(count);
    }

    private int left (int i) {
        return (i - 1) < 0 ? 3 : (i - 1);
    }

    private int right (int i) {
        return (i + 1) == 4 ? 0 : (i + 1);
    }

    private int next () {
        for (int i = 0; i < w; i++) {
            if (map[i][0] == 0) {
                return i;
            }
        }
        return -1;
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
        new DontBreakTheNile().run();
    }
}
