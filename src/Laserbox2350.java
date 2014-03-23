import java.util.HashMap;
import java.util.Scanner;

public class Laserbox2350 {
    private static class State extends Object {
        int x;
        int y;
        int direction;
        @Override
        public String toString() {
            return Integer.toString(x)+ "," + Integer.toString(y)+ "," + Integer.toString(direction);
        }
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            final int n = s.nextInt() + 2;
            final int[][] board = new int[n][n];            
            
            int r = s.nextInt();
            s.nextLine();
            for (; r > 0; r--) {
                final int x = s.nextInt();
                final int y = s.nextInt();
                board[x][y] = 1;
                s.nextLine();
            }
            final int lazerX = s.nextInt();
            final int lazerY = s.nextInt();
            final int direction;// 0: up, 1: right, 2: down, 3: left
            if (lazerX == 0) {// right
                direction = 1;
            } else if (lazerX == n - 1) {// left
                direction = 3;
            } else if (lazerY == 0) {// up
                direction = 0;
            } else {// if (lazerY == n - 1) down
                direction = 2;
            }
            State state = new State();
            state.x = lazerX;
            state.y = lazerY;
            state.direction = direction;
            HashMap<String, Integer> hash = new HashMap<String, Integer>();
            
            
            boolean found = true;
            while (found) {
                switch (state.direction) {
                case 0:
                    found = up(board, n, state);
                    break;
                case 1:
                    found = right(board, n, state);
                    break;
                case 2:
                    found = down(board, n, state);
                    break;
                default:
                    found = left(board, n, state);
                    break;
                }
                if (found == true && hash.get(state.toString()) != null) {
                    break;// lazer has been trapped
                } else {
                    hash.put(state.toString(), 1);
                }
            }

            if (found != true) {
                System.out.println(state.x + " " + state.y);
            } else {
                System.out.println("0 0");
            }
            
            s.nextLine();
        }
        s.close();
    }
    private static boolean up(int[][] board, int n, State state) {
        int y = state.y + 1;
        for (; y < n - 1; y++) {
            if (board[state.x][y] == 1) {
                break;
            }
        }
        state.y = y;
        if (y != n - 1) {
            state.direction = (state.direction + 1) % 4; // turn right
            return true;
        } else {
            return false;
        }
    }
    private static boolean right(int[][] board, int n, State state) {
        int x = state.x + 1;
        for (; x < n - 1; x++) {
            if (board[x][state.y] == 1) {
                break;
            }
        }
        state.x = x;
        if (x != n - 1) {
            state.direction = (state.direction + 1) % 4; // turn right
            return true;
        } else {
            return false;
        }
    }
    private static boolean down(int[][] board, int n, State state) {
        int y = state.y - 1; 
        for (; y > 0; y--) {
            if (board[state.x][y] == 1) {
                break;
            }
        }
        state.y = y;
        if (y != 0) {
            state.direction = (state.direction + 1) % 4; // turn right
            return true;
        } else {
            return false;
        }
    }
    private static boolean left(int[][] board, int n, State state) {
        int x = state.x - 1;
        for (; x > 0; x--) {
            if (board[x][state.y] == 1) {
                break;
            }
        }
        state.x = x;
        if (x != 0) {
            state.direction = (state.direction + 1) % 4; // turn right
            return true;
        } else {
            return false;
        }
    }

}
