import java.util.Scanner;

public class CircleCalculationApp {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        Circle[] myCircles = new Circle[3];

        for (int i = 0; i < myCircles.length; i++) {
            double r = s.nextDouble();
            myCircles[i] = new Circle(r);
        }

        double area = areaTotal(myCircles, 0);
        System.out.printf("Total area is : %f", area);

        s.close();
    }

    public static double areaTotal (Circle[] circArray, int currentIndex) {
        if (circArray.length == currentIndex) {
            return 0;
        }

        double currentArea = circArray[currentIndex].area();
        double sum = currentArea + areaTotal(circArray, currentIndex + 1);
        return sum;
    }
}
