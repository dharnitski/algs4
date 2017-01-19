import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
	private SET<Point2D> set;

    // construct an empty set of points
    public KdTree() {
        set = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }
  
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new NullPointerException();
        
        Queue<Point2D> result = new Queue<Point2D>();
        
        for (Point2D point2d : set) {
			if (point2d.y() < rect.ymin())
			  continue;

			//points are sorted by Y coordinate, can skip remaining points after first one with bigger Y found
			if (point2d.y() > rect.ymax())  
				break;
			
			//points are sorted 
			if (point2d.x() >= rect.xmin() && point2d.x() <= rect.xmax())
			    result.enqueue(point2d);
		}
        
        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        
        Point2D nearest = null;
        double distance = Double.MAX_VALUE;
        
        for (Point2D point2d : set) {
		    double current = p.distanceSquaredTo(point2d);
		    if (current < distance)
		    {
		    	nearest = point2d;
		    	distance = current;
		    }
		}
        
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
