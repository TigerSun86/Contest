import java.util.Arrays;
import java.util.Scanner;


public class TheKthCity {
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
        // get the input
        final Scanner s = new Scanner(System.in);
        while (true){
            final int nodesCount = s.nextInt();
            if (nodesCount == 0) {
                break;
            }

            int[][] roads = new int[nodesCount][nodesCount];
            for (int i = 0; i < nodesCount; i++) {
                for (int j = 0; j < nodesCount; j++) {
                    roads[i][j] = MAX_VALUE;
                    if (i == j) {
                        roads[i][j] = 0;
                    }
                }
            }
            
            final int roadsCount = s.nextInt();
            for (int i = 0; i < roadsCount; i++) {
                final int cityA = s.nextInt();
                final int cityB = s.nextInt();
                final int distance = s.nextInt();
                roads[cityA][cityB] = distance;
                roads[cityB][cityA] = distance;
            }
            
            final int k = s.nextInt();
            
            // for all members, get the nearest route
            final int[] distance = ShortestPathes(roads, nodesCount); 
            
            // sort all the route
            final int[] sortedDistance = distance.clone();
            final int[] sortedIndex = new int[nodesCount];
            for (int i = 0; i < nodesCount; i++) {
                sortedIndex[i] = i;
            }
            for (int i = 0; i < nodesCount - 1; i++) {
                for (int j = 0; j < nodesCount - 1 - i; j++) {
                    if (sortedDistance[j] > sortedDistance[j + 1]){
                        final int temp = sortedDistance[j];
                        sortedDistance[j] = sortedDistance[j + 1];
                        sortedDistance[j + 1] = temp;
                        final int temp2 = sortedIndex[j];
                        sortedIndex[j] = sortedIndex[j + 1];
                        sortedIndex[j + 1] = temp2;
                    }
                }
            }
            // get the kth one
            System.out.println(sortedIndex[k]);
        } 
        s.close();
    }
}