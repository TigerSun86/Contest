import java.util.BitSet;
import java.util.Scanner;

public class TheDrunkJailer {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        for (int i = s.nextInt(); i > 0; i--) {
            int number = s.nextInt();
            // true is locked, false is unlocked, and do not use the cell 0 in bitset(leave it false) 
            BitSet bs = new BitSet(number + 1);
            for (int j = 2; j <= number; j++) {
                for (int k = 1; k <= number; k++) {
                    if (k % j == 0) {
                        bs.flip(k);
                    }
                }
            }
            System.out.println(number - bs.cardinality());
        }

        s.close();
    }

}
