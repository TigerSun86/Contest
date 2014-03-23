import java.util.Scanner;

public class CountingLetters {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            String line = s.nextLine();
            int[] letters = new int[26]; 
            int max = 0;
            for (int j = 0; j < line.length(); j++) {
                letters[(int) (line.charAt(j) - 'a')]++;
                final int temp = letters[(int) (line.charAt(j) - 'a')];
                if (temp > max) {
                    max = temp;
                }
            }
            for (int k = 0; k < 26; k++) {
                if (letters[k] == max) {
                    System.out.printf("%c", ((char) k) + 'a');
                }
            }
            System.out.println();
        }
        s.close();
    }

}
