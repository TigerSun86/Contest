import java.util.Scanner;

public class Vector {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        for (int i = s.nextInt(); i > 0; i--) {
            int dimension = s.nextInt();
            int[] vector = new int[dimension]; 
            for (int j = 0; j < dimension; j++) {
                vector[j] = s.nextInt();
            }
            int sum = 0;
            for (int k = 0; k < dimension; k++) {
                sum += s.nextInt() * vector[k];
            }

            System.out.println(sum);
        }
        s.close();
    }

}
