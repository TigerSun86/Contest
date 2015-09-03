package gcjpractice;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * FileName:     UpandDown.java
 * @Description: 
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 31, 2014 12:00:04 PM 
 */
public class UpandDown {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        sc.nextLine();
        
        a = new LinkedList<Integer>();
        for (int ni = 0; ni < n; ni++) {
            a.add(sc.nextInt());
        }
    }
    
    int n;
    LinkedList<Integer> a;
        
    void solve () {
        int fb = -1;
        int lb = -1;
        
        int pre = -1;
        for (int i = 0; i < n; i++){
            if (a.get(i) > pre){
                fb = i;
                pre = a.get(i);
            } else{
                break;
            }
        }
        pre = -1;
        for (int i = n - 1; i >=0; i--){
            if (a.get(i) > pre){
                lb = i;
                pre = a.get(i);
            } else {
                break;
            }
        }
        fb++;
        lb--;
        int count  = 0;
        while (fb <= lb){
            int minI = min(fb, lb);
            int v = a.get(minI);
            int uI = upPos(v, fb);
            int dI = downPos(v, lb);
            if ((minI - uI) < (dI - minI)){
                count += minI-uI;
                fb++;
                a.remove(minI);
                a.add(uI, v);
            } else {
                count += dI - minI;
                lb--;
                a.add(dI, v);
                a.remove(minI);
            }
        }
        System.out.println(count);
    }
    int min (int fb, int lb){
        int min  = Integer.MAX_VALUE;
        int index = -1;
        for (int i = fb; i <=lb;i++){
            if (a.get(i)< min){
                min = a.get(i);
                index = i;
            }
        }
        return index;
    }
    
    int upPos(int v, int fb){
        for (int i = 0; i <= fb; i++) {
            if (a.get(i)>= v){
                return i;
            }
        }
        assert false;
        return -1;
    }
    int downPos(int v, int lb){
        for (int i = n-1; i >=lb; i--){
            if (a.get(i) >=v){
                return i;
            }
        }
        assert false;
        return -1;
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
        new UpandDown().run();
    }
}
