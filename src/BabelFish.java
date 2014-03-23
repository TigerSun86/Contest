import java.util.HashMap;
import java.util.Scanner;
import java.lang.System;

public class BabelFish {
    public static void main (String[] args) {
        String[] words = new String[2];
        Scanner s = new Scanner(System.in);
        HashMap<String, String> hash = new HashMap<String, String>();

        while (s.hasNextLine()) {
            final String line = s.nextLine();
            words = line.split(" ");
            if (words.length > 1 && line.length() != 0) {
                hash.put(words[1], words[0]);
            } else if (line.length() != 0) {
                words[0] = (String)hash.get(words[0]);
                if (words[0] != null) {
                    System.out.println(words[0]);
                }
                else {
                    System.out.println("eh");
                }
            } else {
                //do nothing
            }
        }
        s.close();
    }
}
