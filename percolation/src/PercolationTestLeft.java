import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class PercolationTestLeft {

    @Parameters(name = "{index}: fib({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { -1, 1, 1}, { 0, 1, 2 }, { 1, 1, 3}, {-1, 2, 1}, {3, 2, 2} 
           });
    }

    private int col, row;
    private int expected;

    public PercolationTestLeft(int expected, int row, int col) {
        this.row = row;
        this.col = col;
        this.expected = expected;
    }

    @Test
    public void test() {
        Percolation sut = new Percolation(3);
        //assertEquals(expected, sut.GetLeft(row, col));
    }
}
