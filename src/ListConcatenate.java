import java.util.LinkedList;

public class ListConcatenate {
    
    public static void concatenate (LinkedList<Object> list1,
            LinkedList<Object> list2) {
        for (int i = 0; i < list2.size(); i++) {
            Object obj = list2.get(i);
            list1.add(obj);
        }
    }
    
    public static void concatenate2 (LinkedList<Object> list1,
            LinkedList<Object> list2) {
        list1.addAll(list2);
    }
}
