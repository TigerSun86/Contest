package gcjpractice;

import java.math.BigInteger;

/**
 * FileName: RabbitFace.java
 * @Description:
 *
Line up the captives
====================

As you ponder sneaky strategies for assisting with the great rabbit escape, you realize that you have an opportunity to fool Professor Booleans guards into thinking there are fewer rabbits total than there actually are.

By cleverly lining up the rabbits of different heights, you can obscure the sudden departure of some of the captives.

Beta Rabbits statisticians have asked you for some numerical analysis of how this could be done so that they can explore the best options.

Luckily, every rabbit has a slightly different height, and the guards are lazy and few in number. Only one guard is stationed at each end of the rabbit line-up as they survey their captive population. With a bit of misinformation added to the facility roster, you can make the guards think there are different numbers of rabbits in holding.

To help plan this caper you need to calculate how many ways the rabbits can be lined up such that a viewer on one end sees x rabbits, and a viewer on the other end sees y rabbits, because some taller rabbits block the view of the shorter ones.

For example, if the rabbits were arranged in line with heights 30 cm, 10 cm, 50 cm, 40 cm, and then 20 cm, a guard looking from the left side would see 2 rabbits (30 and 50 cm) while a guard looking from the right side would see 3 rabbits (20, 40 and 50 cm). 

Write a method answer(x,y,n) which returns the number of possible ways to arrange n rabbits of unique heights along an east to west line, so that only x are visible from the west, and only y are visible from the east. The return value must be a string representing the number in base 10.

If there is no possible arrangement, return "0".

The number of rabbits (n) will be as small as 3 or as large as 40
The viewable rabbits from either side (x and y) will be as small as 1 and as large as the total number of rabbits (n).

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java

Test cases
==========

Inputs:
    (int) x = 2
    (int) y = 2
    (int) n = 3
Output:
    (string) "2"

Inputs:
    (int) x = 1
    (int) y = 2
    (int) n = 6
Output:
    (string) "24"

Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Sep 2, 2015 11:21:53 PM
 */
public class RabbitFace {
    private static final int MAX = 41;
    private static final BigInteger[][] COMBS = combGene(MAX);

    private static BigInteger[][] combGene (int max) {
        BigInteger[][] combs = new BigInteger[max][max];
        for (int n = 0; n < max; n++) {
            combs[n] = new BigInteger[41];
            combs[n][0] = BigInteger.ONE;
            combs[n][n] = BigInteger.ONE;
        }

        for (int n = 1; n < max; n++) {
            for (int k = 1; k < n; k++) {
                combs[n][k] = combs[n - 1][k - 1].add(combs[n - 1][k]);
            }
        }
        return combs;
    }

    private static void combTest () {
        for (int n = 0; n < MAX; n++) {
            for (int k = 0; k <= n; k++) {
                System.out.print(COMBS[n][k].toString() + "\t");
            }
            System.out.println();
        }
        System.out.print(COMBS[19][17].toString() + "\t");
    }

    private static BigInteger[][][] F_DP = fGene(MAX);

    private static BigInteger[][][] fGene (int max) {
        BigInteger[][][] ret = new BigInteger[max][max][max];
        for (int n = 0; n < max; n++) {
            ret[n] = new BigInteger[max][max];
            for (int lc = 0; lc < max; lc++) {
                ret[n][lc] = new BigInteger[max];
                for (int rc = 0; rc < max; rc++) {
                    ret[n][lc][rc] = BigInteger.ZERO;
                }
            }
        }
        return ret;
    }

    private static final BigInteger[][] S_DP = sGene(MAX);

    private static BigInteger[][] sGene (int max) {
        BigInteger[][] sdp = new BigInteger[max][max];
        for (int n = 0; n < max; n++) {
            sdp[n] = new BigInteger[max];
            for (int c = 0; c < max; c++) {
                sdp[n][c] = BigInteger.ZERO;
            }
        }
        return sdp;
    }


    private static BigInteger f (int n, int lc, int rc) {
        assert n >= 0 && lc >= 0 && rc >= 0;
        //System.out.printf("f(%d,%d,%d)%n", n, lc, rc);
        if (lc < rc) {
            int temp = lc;
            lc = rc;
            rc = temp;
        }
        if (!F_DP[n][lc][rc].equals(BigInteger.ZERO)) {
            return F_DP[n][lc][rc];
        }
        BigInteger sum = BigInteger.ZERO;
        int minPH = lc - 1;
        int maxPH = n - rc;
        if (lc == 1) {
            minPH = 0;
            maxPH = 0;
        } else if (rc == 1) {
            minPH = n - 1;
            maxPH = n - 1;
        }
        for (int posOfHigh = minPH; posOfHigh <= maxPH; posOfHigh++) {
            int ln = posOfHigh;
            int rn = n - 1 - posOfHigh;
            BigInteger comb = COMBS[n - 1][ln];
            BigInteger left = s(ln, lc - 1);
            BigInteger right = s(rn, rc - 1);
            BigInteger temp = comb;
            temp = temp.multiply(left);
            temp = temp.multiply(right);
            sum = sum.add(temp);
        }
        //System.out.printf("f(%d,%d,%d)=%s%n", n, lc, rc, sum.toString());
        F_DP[n][lc][rc] = sum;
        return sum;
    }

    private static BigInteger s (int n, int c) {
        assert n >= 0 && c >= 0;
        //System.out.printf("s(%d,%d)%n", n, c);
        if (!S_DP[n][c].equals(BigInteger.ZERO)) {
            return S_DP[n][c];
        }
        BigInteger sum = BigInteger.ZERO;
        if (n <= 1) {
            sum = BigInteger.ONE;
        } else {
            int lc = c;
            for (int rc = Math.max(3 - lc, 1); rc <= n + 1 - lc; rc++) {
                sum = sum.add(f(n, lc, rc));
            }
        }
        //System.out.printf("s(%d,%d)=%s%n", n, c, sum.toString());
        S_DP[n][c] = sum;
        return sum;
    }
    
    public static String answer (int x, int y, int n) {

        // Your code goes here.

        return f(n,x,y).toString();
    }

    public static void main (String[] args) {
        System.out.println(answer(2, 2, 3 ));
    }
}
