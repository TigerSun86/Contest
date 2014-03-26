import java.util.Scanner;

/**
 * FileName: WorkingAtTheRestaurant.java
 * @Description:
 * 
 * @author Xunhu(Tiger) Sun
 *         email: TigerSun86@gmail.com
 * @date Mar 22, 2014 5:35:29 PM
 */
public class WorkingAtTheRestaurant {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int com = s.nextInt();
            if (com == 0) {
                break;
            }
            int cur = 2;
            int other = 1;
            int[] t = new int[3];
            s.nextLine();
            boolean isA = false;
            for (int i = 0; i < com; i++) {
                String[] l = s.nextLine().split(" ");
                if (l[0].charAt(0) == 'D') {
                    if (t[cur] != 0 && isA) {
                        System.out.printf("MOVE %d->%d %d%n", cur, other,
                                t[cur]);
                        t[other] = t[cur];
                        t[cur] = 0;

                        cur = switchN(cur);
                        other = switchN(other);
                    }
                    System.out.printf("DROP %d %d%n", cur,
                            Integer.parseInt(l[1]));
                    t[cur] += Integer.parseInt(l[1]);
                    isA = false;
                } else {
                    if (!isA) {
                        System.out.printf("MOVE %d->%d %d%n", cur, other,
                                t[cur]);
                        t[other] = t[cur];
                        t[cur] = 0;
                        cur = switchN(cur);
                        other = switchN(other);
                    }

                    System.out.printf("TAKE %d %d%n", cur,
                            Integer.parseInt(l[1]));
                    t[cur] -= Integer.parseInt(l[1]);
                    isA=true;
                }
            }
            System.out.println();
        }
    }

    private static int switchN (int cur) {
        if (cur == 1) {
            return 2;
        } else {
            return 1;
        }
    }

}
