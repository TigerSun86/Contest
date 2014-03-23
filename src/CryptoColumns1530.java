import java.util.HashMap;
import java.util.Scanner;

public class CryptoColumns1530 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            final String key = s.nextLine();
            if (key.equals("THEEND")) {break;}
            final String cip = s.nextLine();
            final int keyLength = key.length();
            final int cipLength = cip.length();
            final int colLength = cipLength/keyLength;
            final String[] result = new String[keyLength];
            
            HashMap<Integer, Character> exploredIndex = new HashMap<Integer, Character>();
            int cipCounter = 0;
            for (int i = 0; i < keyLength; i++) {
                // loop keyLength times
                char min = 'Z' + 1;
                int minIndex = -1;
                for (int j = 0; j < keyLength; j++) {
                    // find the min char in key
                    if (exploredIndex.get(j) == null) {
                        if (key.charAt(j) < min) {
                            min = key.charAt(j);
                            minIndex = j;
                        }
                    }
                }
                
                exploredIndex.put(minIndex, min);
                // put substring in cip into certain spot in result
                result[minIndex] = cip.substring(cipCounter, cipCounter + colLength);
                cipCounter += colLength;
            }
            
            for (int i = 0; i < colLength; i++) {
                for (int j = 0; j < keyLength; j++) {
                    System.out.printf("%c", result[j].charAt(i));
                }
            }
            System.out.println();
        }
        s.close();
    }

}
