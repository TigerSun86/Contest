import java.util.Scanner;
public class SheldonsFriendshipII3778 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        int count = 0;
        while (s.hasNext()) {
            String str = s.next();
            if (str.equals("n")) {
                int tmp = s.nextInt();
                if (tmp > max) {
                    max = tmp;
                }
            }
            
            count++;
            if (count > 100000) {
                break;
            }
        }
        s.close();
        System.out.println(max*max);
    }
}
