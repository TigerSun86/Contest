package gcjpractice;

import java.util.HashSet;
import java.util.Scanner;

/**
 * FileName:     ChargingChaos2.java
 * @Description: 2014 round1a a
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 5, 2014 12:50:04 AM 
 */
public class ChargingChaos2 {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        l = sc.nextInt();
        sc.nextLine();
        
        ol = new long[n];
        for (int i = 0; i < n; i++) {
            ol[i] = sc.nextLong(2);
        }
        sc.nextLine();
        
        dv = new long[n];
        for (int i = 0; i < n; i++) {
            dv[i] = sc.nextLong(2);
        }
        sc.nextLine();
    }
    
    int n;
    int l;
    long[] ol;
    long[] dv;
    
    Long best;
    HashSet<Long> dvhs;
    void solve () {
        final long[] fss = new long[n];
        
        for (int i = 0; i < n; i++){
            final long fs = ol[0] ^ dv[i];
            fss[i] = fs;
        }
        
        best = null;
        dvhs = new HashSet<Long>();
        for (long d: dv){
            dvhs.add(d);
        }
        for (long fs : fss){
            check(fs);
        }
        if (best != null){
            System.out.println(Long.bitCount(best));    
        } else {
            System.out.println("NOT POSSIBLE");
        }
    }

    private void check (long fs) {
        int count = 0;
        for (long o : ol){
            final long temp = o ^ fs;
            if (dvhs.contains(temp)){
                count++;
            }
        }
        if (count == n){
            if (best != null){
                if (Long.bitCount(best) > Long.bitCount(fs)){
                    best = fs;
                }
            } else {
                best = fs;
            }
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
        new ChargingChaos2().run();
    }
}
