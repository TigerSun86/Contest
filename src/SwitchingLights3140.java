import java.util.BitSet;
import java.util.Scanner;

public class SwitchingLights3140 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        final int ls = s.nextInt();
        int cs = s.nextInt();
        s.nextLine();

        final BitSet bs = new BitSet(ls);
        bs.set(0, ls,false);
        for (int i = 0; i < cs; i++) {
            final int c = s.nextInt();
            final int start = s.nextInt()-1;
            final int end = s.nextInt()-1;
            if (c == 0) {
                bs.flip(start, end+1);
            } else {
                final BitSet bTemp = new BitSet(ls);
                bTemp.set(start, end+1);
                bTemp.and(bs);
                System.out.println(bTemp.cardinality());
            }
        }
        s.close();
    }

}
