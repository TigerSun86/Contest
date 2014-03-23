import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Lights3464TimeLimit2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        final int ls = s.nextInt();
        int cs = s.nextInt();
        s.nextLine();

        final ArrayList<HashSet<Integer>> c = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < ls; i++) {
            c.add(new HashSet<Integer>());
        }
        for (int i = 0; i < cs; i++) {
            final int n1 = s.nextInt() - 1;
            final int n2 = s.nextInt() - 1;
            s.nextLine();
            c.get(n1).add(n2);
            c.get(n2).add(n1);
        }

        final LinkedList<Node> front = new LinkedList<Node>();
        final BitSet bs = new BitSet(ls);
        final Node initNode = new Node();
        initNode.state = bs;
        initNode.depth = 0;
        initNode.lastAction = -1;
        front.add(initNode);

        while (true) {
            final Node node = front.removeFirst();
            if (goalTest(node.state,ls)) {
                System.out.println(node.depth);
                break;
            }
            front.addAll(expandNodes(node,c));
        }

        s.close();
    }

    private static LinkedList<Node> expandNodes(Node node,
            ArrayList<HashSet<Integer>> c) {
        final LinkedList<Node> children = new LinkedList<Node>();
        for (int i=0;i< c.size();i++){
            // Try turn each light except last one.
            if (node.lastAction == i){
                continue;
            }
            final Node child = new Node();
            child.depth = node.depth+1;
            child.lastAction = i;
            child.state = new BitSet(c.size());
            child.state.or(node.state); // copy father.
            child.state.flip(i);
            final HashSet<Integer> connects = c.get(i);
            for (Integer j: connects){
                child.state.flip(j);
            }

            children.add(child);
        }
        return children;
    }

    private static boolean goalTest(BitSet state, int ls) {
        if (state.cardinality() == ls){
            return true;
        }
        return false;
    }

    static class Node {
        public BitSet state;
        public int depth;
        public int lastAction;

    }

}
