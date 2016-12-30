import static org.junit.Assert.*;
import org.junit.Test;

public class FastCollinearPointsTest {

    @Test
    public void ShouldFindSegment() {
        // Arrange
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new Point(3, 3);
        // Act
        FastCollinearPoints sut = new FastCollinearPoints(points);
        
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
        FastCollinearPoints sut = new FastCollinearPoints(points);
        
        // assert
        assertEquals(0, sut.numberOfSegments());  
        assertEquals(0, sut.segments().length); 
    }
    
    @Test
    public void ShouldFindFromInput6() {
        // Arrange
        Point[] points = new Point[6];
        points[0] = new Point(19000, 10000);
        points[1] = new Point(18000, 10000);
        points[2] = new Point(32000, 10000);
        points[3] = new Point(21000, 10000);
        points[4] = new Point(1234, 5678);
        points[5] = new Point(14000, 10000); 
        
        // Act
        FastCollinearPoints sut = new FastCollinearPoints(points);
        
        // assert
        assertEquals(1, sut.numberOfSegments());  
        assertEquals(1, sut.segments().length); 
        assertEquals("(14000, 10000) -> (32000, 10000)", sut.segments()[0].toString());  
    }
    
    @Test
    public void ShouldFindFromInput8() {
        // Arrange
        Point[] points = new Point[8];
        points[0] = new Point(10000, 0);
        points[1] = new Point(0, 10000);
        points[2] = new Point(3000, 7000);
        points[3] = new Point(7000, 3000);
        points[4] = new Point(20000, 21000);
        points[5] = new Point(3000, 4000);
        points[6] = new Point(14000, 15000);
        points[7] = new Point(6000, 7000);
        
        // Act
        FastCollinearPoints sut = new FastCollinearPoints(points);
        
        // assert
        assertEquals(2, sut.numberOfSegments());  
        assertEquals(2, sut.segments().length); 
        assertEquals("(10000, 0) -> (0, 10000)", sut.segments()[0].toString()); 
        assertEquals("(3000, 4000) -> (20000, 21000)", sut.segments()[1].toString());
    }
    
    

}
