import java.util.HashMap;
import java.util.Scanner;

public class ReducedIDNumbers2353 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            int j = s.nextInt();
            int[] input = new int[j];
            s.nextLine();
            for (j = j -1; j >= 0; j--) {
                input[j] = s.nextInt();
                s.nextLine();
            }
            
            int m;
            for (m = 1; m < Integer.MAX_VALUE; m++) {
                final HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
                boolean found = true;
                for (int n = 0; n < input.length; n++) {
                    final int temp = input[n] % m;
                    if (hash.get(temp) != null) {
                        found = false;
                        break;
                    } else {
                        hash.put(temp, 1);
                    }
                }
                if (found) {
                    System.out.println(m);
                    break;
                }
            }
            
        }
        s.close();
    }

}
