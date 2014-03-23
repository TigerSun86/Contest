import graphsearch.Node;

import java.util.ArrayList;
import java.util.Scanner;
class Node{
    int cost;
    int sumCost;
    int father;
    boolean explored = false;
    ArrayList<Integer> list = new ArrayList<Integer>();
}
public class ColoraTree1096Damnit {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            final int numOfNodes = s.nextInt();
            if (numOfNodes == 0) {break;}
            final int root = s.nextInt();
            s.nextLine();
            final Node[] nodes = new Node[numOfNodes + 1];
            for (int i = 1; i <= numOfNodes; i++) {
                nodes[i] = new Node();
                nodes[i].cost = s.nextInt();
                nodes[i].sumCost = nodes[i].cost;
                nodes[i].list.add(i);// add itself
            }
            s.nextLine();
            for (int edge = 0; edge < numOfNodes - 1; edge++) {
                final int father = s.nextInt();
                final int child = s.nextInt();
                nodes[child].father = father;
                s.nextLine();
            }

            while(true) {
                double max = 0;
                int maxIndex = -1;
                for (int j = 1; j <= numOfNodes; j++) {
                    if (nodes[j].sumCost / nodes[j].list.size() > max && !nodes[j].explored && j != root) {
                        max = nodes[j].sumCost / nodes[j].list.size();
                        maxIndex = j;
                    }
                }
                if (maxIndex == -1) {break;}// done
                nodes[maxIndex].explored = true;
                // add all the follower of child and child itself into ancestor's follower list
                int father = nodes[maxIndex].father;
                while (nodes[father].explored) {
                    father= nodes[father].father;
                }
                nodes[father].list.addAll(nodes[maxIndex].list);
                nodes[father].sumCost = 0;
                for (int i = 0; i < nodes[father].list.size(); i++) {
                    final int nID = nodes[father].list.get(i);
                    nodes[father].sumCost += nodes[nID].cost * (i + 1);
                }
            }
            int cost = 0;
            for (int i = 0; i < numOfNodes; i++) {
                final int curNode = nodes[root].list.get(i);
                cost += nodes[curNode].cost * (i + 1);
            }
            System.out.println(cost);
        }
        s.close();
    }
}