import java.io.IOException;
import java.util.Scanner;

/**
 * FileName:     RoboElection.java
 * @Description: Facebook Hacker Cup 2013 Round 2
 * 0.01 * 9207 == 92.07000000000001 not 92.07
 * 9207.0/100 == 92.07
 * never use double multiplication if you can use long multiplication instead
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 22, 2015 4:17:36 PM 
 */

public class RoboElection {
    Scanner sc;

    void read () throws NumberFormatException, IOException {
        n = sc.nextLong();
        k = sc.nextLong();
        p = sc.nextLong();
        sc.nextLine();
    }

    long n;
    long k;
    long p;

    void solve () throws IOException {
        if (p == 100) {
            if (k >= n) {
                System.out.println(1);
            } else {
                System.out.println((long) Math.ceil(((double) n) / k));
            }
            return;
        }

        long curtotal = n % k;
        if (curtotal == 0) {
            curtotal = Math.min(n, k);
        }
        long rounds = 1;
        long lastsaveround = 1;
        while (curtotal < n) {
            long a =
                    (long) Math.ceil(((double) p) * curtotal / (k * (100 - p)));
            if (curtotal + a * k <= n) {
                rounds += a;
                lastsaveround = rounds;
                curtotal += a * k;
            } else {
                a = (n - curtotal) / k;
                rounds += a;
                curtotal = n;
            }
        }
        System.out.println(rounds - lastsaveround + 1);
    }

    void run () throws NumberFormatException, IOException {
        sc = new Scanner(System.in);
        final int cn = Integer.valueOf(sc.nextLine());
        for (int ci = 1; ci <= cn; ci++) {
            read();
            System.out.printf("Case #%d: ", ci);
            solve();
        }
    }

    public static void main (String[] args) throws NumberFormatException,
            IOException {
        new RoboElection().run();
    }
}
