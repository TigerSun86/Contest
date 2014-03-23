//Runtime Error
import java.util.Arrays;
import java.util.Scanner;

public class AppleTree2840Unsolved {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    // the Shortest Pathes from node 0
    private static int[] ShortestPathes (int[][] roads, int nodesCount) {
        final int[] distance = new int [nodesCount];
        Arrays.fill(distance, MAX_VALUE);

        for (int i = 0; i < nodesCount; i++) {
            if ((roads[0][i] != MAX_VALUE)) {
                distance[i] = roads[0][i];
            }
        }
        final boolean[] visited = new boolean[nodesCount];
        visited[0] = true;
        while (true) {
            int min = MAX_VALUE;
            int pos = 0;
            for (int i = 0; i < nodesCount; i++) {
                if ((!visited[i]) && (min > distance[i])) {
                    min = distance[i];
                    pos = i;
                }
            }
            if (min == MAX_VALUE) {break;}
            visited[pos] = true;
            for (int i = 0; i < nodesCount; i++) {
                if ((distance[i] > distance[pos] + roads[pos][i]) && (roads[pos][i] != MAX_VALUE)) {
                    distance[i] = distance[pos] + roads[pos][i];
                }
            }
        }

        return distance;
    }
    
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            final int nodesNum = s.nextInt();
            final int edgesNum = s.nextInt();
            if (nodesNum == 0) {break;}
            s.nextLine();
            
            final int[][] nodes = new int[nodesNum][nodesNum];
            for (int i = 0; i < nodesNum; i++) {
                for (int j = 0; j < nodesNum; j++) {
                    nodes[i][j] = MAX_VALUE;
                    if (i == j) {
                        nodes[i][j] = 0;
                    }
                }
            }
            final int[][] edges = new int [edgesNum][2];
            for (int i = 0; i < edgesNum; i++) {
                final int a = s.nextInt() - 1;
                final int b = s.nextInt() - 1;
                nodes[a][b] = 1;
                nodes[b][a] = 1;
                edges[i][0] = a;
                edges[i][1] = b;
                s.nextLine();
            }
            
            int[] apples = new int[nodesNum];            
            for (int i = 0; i < nodesNum; i++) {
                apples[i] = s.nextInt();
            }
            s.nextLine();
            
            if (nodesNum == 1) {
                System.out.println("No apple");
                continue;
            }
            
            int maxSum = Integer.MIN_VALUE;
            boolean foundInAllTurn = false;
            for (int turn = 0; turn < edgesNum; turn++) {
                final int[][] nodesTemp = new int[nodesNum][nodesNum];

                for (int i = 0; i < nodesNum; i++) {
                    for (int j = 0; j < nodesNum; j++) {
                        nodesTemp[i][j] = nodes[i][j];
                    }
                }

                nodesTemp[edges[turn][0]][edges[turn][1]] = MAX_VALUE;
                nodesTemp[edges[turn][1]][edges[turn][0]] = MAX_VALUE;
                
                final int[] distance = ShortestPathes(nodesTemp, nodesNum); 

                int sum = 0;
                boolean found = false;
                for (int i = 0; i < nodesNum; i++) {
                    if (distance[i] == MAX_VALUE) {
                        sum += apples[i];
                        found = true;
                    }
                }
                if ((found) && (sum > maxSum)) {
                    maxSum = sum;
                    foundInAllTurn = true;
                }
            }
            
            if (foundInAllTurn) {
                System.out.println(maxSum);
            } else {
                System.out.println("No apple");
            }
        }
        s.close();
    }

}
