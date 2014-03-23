import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

public class CuttheCakeSER2013 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while(true) {
            int turn = s.nextInt();
            if (turn == 0) {break;}
            final double r = (double)turn;
            final double px = (double)s.nextInt();
            final double py = (double)s.nextInt();
            final ArrayList<Line2D.Double> linesList = new ArrayList<Line2D.Double>();
            int piecesCount = 1;
            final int n = s.nextInt();
            for (int i = 0; i < n; i++) {
                final double x1 = (double)s.nextInt();
                final double y1 = (double)s.nextInt();
                final Point2D.Double point1 = new Point2D.Double(x1,y1);
                final double x2 = (double)s.nextInt();
                final double y2 = (double)s.nextInt();
                final Point2D.Double point2 = new Point2D.Double(x2,y2);
                final Line2D.Double newLine = new Line2D.Double(point1, point2);
                if (circleLineInter(r, px, py, newLine)){
                    piecesCount++;
                    for (Line2D.Double oldLine: linesList) {
                        if (linesInterInCircle(r, px, py, newLine, oldLine)){
                            piecesCount++;
                        }
                    }
                    linesList.add(newLine);
                }
            }
            System.out.println(piecesCount);
        }
        s.close();
    }
    
    private static boolean circleLineInter(double r, double px, double py, Line2D.Double newLine) {
        if (r > newLine.ptLineDist(px, py)){
            return true;
        } else {
            return false;
        }
    }
    private static Point2D.Double lineLineInter(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        if (((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4)) == 0){
            return null;
        } 
        double x,y;
        x = ((x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
        y = ((x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
        return new Point2D.Double(x,y);
    }
    private static boolean pointInCircle(double r, double px, double py, Point2D.Double point) {
        if (r > point.distance(px, py)) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean linesInterInCircle(double r, double px, double py, Line2D.Double newLine, Line2D.Double oldLine){
        Point2D.Double point = lineLineInter(newLine.getX1(),  newLine.getY1(),  newLine.getX2(),  newLine.getY2(),  oldLine.getX1(),  oldLine.getY1(),  oldLine.getX2(),  oldLine.getY2());
        if (point != null) {
            return pointInCircle(r, px, py, point);
        } else {
            return false;
        }
    }
}
