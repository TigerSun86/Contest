import java.awt.Point;
import java.util.Scanner;

public class TheGame1312ConectiveFiveWinner {
    private static final int PE = 0;
    private static final int PW = 1;
    private static final int PB = 2;
    private static final int MAX = 19;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            final int[][] b = new int[MAX][MAX];
            for (int row = 0; row < MAX; row++) {
                for (int col = 0; col < MAX; col++) {
                    b[row][col] = s.nextInt();
                }
                s.nextLine();
            }

            // Find.
            int winner = PE;
            int rowF = -1;
            int colF = -1;
            for (int row = 0; row < MAX; row++) {
                for (int col = 0; col < MAX; col++) {
                    if (b[row][col] != PE) {
                        // Check.
                        winner = check(b, row, col);
                        if (winner != PE) {
                            rowF = row + 1;
                            colF = col + 1;
                            break;
                        }
                    }
                }
                if (winner != PE) {
                    break;
                }
            }

            System.out.println(winner);
            if (winner != PE) {
                System.out.println(rowF + " " + colF);
            }
        }
        s.close();
    }

    private static final Point R = new Point(0, 1);
    private static final Point L = new Point(0, -1);
    private static final Point D = new Point(1, 0);
    private static final Point U = new Point(-1, 0);
    private static final Point RU = new Point(R.x + U.x, R.y + U.y);
    private static final Point RD = new Point(R.x + D.x, R.y + D.y);
    private static final Point LU = new Point(L.x + U.x, L.y + U.y);
    private static final Point LD = new Point(L.x + D.x, L.y + D.y);

    private static boolean isInbound(final Point p) {
        if (p.x >= 0 && p.x < MAX && p.y >= 0 && p.y < MAX) {
            return true;
        }
        return false;
    }

    private static int count(int[][] b, int cur, Point curP, Point nextP) {
        final Point pos = new Point(curP);
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (isInbound(pos)) {
                if (b[pos.x][pos.y] == cur) {
                    count++;
                } else {
                    break;
                }
                pos.move(pos.x + nextP.x, pos.y + nextP.y);
            }
        }
        return count;
    }

    private static int check(int[][] b, int r, int c) {
        final int cur = b[r][c];
        final Point curP = new Point(r, c);
        // Hor.
        // Exam one step back.
        boolean unchecked = true;
        Point back = new Point(curP.x + L.x, curP.y + L.y);
        if (isInbound(back)) {
            if (b[back.x][back.y] == cur) {
                unchecked = false;
            }
        }
        if (unchecked) {
            int cnt = count(b, cur, curP, R);
            if (cnt == 5) {
                return cur;
            }
        }

        // Ver.
        unchecked = true;
        back = new Point(curP.x + U.x, curP.y + U.y);
        if (isInbound(back)) {
            if (b[back.x][back.y] == cur) {
                unchecked = false;
            }
        }
        if (unchecked) {
            int cnt = count(b, cur, curP, D);
            if (cnt == 5) {
                return cur;
            }
        }
        
        // To right up.
        unchecked = true;
        back = new Point(curP.x + LD.x, curP.y + LD.y);
        if (isInbound(back)) {
            if (b[back.x][back.y] == cur) {
                unchecked = false;
            }
        }
        if (unchecked) {
            int cnt = count(b, cur, curP, RU);
            if (cnt == 5) {
                return cur;
            }
        }

        // To right down.
        unchecked = true;
        back = new Point(curP.x + LU.x, curP.y + LU.y);
        if (isInbound(back)) {
            if (b[back.x][back.y] == cur) {
                unchecked = false;
            }
        }
        if (unchecked) {
            int cnt = count(b, cur, curP, RD);
            if (cnt == 5) {
                return cur;
            }
        }
        
        return PE;
    }

}
