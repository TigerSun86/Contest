import java.util.Scanner;

public class DeathKnightHero {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        int count = 0;
        
        for (; i > 0; i--) {
            String line = s.nextLine();
            char pre = 0;
            boolean won = true;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'D' && pre == 'C') {
                    won = false;
                    break;
                }
                pre = line.charAt(j);
            }
            if (won == true) {
                count++;
            }
        }
        System.out.println(count);
        s.close();
    }
}