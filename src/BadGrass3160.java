import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName: BadGrass3160.java
 * @Description: Bottom up dynamic programming (or breadth first search) to save space.
 *               But still got several times time limited:
 *               1. LinkedList is much slower than HashSet in method contains.
 *               2. First visit the spot, then add it into frontier will be much faster,
 *                  because it even get rid of method contains.
 * 
 * @author Xunhu(Tiger) Sun
 *         email: TigerSun86@gmail.com
 * @date Apr 19, 2014 3:50:25 PM
 */
public class BadGrass3160 {
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
            visitIt(map, p);
        }
        System.out.println(count);
        s.close();
    }

    private static void visitIt (int[][] map, Point p) {
        // Use map as "visited"
        final LinkedList<Point> frontier = new LinkedList<Point>();
        frontier.add(p);

        while (!frontier.isEmpty()) {
            final Point np = frontier.removeFirst();
            expand(map, np, frontier);
        }
    }

    private static void
            expand (int[][] map, Point p, LinkedList<Point> frontier) {
        for (Point d : DIR) {
            final Point np = new Point(p.x + d.x, p.y + d.y);
            if (isValid(map, np)) {
                map[np.x][np.y] = 0; // Visit spot here.
                frontier.add(np);
            }
        }
    }

    private static boolean isValid (int[][] map, Point np) {
        if (np.x < 0 || np.x >= map.length || np.y < 0 || np.y >= map[0].length) {
            return false;
        }
        if (map[np.x][np.y] == 0) {
            return false;
        }
        return true;
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
}
