import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * FileName: HappyTelephonesSWERC.java
 * @Description:
 * 
 * @author Xunhu(Tiger) Sun
 *         email: TigerSun86@gmail.com
 * @date Mar 22, 2014 1:40:39 PM
 */
public class HappyTelephonesSWERC {

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            final int phone = s.nextInt();
            if (phone == 0) {
                break;
            }
            final int interv = s.nextInt();
            s.nextLine();

            final TreeMap<Integer, Integer> tm =
                    new TreeMap<Integer, Integer>();

            for (int i = 0; i < phone; i++) {
                s.nextInt();
                s.nextInt();
                final int start = s.nextInt();
                final int end = start + s.nextInt();
                int vS = getValueStart(tm, start);
                tm.put(start, vS);
                for (int j = start + 1; j < end; j++) {
                    Integer vI = tm.get(j);
                    if (vI != null) {
                        tm.put(j, vI + 1);
                    }
                }
                Integer vE = tm.get(end);
                if (vE == null) {
                    Entry<Integer, Integer> e = tm.floorEntry(end);
                    Integer vPrev =  e.getValue();
                    tm.put(end, vPrev - 1);
                }
                System.out.println(tm);
                s.nextLine();
            }
            for (int i = 0; i < interv; i++) {
                final int start = s.nextInt();
                final int end = s.nextInt()+start;
                s.nextLine();

                int max = 0;
                Entry<Integer, Integer> e = tm.floorEntry(start);
                if ( e != null) {
                    if (max < e.getValue()) {
                        max = e.getValue();
                    }
                }
                for (int j = start; j < end; j++) {
                    Integer n = tm.get(j);
                    if (n != null) {
                        if (max < n) {
                            max = n;
                        }
                    }
                }
                System.out.println(max);

            }
        }
        s.close();
    }

    private static int getValueStart (TreeMap<Integer, Integer> tm, int start) {
        Integer vS = tm.get(start);
        if (vS == null) {
            Entry<Integer, Integer> e = tm.floorEntry(start);
            if (e == null) {
                return 1;
            } else {
                return e.getValue() + 1;
            }
        } else {
            return vS + 1;
        }
    }
}
