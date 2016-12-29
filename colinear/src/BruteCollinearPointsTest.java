import static org.junit.Assert.*;

import org.junit.Test;

public class BruteCollinearPointsTest {

    @Test
    public void ShouldFindSegment() {
        // Arrange
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new Point(3, 3);
        // Act
        BruteCollinearPoints sut = new BruteCollinearPoints(points);
        
        // assert
        assertEquals(1, sut.numberOfSegments());  
        assertEquals(1, sut.segments().length); 
        assertEquals("(0, 0) -> (3, 3)", sut.segments()[0].toString()); 
    }
    
    @Test
    public void ShouldNotFindSegment() {
        // Arrange
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new Point(3, 5);
        // Act
        BruteCollinearPoints sut = new BruteCollinearPoints(points);
        
        // assert
        assertEquals(0, sut.numberOfSegments());  
        assertEquals(0, sut.segments().length); 
    }

}
