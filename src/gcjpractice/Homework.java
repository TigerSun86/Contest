package gcjpractice;

import java.util.Scanner;

/**
 * FileName: Homework.java
 * @Description:
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 18, 2015 12:00:45 AM
 */
public class Homework {
    final Scanner sc = new Scanner(System.in);

    void read () {
        String[] str = sc.nextLine().split(" ");
        a = Integer.parseInt(str[0]);
        b = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);
    }

    int a;
    int b;
    int k;

    void solve () {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (primacity[i] == k) {
                count++;
            }
        }
        System.out.println(count);
    }

    static int[] primacity;

    static void gene () {
        int max = 10000001;
        primacity = new int[max];

        for (int i = 2; i < max; i++) {
            if (primacity[i] == 0) {
                for (int j = 1; i * j < max; j++) {
                    primacity[i * j]++;
                }
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
        gene();
        new Homework().run();
    }
}
