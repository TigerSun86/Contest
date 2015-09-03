package gcjpractice;

import java.util.BitSet;
import java.util.Scanner;

/**
 * FileName: NewYearResolution.java
 * @Description: Facebook Hacker Cup 2015 Qualification Round
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jan 10, 2015 3:39:14 PM
 */
public class NewYearResolution {
    final Scanner sc = new Scanner(System.in);

    private static class Nutri {
        int a;
        int b;
        int c;

        public Nutri(int a, int b, int c) {
            super();
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals (Object o) {
            if (!(o instanceof Nutri)) {
                return false;
            } else {
                Nutri t = (Nutri) o;
                return (a == t.a) && (b == t.b) && (c == t.c);
            }
        }

        public boolean isLess (Nutri t) {
            return (a < t.a) && (b < t.b) && (c < t.c);
        }
    }

    void read () {
        int ga = sc.nextInt();
        int gb = sc.nextInt();
        int gc = sc.nextInt();
        sc.nextLine();
        g = new Nutri(ga, gb, gc);

        n = sc.nextInt();
        sc.nextLine();
        foods = new Nutri[n];
        for (int ni = 0; ni < n; ni++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            sc.nextLine();
            foods[ni] = new Nutri(a, b, c);
        }
    }

    Nutri g;
    Nutri[] foods;
    int n;
    BitSet visited;

    void solve () {
        int maxComb = 1 << n;
        visited = new BitSet(maxComb);
        boolean ret = check();
        if (ret) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private boolean check () {
        BitSet comb = new BitSet(n);
        Nutri cur = new Nutri(0, 0, 0);
        for (int i = 0; i < foods.length; i++) {
            comb.set(i);
            boolean ret = checkOne(cur, foods[i], comb);
            comb.clear(i);
            if (ret) {
                return ret;
            }
        }

        return false;
    }

    private boolean checkOne (Nutri sum, Nutri add, BitSet comb) {
        int idx = (int) comb.toLongArray()[0];
        if (visited.get(idx)) {
            return false;
        }
        visited.set(idx);
        Nutri cur = new Nutri(sum.a + add.a, sum.b + add.b, sum.c + add.c);
        if (cur.equals(g)) {
            // System.out.println(comb.toString());
            return true;
        } else if (cur.isLess(g)) {
            for (int i = 0; i < foods.length; i++) {
                if (!comb.get(i)) {
                    comb.set(i);
                    boolean ret = checkOne(cur, foods[i], comb);
                    comb.clear(i);
                    if (ret) {
                        return ret;
                    }
                }
            }
            return false;
        } else {
            return false;
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
        new NewYearResolution().run();
    }
}
