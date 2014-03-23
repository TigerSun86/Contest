import java.util.Scanner;

public class NumberSteps {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        for (int i = s.nextInt(); i > 0; i--) {
            final int x = s.nextInt();
            final int y = s.nextInt();
            if (((x == y) || (x == y + 2)) && (x >= 0) && (y >= 0)) {
                // even
                if (x % 2 == 0) {
                    System.out.println(x * 2 - (x - y));
                } else { // odd
                    System.out.println(x * 2 - 1 - (x - y));
                }
            } else {
                System.out.println("No Number");
            }
        }
        s.close();
    }
}
