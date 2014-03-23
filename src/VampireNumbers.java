import java.util.Arrays;
import java.util.Scanner;

public class VampireNumbers {
    private final static int MAX = 1000255 + 1;
    private final static boolean[] VTABLE = new boolean[MAX];
    private static void geneVTable2() {
        for (int i = 2; i < MAX / 2; i++) {
            for (int j = 2; j < MAX / 2; j++) {
                final int x = i * j;
                if (x > MAX) {break;} 
                final int[] numOccurI = numOccured(i);
                final int[] numOccurJ = numOccured(j);
                final int[] numOccurX = numOccured(x);
                for (int k = 0; k < numOccurI.length; k++) {
                    numOccurI[k] += numOccurJ[k];
                }
                if (Arrays.equals(numOccurX, numOccurI)) {
                    VTABLE[x] = true;
                }
            }

           //System.out.println(i);
        }
        //System.out.println("done");
    }
    private static int[] numOccured(int x) {
        final int[] numOccur = new int[10];
        int temp = x;
        while (temp != 0) {
            final int remainder = temp % 10;
            numOccur[remainder]++;
            temp /= 10;
        }
        return numOccur;
    }
    private static void geneVTable() {
        for (int i = 10; i < VTABLE.length; i++) {
           VTABLE[i] = isVNumber(i);
           System.out.println(i);
        }
        System.out.println("done");
    }
    private static boolean isVNumber(int x) {
        int[] array = splitDigits(x);
        Arrays.sort(array);
        boolean isVN = false;
        boolean hasNext = true;
        while (hasNext) {
            if (array[0] != 0) {
                if (vTest(array, x)) {
                    isVN = true;
                    break;
                }
            }
            hasNext = nextPer(array);
        }
        return isVN;
    }
    private static boolean nextPer (int[] array) {
        int pos1 = -1;
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] < array[i + 1]) {
                pos1 = i;
                break;
            }
        }

        if (pos1 == -1) {
            return false;
        }
        
        int pos2 = -1;
        for (int i = array.length - 1; i > pos1; i--) {
            if (array[pos1] < array[i]) {
                pos2 = i;
                break;
            }
        }
        swap(array, pos1, pos2);
        
        for (int l = pos1 + 1, h = array.length - 1; l < h; l++, h--) {
            swap(array, l, h);
        }
        return true;
    }
    private static void swap(int[] array, int pos1, int pos2) {
        final int temp;
        temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
        
    }
    public static void main (String[] args) {
        geneVTable2();
        Scanner s = new Scanner(System.in);
        while(true) {
            int x = s.nextInt();
            if (x == 0) {break;}
            
            while (!VTABLE[x]) {x++;}
            System.out.println(x);
        }
        s.close();
    }
    private static boolean vTest(int[] array, int x) {
        boolean result = false;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != 0) {
                final int fac1 = combine(array, 0, i);
                final int fac2 = combine(array, i, array.length);
                if (x == fac1 * fac2) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    private static int combine(int[] array, int i, int j) {
        StringBuffer result = new StringBuffer();
        for (int k = i; k < j; k++) {
           result.append(array[k]);
        }
        String mynewstring = result.toString();
        return Integer.valueOf(mynewstring);
    }
    private static int[] splitDigits(int x) {
        String str = String.valueOf(x);
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = Character.getNumericValue(str.charAt(i));
        }
        return result;
    }

}
