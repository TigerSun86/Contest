import java.util.Scanner;

public class CountingSheep {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int k = 1;
        for (int i = s.nextInt(); i > 0; i--) {
            int count = 0;
            for (int j = s.nextInt(); j > 0; j--) {
                if (s.next().equals("sheep")) {
                    count++;
                }
            }
            System.out.printf("Case %d: This list contains %d sheep.%n", k, count);
            k++;
            if (i != 1) {
                System.out.println();
            }
        }

        s.close();
    }

}
