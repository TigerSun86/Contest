package gcjpractice;

import java.util.BitSet;
import java.util.Scanner;

/**
 * FileName: ttts2.java
 * @Description: Model
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 4, 2014 11:25:56 PM
 */
public class Main {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        sc.nextLine();
        
        bs = new BitSet[n];
        for (int ni = 0; ni < n; ni++) {
            bs[ni] = new BitSet();
        }
    }
    
    int n;
    BitSet[] bs;
    
    void solve () {

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
        new Main().run();
    }
}
