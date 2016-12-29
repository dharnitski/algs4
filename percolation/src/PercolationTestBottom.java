import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class PercolationTestBottom {

    @Parameters(name = "{index}: fib({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { 3, 1, 1}, { 5, 1, 3}, {6, 2, 1}, {7, 2, 2}, { 10, 3, 1 }, { 10, 3, 2 }, { 10, 3, 3 } 
           });
    }

    private int col, row;
    private int expected;

    public PercolationTestBottom(int expected, int row, int col) {
        this.row = row;
        this.col = col;
        this.expected = expected;
    }

    @Test
    public void test() {
        Percolation sut = new Percolation(3);
        //assertEquals(expected, sut.GetBottom(row, col));
    }
}
