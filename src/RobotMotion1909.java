import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RobotMotion1909 {
    private static final HashMap<Character, Point> dMap = new HashMap<Character, Point> ();
    static { 
        dMap.put('N', new Point(-1, 0));
        dMap.put('S', new Point(1, 0));
        dMap.put('E', new Point(0, 1));
        dMap.put('W', new Point(0, -1));
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            int r = s.nextInt();
            
            if (r == 0) {break;}
            int c = s.nextInt();
            int start = s.nextInt()-1;
            s.nextLine();
            char[][] map = new char[r][c];
            for (int index = 0; index<r; index++){
                map[index] = new char[c];
                String temp = s.nextLine();
                for (int ic = 0; ic < c; ic++){
                    map[index][ic] = temp.charAt(ic);
                }
            }
            Point st = new Point(0, start);
            ArrayList<Point> pos = new ArrayList<Point>();
            pos.add(st);
            Point cur = st;
            while (true){
                char dir = map[cur.x][cur.y];
                Point action = dMap.get(dir);
                Point next = new Point(cur.x+action.x,cur.y+action.y);
                if (isLegal(next, r, c)){
                    int samePos = -1;
                    for (int i = 0; i < pos.size();i++){
                        if (pos.get(i).equals(next)){
                            samePos = i;
                            break;
                        }
                    }
                    
                    if (samePos != -1){
                        int n1 = samePos;
                        int n2 = pos.size() - samePos;
                        System.out.println(n1+" step(s) before a loop of "+n2+" step(s)");
                        break;
                    } else {
                        pos.add(next);
                        cur = next;
                    }
                } else {
                    System.out.println(pos.size()+ " step(s) to exit");
                    break;
                }
            }
        }
        s.close();
    }

    private static boolean isLegal (Point next, int r, int c) {
        if (next.x <0 || next.x >=r){
            return false;
        } else if (next.y < 0|| next.y >=c){
            return false;
        }
        return true;
    }

}
