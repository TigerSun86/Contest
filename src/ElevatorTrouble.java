import java.util.ArrayDeque;
import java.util.Scanner;

public class ElevatorTrouble {
    /**** state begin ********************************************/
    private static class State {
        int cur;
        int cost;
    }

    private static boolean goalTest (State state, int goal) {
        if (state.cur == goal) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSameStates (State state1, State state2) {
        if (state1.cur == state2.cur) {
            return true;
        } else {
            return false;
        }
    }

    private static void stateCopy (State dec, State src) {
        dec.cur = src.cur;
        dec.cost = src.cost;
    }
    /**** state end ********************************************/
    private static State next (State node, int stepSide) {
        State newNode = new State();
        stateCopy(newNode, node);
        newNode.cur += stepSide;
        newNode.cost++;
        return newNode;
    }
    private static ArrayDeque<State> expandNodes (State node, int u, int d) {
        final ArrayDeque<State> result = new ArrayDeque<State>();

        result.add(next(node, u));
        result.add(next(node, d));

        return result;
    }
    
    private static void filterNodes (ArrayDeque<State> result,
            ArrayDeque<State> explored, int f) {
        final int resultsNumber = result.size(); // size may change dynamically
        for (int i = 0; i < resultsNumber; i++) {
            final State node = result.remove();
            if (node.cur > f || node.cur < 1) {
                continue;
            }
            boolean isExplored = false;
            // to check if the node has existed in the explored queue
            final int exploredNumber = explored.size();
            for (int j = 0; j < exploredNumber; j++) {
                final State exploredNode = explored.remove();
                // I just want to have a look, so don't forget to add it back
                explored.add(exploredNode);
                if (isSameStates(node, exploredNode)) {
                    isExplored = true;
                    /* here I didn't break because I don't want to change the
                       node order in explored queue */
                }
            }
            // if the node hasn't been explored, keep it; otherwise, discard it.
            if (!isExplored) {
                result.add(node);
            }
        }
    }
    
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        final int f = scanner.nextInt();
        final int s = scanner.nextInt();
        final int g = scanner.nextInt();
        final int u = scanner.nextInt();
        final int d = 0 - scanner.nextInt(); //negative
        
        final State initState = new State();
        initState.cur = s;
        initState.cost = 0;
        
        final ArrayDeque<State> frontier = new ArrayDeque<State>();
        final ArrayDeque<State> explored = new ArrayDeque<State>();
        // initialize the frontier using the initial state of problem

        frontier.add(initState);

        State goalNode = null;
        // if the frontier is empty then return failure
        while (!frontier.isEmpty()) {
            // choose a leaf node and remove it from the frontier
            final State node;
            node = frontier.remove();
            // the node is about to be explored
            explored.add(node);
            // if the node contains a goal state then return the corresponding
            // solution
            if (goalTest(node, g)) {
                goalNode = node;
                break;
            }
            // else expand the chosen node
            final ArrayDeque<State> result = expandNodes(node, u, d);
            // delete the nodes have already been explored
            filterNodes(result, explored, f);
            // also I don't need to add a node into frontier twice
            filterNodes(result, frontier, f);
            // add the resulting nodes to the frontier
            while (!result.isEmpty()) {
                frontier.add(result.remove());
            }
        }

        if (goalNode != null) {
            System.out.println(goalNode.cost);
        } else {
            System.out.println("use the stairs");
        }
        scanner.close();
    }

}
