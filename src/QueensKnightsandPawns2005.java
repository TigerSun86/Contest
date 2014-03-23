import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;

public class QueensKnightsandPawns2005 {
    private static final int Q = 1;
    private static final int K = 2;
    private static final int P = 3;
    private static final int T = -1;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int count = 1;
        while (true) {

            final int rm = s.nextInt();
            if (rm == 0) {
                break;
            }
            final int cm = s.nextInt();
            final int[][] b = new int[rm][cm];
            for (int r = 0; r < rm; r++) {
                b[r] = new int[cm];
            }
            s.nextLine();
            final HashSet<Point> qs = new HashSet<Point>();
            final int qm = s.nextInt();
            for (int j = 0; j < qm; j++) {
                final int r = s.nextInt() - 1;
                final int c = s.nextInt() - 1;
                b[r][c] = Q;
                qs.add(new Point(r, c));
            }
            s.nextLine();
            final HashSet<Point> ks = new HashSet<Point>();
            final int km = s.nextInt();
            for (int j = 0; j < km; j++) {
                final int r = s.nextInt() - 1;
                final int c = s.nextInt() - 1;
                b[r][c] = K;
                ks.add(new Point(r, c));
            }
            s.nextLine();
            final int pm = s.nextInt();
            for (int j = 0; j < pm; j++) {
                final int r = s.nextInt() - 1;
                final int c = s.nextInt() - 1;
                b[r][c] = P;
            }
            s.nextLine();

            for (Point q : qs) {
                checkQ(b, q);
            }
            for (Point k : ks) {
                checkK(b, k);
            }

            int safe =0;
            for (int r = 0; r < rm; r++) {
                for (int c = 0; c < cm; c++) {
                    if (b[r][c] == 0) {
                        safe++;
                    }
                }
            }
            final String str = String.format("Board %d has %d safe squares.",count,safe);
            System.out.println(str);
            count++;
        }
        s.close();
    }
    private static final HashSet<Point> kDirect = new HashSet<Point>();
    static {
        kDirect.add(new Point(-2, 1));// up right 1
        kDirect.add(new Point(-1, 2));// up right 2
        kDirect.add(new Point(1, 2));// down right 1
        kDirect.add(new Point(2, 1));// down right 2
        kDirect.add(new Point(2, -1));// down left 1
        kDirect.add(new Point(1, -2));// down left 2
        kDirect.add(new Point(-1, -2));// up left 1
        kDirect.add(new Point(-2, -1));// up left 2
    }
    
    private static void checkK(int[][] b, Point k) {
        for (Point kd : kDirect) {
            checkKOne(b, k, kd);
        }
    }
    private static void checkKOne(int[][] b, Point k2, Point kd) {
        Point p = new Point(k2.x+ kd.x, k2.y+kd.y);
        if  (p.x< b.length &&p.x>=0&& p.y < b[0].length && p.y >=0){
            if (b[p.x][p.y] == 0){
                b[p.x][p.y] = T;
            }
        }
    }
    private static final HashSet<Point> qDirect = new HashSet<Point>();
    static {
        qDirect.add(new Point(-1, 0));// up
        qDirect.add(new Point(1, 0));// down
        qDirect.add(new Point(0, -1));// left
        qDirect.add(new Point(0, 1));// right
        qDirect.add(new Point(-1, 1));// up right
        qDirect.add(new Point(-1, -1));// up left
        qDirect.add(new Point(1, 1));// down right
        qDirect.add(new Point(1, -1));// down left
    }

    private static void checkQ(int[][] b, Point q) {
        for (Point qd : qDirect) {
            checkQOne(b, q, qd);
        }
    }

    private static void checkQOne(int[][] b, Point q2, Point qd) {
        Point p = new Point(q2.x+ qd.x, q2.y+qd.y);
        while (p.x< b.length &&p.x>=0&& p.y < b[0].length && p.y >=0){
            if (b[p.x][p.y] == 0){
                b[p.x][p.y] = T;
            } else if (b[p.x][p.y] != T) {
                break;
            }
            p = new Point(p.x+ qd.x, p.y+qd.y);
        }
    }

}
