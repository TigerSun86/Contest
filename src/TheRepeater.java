import java.util.ArrayList;
import java.util.Scanner;


/**
 * FileName:     TheRepeater.java
 * @Description: 
 *
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date May 3, 2014 12:29:58 PM 
 */
public class TheRepeater {
    public static void main (final String[] args) {
        final Scanner s = new Scanner(System.in);
        final int tc = s.nextInt();
        s.nextLine();
        for (int tci = 1; tci <= tc; tci++) {
            final int n = s.nextInt();
            s.nextLine();
            final String[] strs = new String[n];
            for (int ni = 0; ni < n; ni++) {
                strs[ni] = s.nextLine();
            }
            StringBuilder ans = null;
            boolean imp = false;
            final int[][] lens = new int[n][1000];
            for (int i= 0; i < strs.length;i++){
                final String str =  strs[i];
                char lastc = 0;
                int count = 1;
                lens[i] = new int [1000];
                final StringBuilder curans = new StringBuilder();
                for (int j = 0; j < str.length(); j ++){
                    final char curc = str.charAt(j);
                    if (Character.compare(curc, lastc)!=0){
                        if (lastc != 0){
                            lens[i][curans.toString().length() - 1] = count;
                        }
                        count = 1;
                        curans.append(curc);
                        lastc = curc;
                    } else {
                        count++;
                    }
                }
                lens[i][curans.toString().length() - 1] = count;
                if (ans == null){
                    ans  = curans;
                } else {
                    if (!ans.toString().equals(curans.toString())){
                        imp = true;
                        break;
                    }
                }
            }
            if (imp){
                System.out.printf("Case #%d: %s%n", tci,"Fegla Won");
            } else {
                int totalop = 0;
                for (int j = 0; j < ans.toString().length(); j++){
                    int sum =0;
                    for (int i = 0; i< strs.length;i++){
                        sum += lens[i][j];
                    }
                    int best = Math.round(sum/ strs.length);
                    int sumop = 0;
                    for (int i = 0; i< strs.length;i++){
                        sumop += Math.abs(lens[i][j]-best);
                    }
                    //System.out.println(best);
                    totalop += sumop;
                }
                System.out.printf("Case #%d: %d%n", tci, totalop);
            }
        }
        s.close();
    }
}
