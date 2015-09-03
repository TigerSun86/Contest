import java.util.LinkedList;
import java.util.Scanner;

public class ListConcatenateApp {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        LinkedList<Object> circList1 = new LinkedList<Object>();
        LinkedList<Object> circList2 = new LinkedList<Object>();

        System.out.println("Please input 5 radiuses for circle list 1:");
        for (int i = 0; i < 5; i++) {
            double r = s.nextDouble();
            Circle c = new Circle(r);
            circList1.add(c);
        }
        System.out.println("Please input 10 radiuses for circle list 2:");
        for (int i = 0; i < 10; i++) {
            double r = s.nextDouble();
            Circle c = new Circle(r);
            circList2.add(c);
        }

        ListConcatenate.concatenate(circList1, circList2);

        System.out.println("The circles of concatenated list:");
        for (int i = 0; i < circList1.size(); i++) {
            Circle c = (Circle) circList1.get(i);
            System.out.printf("Circle %d, radius %f, area %f%n", i, c.radius,
                    c.area());
        }
        s.close();
    }
}
