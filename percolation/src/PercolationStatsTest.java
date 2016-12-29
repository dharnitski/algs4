import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationStatsTest {

    @Test
    public void test() {
        PercolationStats p = new PercolationStats(100, 200);
        assertTrue(p.mean() > 0.5);
        assertTrue(p.mean() < 0.7);
    }

}
