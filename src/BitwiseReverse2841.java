import java.util.Scanner;

public class BitwiseReverse2841 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            int i = s.nextInt();
            if (i == 0) {break;}
            int result = 0;
            while (i != 0) {
                result *= 2;
                result += i % 2;
                i = i / 2;
            }
            System.out.println(result);
            s.nextLine();
        }
        s.close();
    }

}
