import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AverageSpeed1376TimeFormat {
    public static void main(String[] args) throws ParseException {
        Scanner s = new Scanner(System.in);
        long lastSec = 0;
        long lastSpeed = 0;
        double lastKm = 0;
        while (s.hasNext()) {
            final String[] str = s.nextLine().split(" ");
            SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
            final Date d = ft.parse(str[0]);
            final long sec = d.getTime() / 1000;
            lastKm+= (sec - lastSec) * lastSpeed /(3600.0);
            lastSec = sec;
            
            if (str.length == 1) {
                System.out.println(str[0] +" " + String.format("%.2f",lastKm)+" km");
            } else {
                lastSpeed = Long.parseLong(str[1]);
            }

        }
        s.close();
    }

}
