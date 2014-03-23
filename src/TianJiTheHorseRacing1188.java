import java.util.PriorityQueue;
import java.util.Scanner;

public class TianJiTheHorseRacing1188 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int num = s.nextInt();
            if (num == 0) {
                break;
            }
            s.nextLine();
            final PriorityQueue<Integer> t = new PriorityQueue<Integer>();
            final PriorityQueue<Integer> k = new PriorityQueue<Integer>();

            for (int i = 0; i < num; i++) {
                t.add(s.nextInt());
            }
            s.nextLine();
            for (int i = 0; i < num; i++) {
                k.add(s.nextInt());
            }
            s.nextLine();

            int win = 0;

            final PriorityQueue<Integer> tieQ = new PriorityQueue<Integer>();
            while (!k.isEmpty() && !t.isEmpty()) {
                final int tH = t.remove();
                final int kH = k.peek();
                if (tH > kH) {
                    win++;
                    k.remove();
                } else {
                    if (!tieQ.isEmpty() && tH > tieQ.peek()) {
                        win++;
                        tieQ.remove();
                    } else {
                        if (tH == kH) {
                            k.remove();
                            tieQ.add(kH); // Might tie.
                        }
                    }
                }
            }
            int lose = num - win - tieQ.size();
            final int score = (win - lose) * 200;
            System.out.println(score);
        }
        s.close();
    }
}
