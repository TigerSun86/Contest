import java.util.Scanner;

/**
 * FileName: CakeCutting.java
 * @Description: Facebook Hacker Cup 2013 Round 2
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 22, 2015 4:17:36 PM
 */
public class CakeCutting {
    Scanner sc;

    void read () {
        long n = sc.nextInt();
        l = 0;
        v = 0;
        for (long i = 0; i < n; i++) {
            long a = sc.nextInt();
            l += a + 1;
            v += a;
        }
        sc.nextLine();
    }

    long l;
    long v;

    void solve () {
        long p = getP();
        p -= v * 2;
        System.out.println(p);
    }

    private long getP () {
        return (((l + 1) * l) / 2) + 1;
    }

    void run () {
        sc = new Scanner(System.in);
        final int cn = Integer.valueOf(sc.nextLine());
        for (int ci = 1; ci <= cn; ci++) {
            read();
            System.out.printf("Case #%d: ", ci);
            solve();
        }
        sc.close();
    }

    public static void main (String[] args) {
        new CakeCutting().run();
    }
}
