import java.util.Scanner;

public class OnesandZeros {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            final int n = s.nextInt();
            final int p = s.nextInt();
            boolean found = false;
            boolean turn = true;//true, base + 1, false, base * p
            int base = 1 * p;
            while(true) {
                if (base % n == 0 || base % n == 1) {
                    found = true;
                    break;
                } else {
                    if (turn == true) {
                        if (base + 1 > 0) {
                            base = base + 1;
                        } else {
                            found = false;
                            break;
                        }
                    } else {
                        if (base * p > 0) {
                            base = base * p;
                        } else {
                            found = false;
                            break;
                        }
                    }
                }
            }
            if (found == true) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        s.close();
    }
}
