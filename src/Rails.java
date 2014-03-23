import java.util.Scanner;
import java.util.Stack;

public class Rails {    
    private static boolean readOnePermutation(final int maxCount,
            final Scanner s) {
        int temp = s.nextInt();
        if (temp == 0) {
            System.out.println("");
            return false;
        }
        int count = 1;
        int i;
        Stack<Integer> stack = new Stack<Integer>();
        for (i = 0; i < maxCount; i++) {// one permutation
            while (temp >= count) {
                stack.push(count);
                count++;
            }
            if (temp != stack.pop()) {
                System.out.println("No");
                break;// for (int i = 0; i < maxCount; i ++) {// one permutation
            }
            if (i < maxCount - 1) {
                temp = s.nextInt();
            }
        }// for (int i = 0; i < maxCount; i ++) {// one permutation
        if (i == maxCount) {
            System.out.println("Yes");
        }
        return true;
    }

    public static void main(String[] args) {
        boolean endOfInput = false;
        Scanner s = new Scanner(System.in);
        while (!endOfInput) {// one block
            final int maxCount = s.nextInt();
            if (0 == maxCount) {
                endOfInput = true;
            }
            while (readOnePermutation(maxCount, s)) {
                s.nextLine();
            }
        }
        return;
    }
}
