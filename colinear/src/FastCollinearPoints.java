
public class FastCollinearPoints {
    private Point[] points;
    
    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new java.lang.NullPointerException("points");
        
        for (Point point : points) {
           if (point == null)
               throw new java.lang.NullPointerException("point"); 
        }
        
        this.points = points;
    }
    
    // the number of line segments
    public int numberOfSegments()
    {
      return 0;    
    }
    
    // the line segments
    public LineSegment[] segments()
    {
        return null;
    }
}
