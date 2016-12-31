import static org.junit.Assert.*;

import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public void test() {
        RandomizedQueue<Integer> sut = new RandomizedQueue<Integer>();
        
        assertTrue(sut.isEmpty());
        
        sut.enqueue(1);
        assertFalse(sut.isEmpty());
        
        assertEquals(1, (int)sut.sample());
        assertFalse(sut.isEmpty());
        
        int actual = 0;
        for (Integer item : sut) {
            actual = actual + item;
        }
        
        assertEquals(1, actual);
        
        assertEquals(1, (int)sut.dequeue());
        assertTrue(sut.isEmpty());
    }

}
