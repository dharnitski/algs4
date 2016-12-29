package colinear;

import java.util.Comparator;

public class Point {
    
    private int x;
    private int y;
    
    // constructs the point (x, y)
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public   void draw()   
    {
    }
    
    // draws the line segment from this point to that point
    public   void drawTo(Point that)
    {
    }
    
    // string representation
    public String toString()                           
    {
        return "x:" + x + "; y:" + y;
    }
    
    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that)     
    {
       return 1;  
    }
    
    // the slope between this point and that point
    public  double slopeTo(Point that)
    {
        return 1;   
    }
    
   // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder()
    {
        return null;
    }
}


