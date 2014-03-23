import java.util.Scanner;

public class FollowDirections {
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;

    private static class Ro {
        int dir;
        int x;
        int y;
    }

    private static void turn (Ro ro, int rOrL) {
        ro.dir += rOrL;
        if (ro.dir > WEST) {
            ro.dir = NORTH;
        } else if (ro.dir < NORTH) {
            ro.dir = WEST;
        }
    }

    private static void move (Ro ro, int dis) {
        switch (ro.dir) {
            case NORTH:
                ro.y += dis;
                break;
            case EAST:
                ro.x += dis;
                break;
            case SOUTH:
                ro.y -= dis;
                break;
            case WEST:
                ro.x -= dis;
                break;
            default:
                System.out.println("error");
        }
    }

    public static void main (String[] args) {
        final Ro ro = new Ro();
        ro.dir = NORTH;
        ro.x = 0;
        ro.y = 0;

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            final String input = s.nextLine();
            String[] words = input.split(" ");
            if (words[0].equals("Move")) {
                move(ro, Integer.valueOf(words[1]));
            } else {
                if (words[1].equals("right")) {
                    turn(ro, RIGHT);
                } else {
                    turn(ro, LEFT);
                }
            }
            System.out.printf("%d,%d%n", ro.x, ro.y);
        }
        s.close();
    }

}
