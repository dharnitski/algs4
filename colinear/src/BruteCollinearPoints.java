import java.util.Arrays;

public class BruteCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new java.lang.NullPointerException("points");
        
        for (Point point : points) {
           if (point == null)
               throw new java.lang.NullPointerException("point"); 
        }
        
        segments = new LineSegment[0];
        
        for (Point p : points) {
            for (Point q : points) {
                double slopePQ = p.slopeTo(q);
                if (p.compareTo(q) > 0)
                    continue;
                for (Point r : points) {
                    double slopeQR = q.slopeTo(r);
                    if (q.compareTo(r) > 0)
                        continue;
                    if (slopePQ == slopeQR)
                    {
                        for (Point s : points) {
                            if (s.compareTo(q) == 0 || s.compareTo(p) == 0 || s.compareTo(p) == 0)
                                continue;
                            double slopeRS = r.slopeTo(s);
                            if (r.compareTo(s) > 0)
                                continue;
                            if (slopePQ == slopeRS)
                            {
                                numberOfSegments++;
                                segments = Arrays.copyOf(segments, segments.length + 1);
                                LineSegment segment = new LineSegment(p, s);
                                segments[segments.length - 1] = segment;
                            }
                        }
                    }
                }
            }
        }   
    }
    
    // the number of line segments
    public int numberOfSegments()
    {
      return numberOfSegments;    
    }
    
    // the line segments
    public LineSegment[] segments()
    {
        return segments;
    }
}
