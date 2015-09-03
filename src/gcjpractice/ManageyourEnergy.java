package gcjpractice;

import java.util.Scanner;

/**
 * FileName:     ManageyourEnergy.java
 * @Description: 2013 round1a b
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 8, 2014 11:27:28 PM 
 */
public class ManageyourEnergy {
    final Scanner sc = new Scanner(System.in);

    void read () {
        e = sc.nextInt();
        r = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine();
        v = new int[n];
        for (int i = 0; i < n; i++){
            v[i] = sc.nextInt();
        }
        sc.nextLine();
    }

    int e;
    int r;
    int n;
    int[] v;
    
    void solve () {
        int[][] a = new int[n][e+1];
        for (int i = 0; i < a.length;i++){
            a[i] = new int[e+1];
            for (int j = 0; j < a[0].length;j++){
                a[i][j] = -1;
            }
        }
        for (int k = 0; k <= e; k++){ // energey to spend
            int remaine = Math.min(e - k + r, e);
            a[0][remaine] = Math.max(v[0] * k, a[0][remaine]);
        }
        for (int i = 1; i < n; i++){ // activity
            for (int j = r; j <= e; j++){ // cur energy
                if (a[i-1][j] == -1){
                    continue;
                }
                for (int k = 0; k <= j; k++){ // energey to spend
                    int remaine = Math.min(j -k + r, e);
                    int newvalue = v[i] * k + a[i -1][j];
                    a[i][remaine] = Math.max(newvalue, a[i][remaine]);
                }
            }
        }
        int ans = -1;
        for (int j = 0; j <= e; j++){
            ans = Math.max(ans,a[n-1][j]);
        }
        System.out.println(ans);
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
        new ManageyourEnergy().run();
    }
}
