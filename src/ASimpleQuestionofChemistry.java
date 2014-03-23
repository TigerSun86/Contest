import java.util.Scanner;


public class ASimpleQuestionofChemistry {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        double oldNumber = s.nextDouble();
        while (true) {
            final double newNumber = s.nextDouble();
            if (newNumber == 999) {break;}
            System.out.printf("%.2f%n", newNumber - oldNumber);
            oldNumber = newNumber;
        }
        System.out.println("End of Output");
        s.close();
    }
}
