package gcjpractice;

import java.util.BitSet;
import java.util.Scanner;

/**
 * FileName:     ChargingChaos.java
 * @Description: 2014 round1a a
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 4, 2014 11:49:05 PM 
 */
public class ChargingChaos {
    final Scanner sc = new Scanner(System.in);

    void read () {
        n = sc.nextInt();
        l = sc.nextInt();
        sc.nextLine();
        
        ol = new BitSet[n];
        for (int ni = 0; ni < n; ni++){
            ol[ni] = new BitSet(l);
            String temp = sc.next();
            for (int li = 0; li < l; li++){
                final boolean bit = temp.charAt(temp.length() - 1 - li)  == '1'? true: false;
                ol[ni].set(li, bit);
            }
        }
        sc.nextLine();
        
        dv = new BitSet[n];
        for (int ni = 0; ni < n; ni++){
            dv[ni] = new BitSet(l);
            String temp = sc.next();
            for (int li = 0; li < l; li++){
                final boolean bit = temp.charAt(temp.length() - 1 - li)  == '1'? true: false;
                dv[ni].set(li, bit);
            }
        }
        sc.nextLine();
        
/*        for (BitSet bs: ol){
            printbs(bs, l);
            System.out.print(" ");
        }
        System.out.println();
        for (BitSet bs: dv){
            printbs(bs, l);
            System.out.print(" ");
        }
        System.out.println();*/
    }
    
    void printbs(BitSet bs, int size){
        for(int i=size-1; i>=0; i--)
            System.out.print(bs.get(i)? 1 : 0);
    }
    
    int n;
    int l;
    BitSet[] ol;
    BitSet[] dv;
    
    BitSet best;
    void solve () {
        final BitSet[] fss = new BitSet[n];
        
        for (int i = 0; i < n; i++){
            final BitSet fs = (BitSet)ol[0].clone();
            fs.xor(dv[i]);
            fss[i] = fs;
        }
        best = null;
        for (BitSet fs: fss){
            check(fs);
        }
        if (best != null){
            System.out.println(best.cardinality());    
        } else {
            System.out.println("NOT POSSIBLE");
        }
    }

    private void check (BitSet fs) {
        int count = 0;
        for (int oi = 0; oi < n; oi++){
            BitSet newo = (BitSet)ol[oi].clone();
            newo.xor(fs);
            for(int di = 0; di < n; di++){
                if (newo.equals(dv[di])){
                    count++;
                    break;
                }
            }
        }
        if (count == n){
            if (best != null){
                if (best.cardinality() > fs.cardinality()){
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
        new ChargingChaos().run();
    }
}
