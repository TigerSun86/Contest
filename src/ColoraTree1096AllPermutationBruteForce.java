// time limited
import java.util.HashMap;
import java.util.Scanner;

public class ColoraTree1096AllPermutationBruteForce {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            final int numOfNodes = s.nextInt();
            if (numOfNodes == 0) {break;}
            final int root = s.nextInt();
            s.nextLine();
            final int[] cI = new int[numOfNodes + 1];
            for (int i = 1; i <= numOfNodes; i++) {
                cI[i] = s.nextInt();
            }
            s.nextLine();
            HashMap <Integer, HashMap<Integer, Integer>> edges = 
                    new HashMap <Integer, HashMap<Integer, Integer>>();
            for (int edge = 0; edge < numOfNodes - 1; edge++) {
                final int father = s.nextInt();
                final int child = s.nextInt();
                final HashMap<Integer, Integer> childs;
                if (edges.get(father) == null) {
                    childs = new HashMap<Integer, Integer> ();
                    edges.put(father, childs);
                } else {
                    childs = edges.get(father);
                }
                childs.put(child, 0);
                s.nextLine();
            }
            final int[] per = new int[numOfNodes - 1];
            int index = 0;
            for (int i = 1; i <= numOfNodes; i++) {
                if (i != root) {
                    per[index] = i;
                    index++;
                }
            }
            
            final HashMap<Integer, Integer> frontierOfRoot = new HashMap<Integer, Integer> ();
            final HashMap<Integer, Integer> childsOfRoot = edges.get(root);
            if (childsOfRoot != null) {
                frontierOfRoot.putAll(childsOfRoot);
            }
            int min = Integer.MAX_VALUE;
            while (true) {
                    final HashMap<Integer, Integer> frontier = new HashMap<Integer, Integer> ();
                    frontier.putAll(frontierOfRoot);
                    int cost = cI[root];
                    boolean validPermutation = true;
                    for (int curPos = 0; curPos < per.length; curPos++) {
                        if (frontier.get(per[curPos]) == null) {
                            // can not expand this node yet
                            validPermutation = false;
                            break;
                        }
                        frontier.remove(per[curPos]);
                        final HashMap<Integer, Integer> childs = edges.get(per[curPos]);
                        if (childs != null) {
                            frontier.putAll(childs);
                        }
                        // color, and calculate cost
                        cost += (curPos + 2) * cI[per[curPos]];
                    }
                    if (validPermutation) {
                        if (cost < min) {
                            min = cost;
                        }
                    }
                // check next permutation
                if (!next(per)){break;}
            }
            System.out.println(min);
        }
        s.close();
    }
    
    private static void swap (int[] per, int pos1, int pos2) {
        final int temp = per[pos1];
        per[pos1] = per[pos2];
        per[pos2] = temp;        
    }
    private static boolean next (int[] per) {
        int pos1 = -1;
        for (int j = per.length - 2; j >= 0; j--) {
            if (per[j] < per[j + 1]) {
                pos1 = j;
                break;
            }
        }
        if (pos1 == -1) {
            return false;
        }
        
        int pos2 = -1;
        for (int j = per.length - 1; j > pos1; j--) {
            if (per[pos1] < per[j]) {
                pos2 = j;
                break;
            }
        }
        swap(per, pos1, pos2);
        
        for (int l = pos1+1,h = per.length-1; l<h; l++,h--) {
            swap(per, l, h);
        }
        return true;
    }
}
