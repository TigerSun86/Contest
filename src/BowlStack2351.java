// didn't sovle
import java.util.Scanner;

public class BowlStack2351 {
    private static final int MAX = Integer.MAX_VALUE;
    private static class Bowl {
        int h;
        int r;
        int R;        
    }
    private static class Graph {
        int vexNumber;
        Bowl[] vex;
        int[][] edges;
        public Graph(int vexNumber) {
            this.vexNumber = vexNumber;
            vex = new Bowl[vexNumber];
            edges = new int[vexNumber][vexNumber];
            for (int i = 0; i < vexNumber; i++) {
                for (int j = 0; j < vexNumber; j++) {
                    edges[i][j] = MAX;
                    if (i == j) {
                        edges[i][j] = 0;
                    }
                }
            }
        }
    }
    
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            int j = s.nextInt();
            s.nextLine();
            Graph graph = new Graph(j);
            for (; j > 0; j--) {
                graph.vex[j].h = s.nextInt();
                graph.vex[j].r = s.nextInt();
                graph.vex[j].R = s.nextInt();
                s.nextLine();
            }
            
            for (int m = 0; m < graph.vexNumber; m++) {
                for (int n = 0; n < graph.vexNumber; n++) {
                    graph.edges[m][n] = calculate(graph, m, n);
                }
            }
            
            System.out.println();
        }
        s.close();
    }

    private static int calculate(Graph graph, int top, int bottom) {
        if (graph.vex[top].r > graph.vex[bottom].r) {
            
        }
        return 0;
    }

}
