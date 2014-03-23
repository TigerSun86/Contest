import java.util.Scanner;

public class TicTacToe1397 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int i = Integer.parseInt(s.nextLine());
        for (; i > 0; i--) {
            char[][] b = new char[3][3];
            int x = 0;
            int o = 0;
            for (int j = 0; j < 3; j++) {
                final String str = s.nextLine();
                b[j] = new char[3];
                for (int k = 0; k < 3; k++) {
                    if (str.charAt(k) == 'X') {
                        b[j][k] = 'X';
                        x++;
                    } else if (str.charAt(k) == 'O') {
                        b[j][k] = 'O';
                        o++;
                    } else {
                        b[j][k] = '.';
                    }
                }
            }
            if (s.hasNextLine()) {
                s.nextLine();
            }

            if (x == o) {
                if (badWinner(b)) {
                    System.out.println("no");
                } else if ('X' == winner(b)) {
                    System.out.println("no");
                } else {
                    System.out.println("yes");
                }
            } else if (x == o + 1){
                if (badWinner(b)) {
                    System.out.println("no");
                } else if ('O' == winner(b)) {
                    System.out.println("no");
                } else {
                    System.out.println("yes");
                }
            } else {
                System.out.println("no");
            }

        }

        s.close();
    }

    private static boolean badWinner(final char[][] b) {
        char winner = 0;
        for (int j = 0; j < 3; j++) {
            int x = 0;
            int o = 0;
            for (int k = 0; k < 3; k++) {
                if (b[j][k] == 'X') {
                    x++;
                } else if (b[j][k] == 'O') {
                    o++;
                }
            }

            if (x == 3) {
                if (winner != 'O') {
                    winner = 'X';
                } else {
                    return true;
                }

            } else if (o == 3) {
                if (winner != 'X') {
                    winner = 'O';
                } else {
                    return true;
                }
            }
        }

        for (int k = 0; k < 3; k++) {
            int x = 0;
            int o = 0;
            for (int j = 0; j < 3; j++) {
                if (b[j][k] == 'X') {
                    x++;
                } else if (b[j][k] == 'O') {
                    o++;
                }
            }

            if (x == 3) {
                if (winner != 'O') {
                    winner = 'X';
                } else {
                    return true;
                }
            } else if (o == 3) {
                if (winner != 'X') {
                    winner = 'O';
                } else {
                    return true;
                }
            }
        }
        /*
         * int j=0 ,k=0; int x = 0; int o = 0; while(j<3&& k<3){ if (b[j][k] ==
         * 'X') { x++; } else if (b[j][k] == 'O'){ o++; } j++;k++; } if (x== 3)
         * { if (winner != 'O') { winner ='X'; } else { return true; } } else if
         * (o ==3){ if (winner != 'X') { winner ='O'; } else { return true; } }
         * 
         * j=0 ;k=2; x = 0; o = 0; while(j<3&& k>=0){ if (b[j][k] == 'X') { x++;
         * } else if (b[j][k] == 'O'){ o++; } j++;k--; } if (x== 3) { if (winner
         * != 'O') { winner ='X'; } else { return true; } } else if (o ==3){ if
         * (winner != 'X') { winner ='O'; } else { return true; } }
         */
        return false;
    }

    private static char winner(final char[][] b) {
        char winner = 0;
        for (int j = 0; j < 3; j++) {
            int x = 0;
            int o = 0;
            for (int k = 0; k < 3; k++) {
                if (b[j][k] == 'X') {
                    x++;
                } else if (b[j][k] == 'O') {
                    o++;
                }
            }

            if (x == 3) {
                return 'X';

            } else if (o == 3) {
                return 'O';

            }
        }

        for (int k = 0; k < 3; k++) {
            int x = 0;
            int o = 0;
            for (int j = 0; j < 3; j++) {
                if (b[j][k] == 'X') {
                    x++;
                } else if (b[j][k] == 'O') {
                    o++;
                }
            }

            if (x == 3) {
                return 'X';

            } else if (o == 3) {
                return 'O';

            }
        }
        int j = 0, k = 0;
        int x = 0;
        int o = 0;
        while (j < 3 && k < 3) {
            if (b[j][k] == 'X') {
                x++;
            } else if (b[j][k] == 'O') {
                o++;
            }
            j++;
            k++;
        }
        if (x == 3) {
            return 'X';

        } else if (o == 3) {
            return 'O';

        }

        j = 0;
        k = 2;
        x = 0;
        o = 0;
        while (j < 3 && k >= 0) {
            if (b[j][k] == 'X') {
                x++;
            } else if (b[j][k] == 'O') {
                o++;
            }
            j++;
            k--;
        }
        if (x == 3) {
            return 'X';

        } else if (o == 3) {
            return 'O';

        }
        return '.';
    }
}
