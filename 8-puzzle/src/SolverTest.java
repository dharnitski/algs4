import static org.junit.Assert.*;

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
    
    
    @Test
    public void testPrevious() {
        Solver sut = new Solver(new Board(getSmallPrevious()));   
        
        assertEquals(1, sut.moves()); 
    }
    
    @Test
    public void test2Steps() {
        Solver sut = new Solver(new Board(getSmall2Steps()));   
        
        assertEquals(2, sut.moves()); 
    }
    
    @Test
    public void test3Steps() {
        Solver sut = new Solver(new Board(getSmall3Steps()));   
        
        assertEquals(3, sut.moves()); 
    }

}
