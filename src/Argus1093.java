import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Argus1093 {
    static class Node implements Comparable<Node>{
        public int key;
        public int value;

        //@Override
        public int compareTo (Node arg0) {
            if (this.value != arg0.value){
                return this.value - arg0.value;
            } else {
                return this.key - arg0.key;
            }
        }
        //@Override
        public String toString(){
            return key+" "+value;
        }
        //@Override
        public boolean equals(Object o){
            if (!(o instanceof Node)){
                return false;
            }
            Node n = (Node)o;
            if (this.key == n.key && this.value == n.value){
                return true;
            } else {
                return false;
            }
        }
        //@Override
        public int hashCode(){
            int hash = 7;
            hash = 31 * hash + this.key;
            hash = 31 * hash + this.value;
            return hash;
        }
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        while(true) {
            String testCase = s.nextLine();
            if (testCase.equals("#")) {break;}
            String[]  arr = testCase.split(" ");
            map.put(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        }
        int k = s.nextInt();

        Node root = new Node();
        root.key = -1;
        root.value = 0;
        
        HashSet<Node> explored = new HashSet<Node>();
        explored.add(root);
        PriorityQueue<Node> que = new PriorityQueue<Node>();
        ArrayList<Node> list = expandNodes(root, map);
        que.addAll(list);
        
        int count =0;
        while (count < k) {
            Node n = que.remove();
            System.out.println(n.key);
            count++;
            n.value+= map.get(n.key);
            que.add(n);    
        }
        
        s.close();
    }

    private static ArrayList<Node> expandNodes (Node n, HashMap<Integer, Integer> map) {
        ArrayList<Node> list = new ArrayList<Node>();
        for (Entry<Integer, Integer> e :map.entrySet()){
            Node child = new Node();
            child.key = e.getKey();
            child.value = n.value + e.getValue();
            list.add(child);
        }
        
        return list;
    }

}
