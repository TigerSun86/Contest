import java.util.Scanner;

public class Election1322 {
    private static class Candidate {
        String name;
        String party;
        int votes;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        
        int i = s.nextInt();
        Candidate[] can = new Candidate[i];
        s.nextLine();
        for (; i > 0; i--) {
            can[i - 1] = new Candidate();
            can[i - 1].name = s.nextLine();
            can[i - 1].party = s.nextLine();
            can[i - 1].votes = 0;
        }
        
        i = s.nextInt();
        s.nextLine();
        for (; i > 0; i--) {
            final String temp = s.nextLine();
            for (int j = 0; j < can.length; j++) {
                if (can[j].name.equals(temp)) {
                    can[j].votes++;
                    break;
                }
            }
        }
        int maxIndex = -1;
        int maxVotes = 0;
        boolean tie = false;
        for (int j = 0; j < can.length; j++) {
            if (maxVotes < can[j].votes) {
                maxVotes = can[j].votes;
                maxIndex = j;
                tie = false;
            } else if (maxVotes == can[j].votes) {
                tie = true;
            }
        }
        assert(maxIndex != -1);
        if (tie == true) {
            System.out.println("tie");
        } else {
            System.out.println(can[maxIndex].party);
        }
        
        s.close();
    }

}
