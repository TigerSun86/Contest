package gcjpractice;

import java.util.Scanner;

/**
 * FileName:     Bullseye.java
 * @Description: 2013 round1a a
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 8, 2014 4:11:03 PM 
 */
public class Bullseye {
    final Scanner sc = new Scanner(System.in);

    void read () {
        r = sc.nextLong();
        t = sc.nextLong();
        sc.nextLine();
    }

    long r;
    long t;
    
    void solve () {
        long max = 0;
        long min = 0;
        long i = 1;
        while (true){
            long area = getArea(i);
            if (area <= t){
                i *= 2;
            } else {
                max = i;
                min = i / 2;
                break;
            }
        }
        
        long mid = (max + min ) /2;
        while (max >= min){
            long area = getArea(mid);
            if (area == t) {
                break;
            } else if (area < t){
                min = mid +1;
                mid = (min + max) /2;
            } else if (area > t){
                max = mid - 1;
                mid = (min + max) /2;
            }
        }
        long area = getArea(mid);
        assert area >= t;
        if (area > t){
            mid--;
        }
        
        System.out.println(mid);
    }
    
    long getArea(long i){
        return 2*r * i + 2*i*i-i;
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
        new Bullseye().run();
    }
}
