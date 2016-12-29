import java.util.Arrays;

public class BruteCollinearPoints {
    
    private int numberOfSegments;
    private LineSegment[] segments;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] input)
    {
        if (input == null)
            throw new java.lang.NullPointerException("input");
        
        for (Point point : input) {
           if (point == null)
               throw new java.lang.NullPointerException("point"); 
        }
        
        segments = new LineSegment[0];
        Point[] points = Arrays.copyOf(input, input.length);
        Arrays.sort(points);
        
        int N = points.length;
        for (int i = 0; i < N; i += 1) {
            Point p = points[i];
            for (int j = i + 1; j < N; j += 1) {
                Point q = points[j];
                if (p.compareTo(q) == 0)
                    throw new java.lang.IllegalArgumentException();
                for (int k = j + 1; k < N; k += 1) {
                    Point r = points[k];
                    for (int l = k + 1; l < N; l += 1) {
                        Point s = points[l];
                            
                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);
                        if (slopePQ == slopePR && slopePQ == slopePS
                                && slopePR == slopePS)
                        {
                            segments = Arrays.copyOf(segments, segments.length + 1);
                            LineSegment segment = new LineSegment(p, s);
                            segments[segments.length - 1] = segment;
                        }                        
                    }
                }
            }
        }   
    }
    
    // the number of line segments
    public int numberOfSegments()
    {
        return segments.length;    
    }
    
    // the line segments
    public LineSegment[] segments()
    {
        return Arrays.copyOf(segments, numberOfSegments());
    }
}
