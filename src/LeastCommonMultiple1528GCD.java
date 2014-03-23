import java.util.Scanner;

public class LeastCommonMultiple1528GCD {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            final int num = s.nextInt();
            int l = s.nextInt();
            for (int j = 1; j < num; j++) {
                final int next = s.nextInt();
                l = lcm(l, next);
            }
            System.out.println(l);
            s.nextLine();
        }
        s.close();
    }
    
    private static int lcm(final int a, final int b){
        final int g = gcd(a, b);
        return (a/g) *b;
    }
    
    private static int gcd(final int a, final int b){
        if (b == 0){
            return a;
        }
        
        if (a < b){
            return gcd(b, a);
        }
        
        return gcd(b, (a % b));
    }


}
