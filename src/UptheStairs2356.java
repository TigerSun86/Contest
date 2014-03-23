import java.util.Scanner;
 class Carrier {
    public int f;
    public int up;
    public Carrier(int f, int up) {
        this.f = f;
        this.up = up;
    }
    public void oneStep (int fNum) {
        if (up == 1) {
            if (f < fNum) {
                f++;
                if (f == fNum) {
                    up = 0;
                }
            } else {//fNum==f
                up = 0;
                f--;
            }
        } else {// up == 0
            if (f >= 1) {
                f--;
            } 
        }
    }
    
}
public class UptheStairs2356 {


    public static void main (String[] args) {      
        Scanner s = new Scanner(System.in);
        int testCases = s.nextInt();
        s.nextLine();
        for (; testCases > 0; testCases--) {
            final int carNum = s.nextInt();
            final Carrier[] car = new Carrier[carNum];
            final int f = s.nextInt();
            int boxRemain = s.nextInt();
            
            s.nextLine();
            for (int subCases = carNum - 1; subCases >= 0; subCases--) {
                int temp1 = s.nextInt();
                int temp2 = s.nextInt();
                car[subCases] = new Carrier(temp1, temp2);
                s.nextLine();
            }
            int[] sortedTime = new int[carNum];
            boolean[] arrived = new boolean[carNum];
            int countSorted = 0;
            boolean allback= false;

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                if (allback) {
                    break;
                }
                for (int j = 0; j < carNum; j++) {
                    if (car[j].f==0 && car[j].up ==0 && arrived[j] == false) {
                        sortedTime[countSorted] = i;
                        arrived[j] = true;
                        countSorted++;
                        if (countSorted == carNum) {
                            allback = true;
                            break;
                        }
                    }
                    car[j].oneStep(f);

                }
            }
            int times = boxRemain / carNum;
            int partTime = f * 2 * times;
            int soloTime = 0;
            if (boxRemain % carNum != 0) {
                soloTime = f * 2;
            }
            int totalTime = partTime + soloTime;
            if (totalTime > f) {
                totalTime -= f; 
            }
            int temp = boxRemain % carNum - 1;
            if (temp < 0) temp = carNum -1;
            int beforeTime = sortedTime[temp]; 
            
            totalTime += beforeTime;

            System.out.println(totalTime);     
        }
        s.close();
    }
}
