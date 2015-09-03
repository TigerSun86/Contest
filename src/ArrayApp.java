import java.util.Scanner;


public class ArrayApp {
    public static void main(String[] args){
        System.out.println("Please input 5 integers:");
        Scanner s = new Scanner(System.in);
        // Input
        int[] array = new int[5];
        for (int i = 0; i < array.length; i++){
            array[i] = s.nextInt();
        }
        
        // Average
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }
        double avg = ((double)sum )/ array.length;
        System.out.printf("Average is : %f%n", avg);
        
        // Reverse output.
        for (int i = array.length -1; i >=0; i--){
            System.out.printf("%d ",array[i]);
        }
        System.out.println();
        s.close();
    }
}
