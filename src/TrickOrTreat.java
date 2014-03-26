import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileName: TrickOrTreat.java
 * @Description:
 * 
 * @author Xunhu(Tiger) Sun
 *         email: TigerSun86@gmail.com
 * @date Mar 22, 2014 3:23:54 PM
 */
public class TrickOrTreat {
    static class Circle{
        double x = 0;
        double y = 0;
        double r = 0;
        ArrayList<Point2D.Double> edge = new ArrayList<Point2D.Double>();
        public boolean isOut(Point2D.Double p){
            double dis = p.distance(x, y);
            if (Double.compare(r, dis) < 0){
                return true;
            } else  {
                return false;
            }
        }
        public boolean isOnEdge(Point2D.Double p){
            double dis = p.distance(x, y);
            if (Double.compare(r, dis) == 0){
                return true;
            } else  {
                return false;
            }
        }
        public void addP(Point2D.Double p){
            if (isOnEdge(p)){
                edge.add(p);
            }
        }
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int house = s.nextInt();
            if (house == 0) {
                break;
            }
            Circle maxC = new Circle();
            
            ArrayList<Point2D.Double> edge = new ArrayList<Point2D.Double>();
            double cenX = 0;
            double dis = 0;
            for (int i = 0; i < house; i++) {
                final double x = s.nextDouble();
                final double y = s.nextDouble();
                final Point2D.Double p = new Point2D.Double(x, y);
                if (maxC.isOnEdge(p)){
                    maxC.addP(p);
                } else if (maxC.isOut(p)){
                    Circle newMaxC = maxC;
                    for (Point2D.Double oldp: maxC.edge){
                        final double newDis = getMDis(oldp, p);
                        final double newCen = getCen(oldp, p);
                        if (Double.compare(newMaxC.r, newDis)<0){
                            Circle tempC = new Circle();
                            tempC.x = newCen;
                            tempC.r = newDis;
                            tempC.addP(oldp);
                            tempC.addP(p);
                            newMaxC = tempC;
                        } else if (Double.compare(newMaxC.r, newDis)== 0){
                            newMaxC.addP(oldp);
                        }

                        
                        
                    }
                }
                
                if (edge.isEmpty()) {
                    edge.add(p);
                    cenX = x;
                    dis = Math.abs(y);
                } else if (edge.size() == 1) {
                    final Point2D.Double oldP = edge.get(0);
                    final double newCen = getCen(oldP, p);
                    if (Double.isNaN(newCen)){
                        edge =new ArrayList<Point2D.Double>();
                        edge.add(p);
                        cenX = p.x;
                        dis = Math.abs(p.y);
                    } else {
                        final double lb = Math.min(oldP.x, p.x);
                        final double hb = Math.max(oldP.x, p.x);
                        if (Double.compare(newCen, lb) > 0
                                && Double.compare(newCen, hb) < 0) {
                            edge =new ArrayList<Point2D.Double>();
                            edge.add(oldP);
                            edge.add(p);
                            cenX = newCen;
                            dis  = p.distance(cenX, 0);
                        } else if(Double.compare(newCen, oldP.x) == 0){
                            // keep old the same.
                        }  else if(Double.compare(newCen, p.x) == 0){
                            edge =new ArrayList<Point2D.Double>();
                            edge.add(p);
                            cenX = newCen;
                            dis = Math.abs(p.y);
                        }
                    }

                } else {
                    double maxCen =cenX;
                    double maxDis =dis;
                    ArrayList<Point2D.Double> newEdge = new ArrayList<Point2D.Double>();
                    for (Point2D.Double oldp: edge){
                        final double newCen = getCen(oldp, p);
                        final double newDis = Math.max(p.distance(newCen, 0), oldp.distance(newCen, 0));
                        if (Double.compare(maxDis, newDis)<0){
                            maxDis = newDis;
                            maxCen = newCen;
                            newEdge =new ArrayList<Point2D.Double>();
                            newEdge.add(oldp);
                            newEdge.add(p);
                        } else if (Double.compare(maxDis, newDis)==0){
                            newEdge.add(oldp);
                        }
                    }
                    
                }

            }

            System.out.println();
        }
        s.close();
    }

    private static double getCen (Point2D.Double p1, Point2D.Double p2) {
        if (p2.x == p1.x){
            return p1.x;
        }
        double x = p2.y * p2.y - p1.y * p1.y + p2.x * p2.x - p1.x * p1.x;
        return x / (2 * (p2.x - p1.x));
    }
    private static double getMDis (Point2D.Double p1, Point2D.Double p2) {
        double x = getCen(p1,p2);
        return Math.max(p1.distance(x, 0),p2.distance(x, 0));
    }
}
