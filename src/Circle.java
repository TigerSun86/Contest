public class Circle{

    double radius;
    
    public Circle( double r)
    {
        radius = r;
    }
    
    public void setRadius(double r)
    {
                radius = r;
    }
    
    public double getRadius()
    {
        return radius;      
    }
    
    public double circumference( )
    {
        return 2 * Math.PI * radius;        
    }
    
    
    public double area()
    {
        
        return Math.PI * radius * radius;
    }
    
}

