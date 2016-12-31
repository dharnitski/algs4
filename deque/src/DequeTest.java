import static org.junit.Assert.*;

import org.junit.Test;

public class DequeTest {

    @Test
    public void test() {
        Deque<String> sut = new Deque<String>();
        sut.addFirst("first");        
        assertEquals(1, sut.size());
        
        sut.addLast("second");
        assertEquals(2, sut.size());
        
        String actual = sut.removeLast();
        assertEquals("second", actual);
        assertEquals(1, sut.size());
        
        actual = sut.removeFirst();
        assertEquals("first", actual);
        assertEquals(0, sut.size());
        
    }
    
    @Test
    public void removeFirstTest() {
        Deque<String> sut = new Deque<String>();
        sut.addFirst("third");        
        sut.addFirst("second");
        sut.addFirst("first");

        assertEquals("first", sut.removeFirst());
        assertEquals("second", sut.removeFirst());
        assertEquals("third", sut.removeFirst());
    }
    
    
    @Test
    public void removeLastTest() {
        Deque<Integer> sut = new Deque<Integer>();
        sut.addLast(0);        
        assertEquals((Integer)0, sut.removeLast());
        sut.addLast(3);
        sut.addLast(5);
        assertEquals((Integer)3, sut.removeFirst());    
    }
    
    @Test
    public void iteratorTest() {
        Deque<Integer> sut = new Deque<Integer>();
        sut.addFirst(1);        
        sut.addFirst(3);
        sut.addFirst(5);
        
        int sum = 0;
        
        for (Integer item : sut) {
            sum = sum + item;
        }
        
        assertEquals(9, sum);    
    }
    
    


}
