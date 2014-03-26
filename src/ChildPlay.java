import java.util.ArrayList;
import java.util.Scanner;

public class ChildPlay {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int testCase = s.nextInt();
        s.nextLine();
        for (; testCase > 0; testCase--) {
            final int num = s.nextInt();
            s.nextLine();
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int st = 0; st < num; st++) {
                arr.add(s.nextInt());
            }
            s.nextLine();
            int[] ans = new int[num + 1];
            for (int i = 1; i < arr.size() + 1; i++) {
                ans[i] = 1;
            }
            for (int i = arr.size() - 1; i >= 0; i--) {
                int cur = arr.get(i);
                for (int j = i - 1; j >= 0; j--) {
                    int left = arr.get(j);
                    if (left > cur) {
                        if (ans[left] < ans[cur] + 1) {
                            ans[left] = ans[cur] + 1;
                        }
                    }
                }
            }
            int max = 0;
            for (int i : ans) {
                if (max < i) {
                    max = i;
                }
            }

            System.out.println(max);
        }
        s.close();
    }

}
