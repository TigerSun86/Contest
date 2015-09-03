import java.io.IOException;
import java.util.Random;

/**
 * FileName: RollingHash.java
 * @Description:
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Aug 3, 2015 5:17:19 PM
 */
public class RollingHash {
    private static final int BASE = 7;
    private static final int PRIME = 1000000 + 3;

    public static void main (String[] args) throws IOException {
        String s = "mississippi";
        String p = "a";

        System.out.println(s + " " + p + " " + findSubString(s, p));
        testRollingHash();
    }

    private static int findSubString (String s, String p) {
        // Calculate weight for each position of p.
        int[] weights = getWeights(p.length());
        // Calculate p's hashcode
        int hp = hashCode(p, 0, p.length() - 1, weights);
        // For s[0] to s[s.length-p.length]
        int lastHash = 0;
        for (int i = 0; i <= s.length() - p.length(); i++) {
            // Calculate s[i]'s hashcode based on s[i-1]'s hashcode
            int hs;
            if (i == 0) {
                hs = hashCode(s, i, i + p.length() - 1, weights);
            } else {
                hs =
                        rollingHash(lastHash, s.charAt(i - 1),
                                s.charAt(i + p.length() - 1), weights);
            }
            lastHash = hs;
            // System.out.println(hs+" "+s.substring(i, i + p.length() ));
            // If hashcode the same and string the same, found.
            if (hp == hs && p.equals(s.substring(i, i + p.length()))) {
                return i;
            }
        }

        // Didn't find, return -1
        return -1;
    }

    private static int[] getWeights (int length) {
        // Weight array decreasing as 7^4, 7^3,..., 7^0
        int[] weights = new int[length];
        weights[length - 1] = 1;
        // for i = length -2 to 0
        for (int i = length - 2; i >= 0; i--) {
            weights[i] = (weights[i + 1] * BASE) % PRIME;
            assert weights[i] > 0;
        }
        return weights;
    }

    private static int hashCode (String s, int start, int end, int[] weights) {
        assert end + 1 - start == weights.length;
        // for c in s[start] to s[end]
        int h = 0;
        for (int i = 0; i < weights.length; i++) {
            char c = s.charAt(i + start);
            h += getCharValue(c) * weights[i];
            h %= PRIME;
            assert h >= 0;
        }
        return h;
    }

    private static int rollingHash (int lastHash, char cRemove, char cAdd,
            int[] weights) {
        int h = lastHash;
        h = h + PRIME - ((getCharValue(cRemove) * weights[0]) % PRIME);
        h %= PRIME;
        assert h >= 0;
        h *= BASE;
        h %= PRIME;
        assert h >= 0;
        h += getCharValue(cAdd) * weights[weights.length - 1];
        h %= PRIME;
        assert h >= 0;
        return h;
    }

    private static final int getCharValue (char c) {
        return c - 'a' + 1;
    }

    private static void testRollingHash () {
        // String s = random long word;
        // length = 1000
        // print all weights
        // for all subs of s
        // print rolling hash
        // assert hash >=0
        StringBuilder sb = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + ran.nextInt(26)));
        }
        String s = sb.toString();
        int length = 999;
        int[] weights = getWeights(length);
        for (int w : weights) {
            // System.out.println(w);
        }
        int lastHash = 0;
        for (int i = 0; i <= s.length() - length; i++) {
            // Calculate s[i]'s hashcode based on s[i-1]'s hashcode
            int hs;
            if (i == 0) {
                hs = hashCode(s, i, i + length - 1, weights);
            } else {
                hs =
                        rollingHash(lastHash, s.charAt(i - 1),
                                s.charAt(i + length - 1), weights);
            }
            lastHash = hs;
            System.out.println(hs + " " + s.substring(i, i + length));
        }
    }
}
