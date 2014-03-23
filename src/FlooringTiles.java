// Wrong Answer
import java.util.Scanner;

public class FlooringTiles {
    private final static int MAX = 1000000;
    private final static int[] APTABLE = new int[MAX];
    private static void geneApTable() {
        for (int i = 1; i < APTABLE.length; i++) {
            APTABLE[i] = getApproximate(i);
        }
        //System.out.println("done");
    }
    private static int getApproximate(int x) {
        int apCount = 0;
        int i = 1;
        while (i * i < x) {
            if (x % i == 0) {
                apCount+=2;
            }
            i++;
        }
        
        if (i * i == x) {
            apCount++;
        }
        
        return apCount;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        geneApTable();
        while(true) {
            final int n = s.nextInt();
            if (n == 0) {break;}
            int ans = 0;
            int i = n;
            while (i < MAX) {
                final int appro = APTABLE[i];
                final int halfAppro;
                if (appro % 2 == 0) {
                    halfAppro = appro / 2;
                } else {
                    halfAppro = appro / 2 + 1;
                }
                if (n == halfAppro) {
                    ans = i;
                    break;
                }
                i++;
            }

            System.out.println(ans);

        }
        s.close();
    }

}
