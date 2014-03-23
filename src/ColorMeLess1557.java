import java.util.Scanner;

public class ColorMeLess1557 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        final int[][] targetSet = new int[16][3];
        for (int i = 0; i < 16; i++) {
            targetSet[i] = new int[3];
            for (int j = 0; j < 3; j++) {
                targetSet[i][j] = s.nextInt();
            }
            s.nextLine();
        }

        while (true) {
            final int[] num = new int[3];
            for (int j = 0; j < 3; j++) {
                num[j] = s.nextInt();
            }
            if (num[0] == -1) {break;}
            
            int minDis = Integer.MAX_VALUE;
            int minIndex = -1;
            
            for (int i = 0; i < 16; i++) {
                final int dis = getDistance(num, targetSet[i]);
                if (dis < minDis) {
                    minDis = dis;
                    minIndex = i;
                }
                if (dis == 0) {break;}
            }
            System.out.printf("(%d,%d,%d) maps to (%d,%d,%d)%n", num[0], num[1], num[2], targetSet[minIndex][0], targetSet[minIndex][1], targetSet[minIndex][2]);
            
            s.nextLine();
        }
        
        s.close();
    }

    private static int getDistance(int[] color1, int[] color2) {
        double distance = 0;
        for (int j = 0; j < 3; j++) {
            distance += (color1[j]- color2[j]) * (color1[j]- color2[j]);
        }
        distance = Math.sqrt(distance);
        return (int) distance;
    }

}
