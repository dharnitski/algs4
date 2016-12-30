import java.lang.reflect.Array;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private LineSegment[] segments;
    
    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] input)
    {
        if (input == null)
            throw new java.lang.NullPointerException("input");
        
        for (Point point : input) {
           if (point == null)
               throw new java.lang.NullPointerException("point"); 
        }
        
        Arrays.sort(input);
        
        if (hasDuplicate(input)) {
            throw new IllegalArgumentException("duplicated input items");
        }
        
        segments = new LineSegment[0];
        Point[] points = Arrays.copyOf(input, input.length);
        
        
        int N = points.length;
        
        for (int i = 0; i < N - 3; i += 1) {
            Arrays.sort(points);
            Point p = points[i];
            
            Arrays.sort(points, p.slopeOrder());
            Point firstAligned = points[1];
            Point lastAligned = firstAligned;
            double lineSlope = p.slopeTo(firstAligned);
            int lineLength = 1;
            
            for (int j = 2; j < N; j += 1) {
                Point q = points[j];
                double currentSlope = p.slopeTo(q);
                
                if (Double.compare(currentSlope, lineSlope) == 0)
                {
                    lineLength++;
                    lastAligned = q;
                }
                
                // end of line
                if (Double.compare(currentSlope, lineSlope) != 0 || j == N - 1)
                {
                    // just found end of line
                    if (lineLength >= 3 && p.compareTo(firstAligned) < 0)
                    {
                        LineSegment segment = new LineSegment(p, lastAligned); 
                        segments = Arrays.copyOf(segments, segments.length + 1);
                        segments[segments.length - 1] = segment;
                    }
                    
                    //reset slope 
                    firstAligned = q;
                    lineSlope = p.slopeTo(q);
                    lineLength = 1;    
                }                                     
            }
        }   
    }
    
    // test the whole array for duplicate points
    private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
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
