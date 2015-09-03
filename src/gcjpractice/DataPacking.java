package gcjpractice;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

/**
 * FileName:     DataPacking.java
 * @Description: 
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 31, 2014 10:08:58 AM 
 */
public class DataPacking {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        x = sc.nextInt();
        sc.nextLine();
        
        s = new int[n];
        for (int ni = 0; ni < n; ni++) {
            s[ni] = sc.nextInt();
        }
    }
    
    int n;
    int x;
    int[] s;
        
    void solve () {
        Arrays.sort(s);
        int count = 0;
        BitSet b = new BitSet(n);
        
        for (int i = n-1; i >=0; i--){
            if (!b.get(i)){
                b.set(i);
                count++;
                int pre = -1;
                for (int j = 0; j < n; j++){
                    if (!b.get(j)){
                        if (s[i]+s[j]>x) {
                            break;
                        } else {
                            pre = j;
                        }
                    }
                }
                if (pre != -1){
                    b.set(pre);
                }
            }
        }
        System.out.println(count);
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
        new DataPacking().run();
    }
}
