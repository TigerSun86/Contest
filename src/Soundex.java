import java.util.Scanner;

public class Soundex {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String in = s.nextLine();
            int oldChar = 0;
            for (int i = 0; i < in.length(); i++) {
                final int newChar;
                switch (in.charAt(i)) {
                    case 'B':
                    case 'F':
                    case 'P':
                    case 'V':
                        newChar = 1;
                        break;
                    case 'C':
                    case 'G':
                    case 'J':
                    case 'K':
                    case 'Q':
                    case 'S':
                    case 'X':
                    case 'Z':
                        newChar = 2;
                        break;
                    case 'D':
                    case 'T':
                        newChar = 3;
                        break;
                    case 'L':
                        newChar = 4;
                        break;
                    case 'M':
                    case 'N':
                        newChar = 5;
                        break;
                    case 'R':
                        newChar = 6;
                        break;
                    default:
                        newChar = 0;
                        break;
                }
                if (newChar != 0 && newChar != oldChar) {
                    System.out.printf("%d", newChar);
                }
                oldChar = newChar;
            } // end of for (int i = 0; i < in.length(); i++) {
            System.out.println();
        }// end of while (s.hasNextLine()) {

        s.close();
    }
}
