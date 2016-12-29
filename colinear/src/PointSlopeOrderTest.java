import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PointSlopeOrderTest {

    @Parameters()
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { -1, 374, 370, 492, 438}, 
           });
    }
    
    private int x0, y0, x1, y1;
    private int expected;
    
    public PointSlopeOrderTest(int expected, int x0, int y0, int x1, int y1) {
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
        assertEquals(expected, p0.slopeOrder().compare(p0, p1));
    }

}

