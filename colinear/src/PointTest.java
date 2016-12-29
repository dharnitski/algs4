import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PointTest {

    @Parameters()
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { 1, 1, 1, 2, 2}, 
            { -1, 1, 1, 2, 0},
            { 1, 1, 1, 0, 0},
            { 0, 1, 1, 2, 1},
            { Double.POSITIVE_INFINITY, 1, 1, 1, 3},
            { 0.3333333333333333333, 0, 0, 3, 1},
            { 3, 0, 0, 1, 3},
            { -3, 0, 0, -1, 3}
           });
    }
    
    private int x0, y0, x1, y1;
    private double expected;
    
    public PointTest(double expected, int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.expected = expected;
    }
    
    @Test
    public void test() {
        Point p0 = new Point(x0, y0);
        Point p1 = new Point(x1, y1);
        assertEquals(expected, p0.slopeTo(p1), 0.001);
    }

}

