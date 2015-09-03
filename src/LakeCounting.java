import java.awt.Point;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName: LakeCounting.java
 * @Description:
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 31, 2015 2:19:40 PM
 */
public class LakeCounting {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = new int[m];
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == 'W') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = -1;
                }
            }
        }
    }

    int n;
    int m;
    int[][] map;
    private static final Point[] direct = new Point[] { new Point(-1, -1),
            new Point(-1, 0), new Point(-1, 1), new Point(0, 1),
            new Point(1, 1), new Point(1, 0), new Point(1, -1),
            new Point(0, -1) };

    void solve () {
        int count = 0;
        while (true) {
            int[] nextEmpty = next();
            if (nextEmpty == null) {
                break;
            }
            count++;
            LinkedList<Point> que = new LinkedList<Point>();
            map[nextEmpty[0]][nextEmpty[1]] = count;
            que.add(new Point(nextEmpty[0], nextEmpty[1]));
            while (!que.isEmpty()) {
                Point cur = que.remove();
                for (Point d : direct) {
                    Point np = new Point(cur.x + d.x, cur.y + d.y);
                    if (np.x >= 0 && np.x < n && np.y >= 0 && np.y < m) {
                        if (map[np.x][np.y] == 0) {
                            map[np.x][np.y] = count;
                            que.add(np);
                        }
                    }
                }
            }
        }
        System.out.println(count);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
    }

    private int[] next () {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    void run () {
        read();
        solve();

    }

    public static void main (String[] args) {
        new LakeCounting().run();
    }
}
