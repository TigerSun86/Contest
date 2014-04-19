import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;

/**
 * FileName: BadGrass3160SpaceLimited.java
 * @Description: To many recursion, space limited.
 * 
 * @author Xunhu(Tiger) Sun
 *         email: TigerSun86@gmail.com
 * @date Apr 19, 2014 2:36:00 PM
 */
public class BadGrass3160SpaceLimited {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        final int r = s.nextInt();
        final int c = s.nextInt();
        s.nextLine();
        final int[][] map = new int[r][c];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = s.nextInt();
            }
            s.nextLine();
        }

        int count = 0;
        while (true) {
            final Point p = findNoZero(map);
            if (p == null) {
                break;
            }
            count++;

            map[p.x][p.y] = 0; // visit this spot.

            for (Point d : DIR) {
                fillIt(map, p, d);
            }

        }
        System.out.println(count);
        s.close();
    }

    private static Point findNoZero (int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private static final HashSet<Point> DIR = new HashSet<Point>();
    static {
        DIR.add(new Point(-1, 0)); // up
        DIR.add(new Point(1, 0)); // down
        DIR.add(new Point(0, -1)); // left
        DIR.add(new Point(0, 1)); // right
        DIR.add(new Point(-1, 1)); // rightup
        DIR.add(new Point(1, 1)); // rightdown
        DIR.add(new Point(1, -1)); // leftdown
        DIR.add(new Point(-1, -1)); // leftup
    }

    private static void fillIt (int[][] map, Point p, Point d) {
        final Point np = new Point(p.x + d.x, p.y + d.y);
        if (np.x < 0 || np.x >= map.length || np.y < 0 || np.y >= map[0].length) {
            return;
        }
        if (map[np.x][np.y] == 0) {
            return;
        }
        map[np.x][np.y] = 0; // visit this spot.

        for (Point d2 : DIR) {
            fillIt(map, np, d2);
        }
    }
}
