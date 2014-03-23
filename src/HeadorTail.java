import java.util.Scanner;

public class HeadorTail {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            int i = s.nextInt();
            if (i == 0) {break;}
            int mCount = 0;
            int jCount = 0;
            for (; i > 0; i--) {
                if (s.nextInt() == 0) {
                    mCount++;
                } else {
                    jCount++;
                }
            }
            System.out.printf("Mary won %d times and John won %d times%n", mCount, jCount);
        }

        s.close();
    }

}
