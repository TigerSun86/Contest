import java.util.Scanner;

public class Rank3456 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        final int jScore = s.nextInt();
        i -= 1;
        int rank  = 1;
        for (; i > 0; i--) {
            final int temp = s.nextInt();
            if (temp > jScore){
                rank++;
            }
        }
        System.out.println(rank);
        s.close();
    }

}
