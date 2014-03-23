import java.util.Scanner;

public class RedandBlack {
    private static int count = 0;
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            final int w = s.nextInt();
            final int h = s.nextInt();
            if (w == 0) {
                break;
            }
            char[][] tiles = new char[h][w];
            int x = 0, y = 0;
            s.nextLine(); // why need this?
            for (int i = 0; i < h; i++) {
                final String line = s.nextLine();
                for (int j = 0; j < w; j++) {
                    tiles[i][j] = line.charAt(j);
                    if (tiles[i][j] == '@') {
                        y = i;
                        x = j;
                    }
                }
            }
            // 1 represent the tile's already been moved, otherwise is 0
            char[][] moved = new char[h][w];
            // the x in direction is the row in the graph, so it's the second in
            // the array
            moved[y][x] = 1;
            count = 1;
            makeAMove(tiles, moved, x, y, w, h, "up");
            makeAMove(tiles, moved, x, y, w, h, "down");
            makeAMove(tiles, moved, x, y, w, h, "left");
            makeAMove(tiles, moved, x, y, w, h, "right");
            
            System.out.println(count);
        }
        s.close();
    }

    private static void makeAMove (char[][] tiles, char[][] moved, int x,
            int y, int w, int h, String string) {
        final int newX;
        final int newY;
        if (string.equals("up")) {
            newX = x;
            newY = y - 1;
        } else if (string.equals("down")) {
            newX = x;
            newY = y + 1;
        } else if (string.equals("left")) {
            newX = x - 1;
            newY = y;
        } else { // if (string.equals("right"))
            newX = x + 1;
            newY = y;
        }

        if ((newX >= 0) && (newX < w)  && (newY >= 0) && (newY < h) &&
            (moved[newY][newX] != 1) && (tiles[newY][newX] == '.')) {
            moved[newY][newX] = 1;
            count++;
            makeAMove(tiles, moved, newX, newY, w, h, "up");
            makeAMove(tiles, moved, newX, newY, w, h, "down");
            makeAMove(tiles, moved, newX, newY, w, h, "left");
            makeAMove(tiles, moved, newX, newY, w, h, "right");
        }
        return;
    }
}
