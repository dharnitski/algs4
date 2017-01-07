import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SolverTest {

    private int[][] getSmallPrevious()
    {
        return new int[][]{
            { 1, 2 },
            { 0, 3 }
        };
    }
    
    private int[][] getSmall2Steps()
    {
        return new int[][]{
            { 0, 2 },
            { 1, 3 }
        };
    }
    
    private int[][] getSmall3Steps()
    {
        return new int[][]{
            { 2, 0 },
            { 1, 3 }
        };
    }
    
    private int[][] getUnsolvable()
    {
        return new int[][]{
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 8, 7, 0 }
        };
    }
    
    
    @Test
    public void testPrevious() {
        Solver sut = new Solver(new Board(getSmallPrevious()));   
        
        assertEquals(1, sut.moves()); 
        List<Board> actual = new ArrayList<>();
        sut.solution().iterator().forEachRemaining(actual::add);
        assertEquals(2, actual.size());
    }
    
    @Test
    public void test2Steps() {
        Solver sut = new Solver(new Board(getSmall2Steps()));   
        
        assertEquals(2, sut.moves());
        List<Board> actual = new ArrayList<>();
        sut.solution().iterator().forEachRemaining(actual::add);
        assertEquals(3, actual.size());
    }
    
    @Test
    public void test3Steps() {
        Solver sut = new Solver(new Board(getSmall3Steps()));   
        
        assertEquals(3, sut.moves());
        List<Board> actual = new ArrayList<>();
        sut.solution().iterator().forEachRemaining(actual::add);
        assertEquals(4, actual.size());
    }
    
    @Test
    public void testUnsolvable() {
        Solver sut = new Solver(new Board(getUnsolvable()));   
        
        assertEquals(-1, sut.moves());
    }

}
