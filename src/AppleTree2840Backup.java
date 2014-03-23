import java.util.Scanner;

public class AppleTree2840Backup {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            final int nodesNum = s.nextInt();
            final int edgesNum = s.nextInt();
            if (nodesNum == 0) {break;}
            s.nextLine();
            
            int[][] nodes = new int[nodesNum][nodesNum];
            for (int i = 0; i < nodesNum; i++) {
                for (int j = 0; j < nodesNum; j++) {
                    nodes[i][j] = Integer.MAX_VALUE;
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
            
            
            int maxSum = Integer.MIN_VALUE;
            boolean foundInAllTurn = false;
            for (int turn = 0; turn < edgesNum; turn++) {
                final int[][] nodesTemp = new int[nodesNum][nodesNum];

                for (int i = 0; i < nodesNum; i++) {
                    for (int j = 0; j < nodesNum; j++) {
                        nodesTemp[i][j] = nodes[i][j];
                    }
                }
                   

                nodesTemp[edges[turn][0]][edges[turn][1]] = Integer.MAX_VALUE;
                nodesTemp[edges[turn][1]][edges[turn][0]] = Integer.MAX_VALUE;
                
                for (int k = 0; k < nodesNum; k++) {
                    for (int i = 0; i < nodesNum; i++) {
                        for (int j = 0; j < nodesNum; j++) {
                            if ((nodesTemp[i][j] > nodesTemp[i][k] + nodesTemp[k][j]) &&
                                (nodesTemp[i][k] != Integer.MAX_VALUE) && 
                                (nodesTemp[k][j] != Integer.MAX_VALUE)) {
                                nodesTemp[i][j] = nodesTemp[i][k] + nodesTemp[k][j];
                            }
                        }
                    }
                }
                int sum = 0;
                boolean found = false;
                for (int i = 0; i < nodesNum; i++) {
                    if (nodesTemp[0][i] == Integer.MAX_VALUE) {
                        sum += apples[i];
                        found = true;
                    }
                }
                if (found && sum > maxSum) {
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
