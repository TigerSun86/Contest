package gcjpractice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * FileName: CookingtheBooks.java
 * @Description: Facebook Hacker Cup 2015 Qualification Round
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 10, 2015 1:51:18 PM
 */
public class CookingtheBooks {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextLine().toCharArray();
    }

    char[] n;

    void solve () {
        small();
        System.out.print(" ");
        large();
        System.out.println();
    }

    private void large () {
        if (n.length == 1) {
            System.out.print(String.copyValueOf(n));
            return;
        }
        
        final int dropEnd = findDropEnd();
        if(dropEnd == n.length - 1) {
            System.out.print(String.copyValueOf(n));
        } else {
            final int candIdx = findLargestLastIdx(n, dropEnd + 1);
            final char cand = n[candIdx];
            int repIdx = -1;
            for(int i = 0; i <= dropEnd; i++){
                if(n[i] < cand){
                    repIdx = i;
                    break;
                }
            }
            assert repIdx != -1;
            final char[] ret = swap(candIdx, repIdx);
            System.out.print(String.copyValueOf(ret));
        }
        return;
    }

    private void small () {
        if (n.length == 1) {
            System.out.print(String.copyValueOf(n));
            return;
        }
        // Check 0 place.
        char min = '9' + 1;
        int idx = -1;
        for (int i = 1; i < n.length; i++) {
            final char cur = n[i];
            if ((cur != '0') && (cur < n[0]) && (cur <= min)) {
                min = cur;
                idx = i;
            }
        }
        if (idx != -1) {
            final char[] ret = swap(0, idx);
            System.out.print(String.copyValueOf(ret));
        } else {
            if (n.length == 2) {
                System.out.print(String.copyValueOf(n));
            } else {
                final int incEnd = findIncreaseEnd();
                if(incEnd == n.length - 1) {
                    System.out.print(String.copyValueOf(n));
                } else {
                    final int candIdx = findSmallestLastIdx(n, incEnd + 1);
                    final char cand = n[candIdx];
                    int repIdx = -1;
                    for(int i = 1; i <= incEnd; i++){
                        if(n[i] > cand){
                            repIdx = i;
                            break;
                        }
                    }
                    assert repIdx != -1;
                    final char[] ret = swap(candIdx, repIdx);
                    System.out.print(String.copyValueOf(ret));
                }
            }
        }
        return;
    }

    private int findIncreaseEnd () {
        char last = n[1];
        int i = 1;
        while((i < n.length)&&(last <= n[i])){
            last = n[i];
            i++;
        }
        return i - 1;
    }
    private int findDropEnd () {
        char last = n[0];
        int i = 0;
        while((i < n.length)&&(last >= n[i])){
            last = n[i];
            i++;
        }
        return i - 1;
    }
    private char[] swap (int i, int idx) {
        final char[] ret = Arrays.copyOf(n, n.length);
        if (i == idx) {
            return ret;
        } else {
            final char temp = ret[i];
            ret[i] = ret[idx];
            ret[idx] = temp;
            return ret;
        }
    }

    private static int findSmallestLastIdx (char[] input, int fromIdx) {
        assert fromIdx < input.length;
        char min = '9' + 1;
        int idx = -1;
        for (int i = fromIdx; i < input.length; i++) {
            if (min >= input[i]) {
                min = input[i];
                idx = i;
            }
        }
        assert idx != -1;
        return idx;
    }
    
    private static int findLargestLastIdx (char[] input, int fromIdx) {
        assert fromIdx < input.length;
        char max = '0' -1;
        int idx = -1;
        for (int i = fromIdx; i < input.length; i++) {
            if (max <= input[i]) {
                max = input[i];
                idx = i;
            }
        }
        assert idx != -1;
        return idx;
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
        new CookingtheBooks().run();
    }
}
