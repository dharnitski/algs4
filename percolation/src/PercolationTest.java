import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

    @Test
    public void test() {
        Percolation sut = new Percolation(3);
        
        assertFalse(sut.percolates());
        assertFalse(sut.isFull(1, 1));
        assertFalse(sut.isOpen(1, 1));
        
        sut.open(1, 1);
        
        assertFalse(sut.percolates());
        assertTrue(sut.isFull(1, 1));
        assertTrue(sut.isOpen(1, 1));
        
        sut.open(2, 1);
        
        assertFalse(sut.percolates());
        assertTrue(sut.isFull(2, 1));
        assertTrue(sut.isOpen(1, 1));
        
        sut.open(3, 1);
        
        assertTrue(sut.percolates());
        assertTrue(sut.isFull(2, 1));
        assertTrue(sut.isOpen(1, 1));
       
    }

}
