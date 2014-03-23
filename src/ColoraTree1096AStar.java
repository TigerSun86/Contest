// time limited
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class State {
    int curNode;
    int finishTime;
    ArrayList<Integer> readyToBeExplored = new ArrayList<Integer>();
    HashMap<Integer,Integer> Explored = new HashMap<Integer,Integer>();
    int costG;
    int costF;
    int parent;
}

class TreeNode {
    int value;
    ArrayList<Integer> child = new ArrayList<Integer> ();
}
class ValueMap {
    int value;
    int nodeID;
}

public class ColoraTree1096AStar {
    private static int AStar(TreeNode[] tree, int numOfNodes, int root) {
        ArrayList<ValueMap> valueMap = generateValueMap(tree);
        
        ArrayList<State> queue = new ArrayList<State>();
        State initstate = new State();
        initstate.curNode = root;
        initstate.finishTime = 1;
        initstate.readyToBeExplored.addAll(tree[root].child);
        initstate.Explored.put(initstate.curNode, 0);
        initstate.parent = 0;
        initstate.costG = tree[root].value * initstate.finishTime;
        queue.add(initstate);
        
        while (!queue.isEmpty()) {
          final State curState = getState(queue);
          //System.out.println(curState.curNode + " parent " + curState.parent + " time " + curState.finishTime + " cost " + curState.costG + " h " + heristic(numOfNodes, curState, valueMap));
          if (curState.finishTime == numOfNodes) {return curState.costG;}
          ArrayList<State> result = explandNode(curState, tree, numOfNodes, valueMap);
          queue.addAll(result);
        }
        
        return -1;
    }
    
    private static ArrayList<ValueMap> generateValueMap (TreeNode[] tree) {
        ArrayList<ValueMap> valueMap = new ArrayList<ValueMap>();
        ValueMap vmTemp = new ValueMap();
        vmTemp.nodeID = 1;
        vmTemp.value = tree[1].value;
        valueMap.add(vmTemp);
        
        for (int nodeID = 2; nodeID < tree.length; nodeID++) {
            boolean inserted = false;
            final ValueMap vmTemp2 = new ValueMap();
            vmTemp2.nodeID = nodeID;
            vmTemp2.value = tree[nodeID].value;
            for (int j = 0; j < valueMap.size(); j++) {
                if (vmTemp2.value < valueMap.get(j).value) {
                    valueMap.add(j, vmTemp2);
                    inserted = true;
                    break;
                }
            }
            if (!inserted) {
                valueMap.add(vmTemp2);
            }
        }
        return valueMap;
    }

    private static State getState (ArrayList<State> queue) {
        final State state;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < queue.size(); i++) {
            final int costF = queue.get(i).costF;
            if (costF < min) {
                min = costF;
                minIndex = i;
            }
        }
        state = queue.remove(minIndex);
        return state;
    }

    private static int heristic (int numOfNodes, State state, ArrayList<ValueMap> valueMap) {
        int counter = numOfNodes;
        int result = 0;
        for (int i = 0; i < valueMap.size(); i++) {
            if (state.Explored.get(valueMap.get(i).nodeID) == null) {
                result += (counter * valueMap.get(i).value);
                counter--;
            }
        }
        return result;
    }

    private static ArrayList<State> explandNode (State curState, TreeNode[] tree, int numOfNodes, ArrayList<ValueMap> valueMap) {
        final ArrayList<State> result = new ArrayList<State>();
        for (int newN = 0; newN < curState.readyToBeExplored.size(); newN++) {
            final State newNode = new State();
            newNode.curNode = curState.readyToBeExplored.get(newN);
            newNode.finishTime = curState.finishTime + 1;
            newNode.readyToBeExplored.addAll(curState.readyToBeExplored);
            final boolean bRemove = newNode.readyToBeExplored.remove((Integer)newNode.curNode);
            assert bRemove;
            newNode.readyToBeExplored.addAll(tree[newNode.curNode].child);
            newNode.Explored.putAll(curState.Explored);
            newNode.Explored.put(newNode.curNode, 0);
            newNode.parent = curState.curNode;
            newNode.costG = curState.costG + tree[newNode.curNode].value * newNode.finishTime;
            final int costH = heristic(numOfNodes, newNode, valueMap);
            newNode.costF = newNode.costG + costH;
            result.add(newNode);
        }
        
        return result;
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            final int numOfNodes = s.nextInt();
            if (numOfNodes == 0) {break;}
            final TreeNode[] tree = new TreeNode[numOfNodes + 1];
            final int root = s.nextInt();
            s.nextLine();
            for (int nodeID = 1; nodeID < tree.length; nodeID++) {
                tree[nodeID] = new TreeNode();
                tree[nodeID].value = s.nextInt();
            }
            s.nextLine();
            for (int edge = 0; edge < numOfNodes - 1; edge++) {
                final int father = s.nextInt();
                final int child = s.nextInt();
                tree[father].child.add(child);
                s.nextLine();
            }
            int result = AStar(tree, numOfNodes, root);
            assert result != -1;
            System.out.println(result);
        }
        s.close();
    }

}
