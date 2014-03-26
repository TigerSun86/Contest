import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Transmitters1916 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()){
            int x = s.nextInt();
            int y = s.nextInt();
            Point cen = new Point(x,y);
            double r = s.nextDouble();
            s.nextLine();
            int num = s.nextInt();
            s.nextLine();
            ArrayList<Point> list = new ArrayList<Point>();
            for (int i=0; i< num ; i++) {
                int xTemp = s.nextInt();
                int yTemp = s.nextInt();
                Point temp = new Point(xTemp, yTemp);
                double dis = cen.distance(temp);
                if (Double.compare(dis, r)<=0){
                    list.add(temp);
                }
            }
            int max = 0;
            for (Point i: list){
                int numBigger = 0;
                int numLess = 0;
                int numEqual = 0;
                for(Point j: list){
                    int value = function(cen, i, j);
                    if (value >0){
                        numBigger++;
                    } else if (value < 0) {
                        numLess++;
                    } else{
                        numEqual++;
                    }
                }
                int maxForThisSemiCir = Math.max((numBigger+numEqual),(numLess+numEqual));
                if (max < maxForThisSemiCir){
                    max = maxForThisSemiCir;
                }
            }
            System.out.println(max);
        }

        s.close();
    }
    
    private static int function(Point lp1, Point lp2, Point pCheck){
        int x1 = lp1.x;
        int y1 = lp1.y;
        int x2 = lp2.x;
        int y2 = lp2.y;
        int xc = pCheck.x;
        int yc = pCheck.y;
        return ((xc-x1)*(y2-y1))-((yc-y1)*(x2-x1));
    }

}
