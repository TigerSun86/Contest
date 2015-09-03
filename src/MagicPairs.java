import java.util.Arrays;
import java.util.Scanner;

/**
 * FileName: MagicPairs.java
 * @Description:
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 20, 2015 6:44:12 PM
 */
public class MagicPairs {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        int x1 = sc.nextInt();
        int a1 = sc.nextInt();
        int b1 = sc.nextInt();
        int c1 = sc.nextInt();
        int r1 = sc.nextInt();
        sc.nextLine();
        int x2 = sc.nextInt();
        int a2 = sc.nextInt();
        int b2 = sc.nextInt();
        int c2 = sc.nextInt();
        int r2 = sc.nextInt();

        board1 = new int[n];
        board2 = new int[m];
        board1[0] = x1;
        board2[0] = x2;
        for (int i = 1; i < Math.max(n, m); i++) {
            if (i < n) {
                board1[i] =
                        (a1 * board1[(i - 1) % n] + b1 * board2[(i - 1) % m] + c1)
                                % r1;
            }
            if (i < m) {
                board2[i] =
                        (a2 * board1[(i - 1) % n] + b2 * board2[(i - 1) % m] + c2)
                                % r2;
            }
        }

        //System.out.println("board1 = " + Arrays.toString(board1));
       // System.out.println("board2 = " + Arrays.toString(board2));
    }

    int n;
    int m;
    int[] board1;
    int[] board2;

    void solve () {

        int[] bits1 = new int[n];
        int[] bits2 = new int[m];

        bits1[0] = 1 << board1[0];
        for (int i = 1; i < board1.length; i++) {
            bits1[i] = bits1[i - 1] | (1 << board1[i]);
        }
        bits2[0] = 1 << board2[0];
        for (int i = 1; i < board2.length; i++) {
            bits2[i] = bits2[i - 1] | (1 << board2[i]);
        }
        System.out.println();
        System.out.println("bits1 = " + Arrays.toString(bits1));
        System.out.println("bits2 = " + Arrays.toString(bits2));

        long count = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (bits1[i] != bits2[j]) {
                if (isInclude(bits1[i], bits2[j])) {
                    j = nextDiffIdx(bits2, j);
                } else if (isInclude(bits2[j], bits1[i])) {
                    i = nextDiffIdx(bits1, i);
                } else {
                    i = nextDiffIdx(bits1, i);
                    j = nextDiffIdx(bits2, j);
                }
            } else {
                int nextI = nextDiffIdx(bits1, i);
                int nextJ = nextDiffIdx(bits2, j);
                long b1count = nextI -1 + 1 - i;
                long b2count = nextJ -1 + 1 - j;
                count += b1count* b2count;
                i = nextI;
                j = nextJ;
            }
        }
        System.out.println(count);
    }

    private int nextDiffIdx (int[] bits, int i) {
        if (i >= bits.length - 1) {
            return bits.length;
        } else {
            int cur = bits[i];
            i++;
            while ((i < bits.length) && (cur == bits[i])) {
                i++;
            }
            return i;
        }
    }

    private boolean isInclude (int x1, int x2) {
        return x1 == (x1 | x2);
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
        new MagicPairs().run();
    }
}
