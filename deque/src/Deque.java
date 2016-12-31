import java.util.Iterator;
import java.util.*;

public class Deque<Item> implements Iterable<Item> {
   
    private class DequeIterator implements Iterator<Item> {

        public DequeIterator() {
            
        }
    
        public boolean hasNext() {
          return false;
        }
    
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return null;
        }
    
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    private class Node{
        public Item item;
        public Node next;
    }
    
    private Node first = null;
    
    // construct an empty deque
    public Deque()           
    {
       
    }
   
    // is the deque empty?
    public boolean isEmpty()                 
    {
        return true;
    }
   
    // return the number of items on the deque
    public int size()
    {
        return 0;
    }
   
    // add the item to the front
    // Throw a java.lang.NullPointerException if the client attempts to add a null item
    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException("item");
    }
   
    // add the item to the end
    // Throw a java.lang.NullPointerException if the client attempts to add a null item
    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException("item");
    }
   
    // remove and return the item from the front
    // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; 
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("empty");
       
        return null;
    }
   
    // remove and return the item from the end
    // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; 
    public Item removeLast()                 
    {
        if (isEmpty())
            throw new NoSuchElementException("empty");
       
        return null;  
    }
   
   
    public Iterator<Item> iterator()  
    {
        return null;
    }
   
    // unit testing
    public static void main(String[] args)
    {
       
    }
}
