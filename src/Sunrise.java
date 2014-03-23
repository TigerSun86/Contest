import java.util.Scanner;

public class Sunrise {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int testCases = s.nextInt();
        s.nextLine();
        for (; testCases > 0; testCases--) {
            int subCases = s.nextInt();
            int[] input = new int[subCases];
            s.nextLine();
            for (subCases = subCases - 1; subCases >= 0; subCases--) {
                input[subCases] = s.nextInt();
                s.nextLine();
            }
            
            System.out.println();     
        }
        s.close();
    }
}