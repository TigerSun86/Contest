package gcjpractice;

import java.util.HashSet;
import java.util.Scanner;

/**
 * FileName: ChargingChaos3.java
 * @Description: 2014 round1a a
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 5, 2014 12:50:04 AM
 */
public class ChargingChaos3 {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        l = sc.nextInt();
        sc.nextLine();

        a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong(2);
        }
        sc.nextLine();

        b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextLong(2);
        }
        sc.nextLine();
    }

    int n;
    int l;
    long[] a;
    long[] b;

    void solve () {
        HashSet<Long> bhs = new HashSet<Long>();
        for (long b1 : b) {
            bhs.add(b1);
        }
        int ans = l + 1;
        for (int i = 0; i < n; i++) {
            final long fs = a[0] ^ b[i];
            boolean good = true;
            for (int j = 0; j < n; j++) {
                final long newa = a[j] ^ fs;
                if (!bhs.contains(newa)) {
                    good = false;
                    break;
                }
            }
            if (good) {
                ans = Math.min(ans, Long.bitCount(fs));
            }
        }

        if (ans != l + 1) {
            System.out.println(ans);
        } else {
            System.out.println("NOT POSSIBLE");
        }
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
        new ChargingChaos3().run();
    }
}
