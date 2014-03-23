import java.util.Scanner;

public class Robotsonagrid {
    private static final char BLOCK = '#';
    public static void main (String[] args) {
        final Scanner s = new Scanner(System.in);
        final int n = s.nextInt();
        char[][] tiles = new char[n][n];
        s.nextLine(); 
        for (int i = 0; i < n; i++) {
            final String line = s.nextLine();
            for (int j = 0; j < n; j++) {
                tiles[i][j] = line.charAt(j);
            }
        }
        long[][] reachable = new long[n][n];
        reachable[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((BLOCK != (tiles[i][j])) && (reachable[i][j] > 0)) {
                    checkNextStep(tiles, reachable, i, j, "right");
                    checkNextStep(tiles, reachable, i, j, "dow.
            System.out.println((reachable[n-1][n-1] % Integer.MAX_VALUE));
        } else {
            char[][] moved = new char[n][n];
            // the x in direction is the row in the graph, so it's the second in
            // the array
            moved[0][0] = 1;
            int x = 0, y = 0;
            makeAMove(tiles, moved, x, y, n, n, "down");
            makeAMove(tiles, moved, x, y, n, n, "right");
            
            if (moved[n-1][n-1] != 0) {
                System.out.println("THE GAME IS A LIE");
            } else {
                System.out.println("INCONCEIVABLE");
            }
        }

        s.close();
    }

    private static void checkNextStep(char[][] tiles, long[][] reachable, int i, int j, String string) {
        final int newI;
        final int newJ;

        if (string.equals("down")) {
            newI = i + 1;
            newJ = j;
        } else { // if (string.equals("right"))
            newI = i;
            newJ = j + 1;
        }
        
        final int n = tiles[0].length;
        // in the bound
        if ((newI >= 0) && (newI < n)  && (newJ >= 0) && (newJ < n)) {
            // have no block
            if ((BLOCK != (tiles[newI][newJ]))) {
                reachable[newI][newJ] += reachable[i][j];
            }
        }        
    }

    private static void makeAMove (char[][] tiles, char[][] moved, int x,
            int y, int w, int h, String string) {
        final int newX;
        final int newY;
        if (string.equals("up")) {
            newX = x - 1;
            newY = y;
        } else if (string.equals("down")) {
            newX = x + 1;
            newY = y;
        } else if (string.equals("left")) {
            newX = x;
            newY = y - 1;
        } else { // if (string.equals("right"))
            newX = x;
            newY = y + 1;
        }

        if ((newX >= 0) && (newX < w)  && (newY >= 0) && (newY < h) &&
                (moved[newX][newY] != 1) && (tiles[newX][newY] != BLOCK)) {
            moved[newX][newY] = 1;
            makeAMove(tiles, moved, newX, newY, w, h, "up");
            makeAMove(tiles, moved, newX, newY, w, h, "down");
            makeAMove(tiles, moved, newX, newY, w, h, "left");
            makeAMove(tiles, moved, newX, newY, w, h, "right");
        }
        return;
    }
}
