import java.util.PriorityQueue;
import java.util.Scanner;

public class TheDoctorsParty {
    static class Time implements Comparable<Time>{
        public int t;
        public boolean isCome;
        @Override
        public int compareTo(Time o) {
            if (this.t - o.t == 0){
                // come first.
                if (this.isCome){
                    return -1;
                } else if (o.isCome) {
                    return 1;
                }
            }
            return this.t - o.t;
        }
        
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int testCase = s.nextInt();
        s.nextLine();
        for (; testCase > 0; testCase--) {
            int num = s.nextInt();
            s.nextLine();
            final PriorityQueue<Time> q = new PriorityQueue<Time>();
            for (int gu = 0; gu< num; gu++){
                final Time come = new Time();
                come.t = s.nextInt();
                come.isCome = true;
                final Time go = new Time();
                go.t = s.nextInt() + come.t;
                go.isCome = false;
                s.nextLine();
                //if (go.t != come.t){ //skip zero stay
                    q.add(come);
                    q.add(go);
                //}
            }
            
            int count = 0;
            int max = 0;
            while (!q.isEmpty()){
                final Time time = q.remove();
                if (time.isCome){
                    count++;
                    if (count > max){
                        max = count;
                    }
                } else {
                    count--;
                }
            }
            
            System.out.println(max);
        }
        s.close();
    }

}
