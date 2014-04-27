import java.util.BitSet;
import java.util.Scanner;

/**
 * FileName: ChargingChaos.java
 * @Description: Time limited in large
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Apr 25, 2014 11:09:38 PM
 */
public class ChargingChaos {
    static BitSet[] o;
    static BitSet[] g;
    static BitSet forexam;
    static int n;
    static int l;
    static int count;

    public static void main (final String[] args) {
        final Scanner s = new Scanner(System.in);
        final int tc = s.nextInt();
        s.nextLine();
        for (int tci = 1; tci <= tc; tci++) {
            n = s.nextInt();
            l = s.nextInt();
            s.nextLine();
            o = new BitSet[n];
            for (int ni = 0; ni < n; ni++) {
                o[ni] = new BitSet(l);
                final String temp = s.next();
                for (int c = 0; c < temp.length(); c++) {
                    final char ct = temp.charAt(c);
                    if (ct == '1') {
                        o[ni].set(c);
                    }
                }
            }
            forexam = new BitSet(l);
            forexam.or(o[0]);

            s.nextLine();
            g = new BitSet[n];
            for (int ni = 0; ni < n; ni++) {
                g[ni] = new BitSet(l);
                final String temp = s.next();
                for (int c = 0; c < temp.length(); c++) {
                    final char ct = temp.charAt(c);
                    if (ct == '1') {
                        g[ni].set(c);
                    }
                }
            }
            count = Integer.MAX_VALUE;
            check(0);
            flip(0);
            check(0);
            if (count == Integer.MAX_VALUE) {
                System.out.printf("Case #%d: %s%n", tci, "NOT POSSIBLE");
            } else {
                System.out.printf("Case #%d: %d%n", tci, count);
            }

        }
        s.close();
    }

    private static void flip (int i) {
        for (int ni = 0; ni < n; ni++) {
            o[ni].flip(i);
        }

    }

    private static void check (int i) {
        if (i == l) {
            if (exam()) {
                final int cTemp = count();
                if (count > cTemp) {
                    count = cTemp;
                    
                }
            }
            return;
        }
        final int newI = i + 1;
        check(newI);
        flip(newI);
        check(newI);
        flip(newI);
    }

    private static int count () {
        final BitSet temp = new BitSet(l);
        temp.or(forexam);
        temp.xor(o[0]);
        return temp.cardinality();

    }

    private static boolean exam () {
        final BitSet result = new BitSet(n);
        for (BitSet oi: o){
            for (int i =0; i < g.length;i++){
                final BitSet gi = g[i];
                if (oi.equals(gi)){
                    result.set(i);
                    break;
                }
            }
        }
        return result.cardinality() ==n;
    }
}
