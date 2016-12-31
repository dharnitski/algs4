import java.util.Iterator;
import java.util.*;

public class Deque<Item> implements Iterable<Item> {
   
    private class DequeIterator implements Iterator<Item> {
        
        private Node node;
        
        public DequeIterator(Node first) {
            node = first;
        }
    
        public boolean hasNext() {
          return node != null;
        }
    
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            
            Item current = node.item;
            
            node = node.next;
            
            return current;
        }
    
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    private class Node{
        
        public Node(Item item){
            this.item = item;
        }
        
        public Item item;
        public Node next;
        public Node previous;
    }
    
    private Node first = null;
    private Node last = null;
    private int size;
    
    // construct an empty deque
    public Deque()           
    {
       
    }
   
    // is the deque empty?
    public boolean isEmpty()                 
    {
        return size == 0;
    }
   
    // return the number of items on the deque
    public int size()
    {
        return size;
    }
   
    // add the item to the front
    // Throw a java.lang.NullPointerException if the client attempts to add a null item
    public void addFirst(Item item)
    {
        if (item == null)
            throw new NullPointerException("item");
        
        Node newNode = new Node(item);

        newNode.next = first;
        connectNodes(newNode, first);
        first = newNode;
        
        if (last == null)
          last = first;
        
        size++;
    }
    
    private void connectNodes(Node previous, Node next)
    {
        if (previous != null)
            previous.next = next;
        
        if (next != null)
            next.previous = previous;
    }
   
    // add the item to the end
    // Throw a java.lang.NullPointerException if the client attempts to add a null item
    public void addLast(Item item)
    {
        if (item == null)
            throw new NullPointerException("item");
        
        Node newNode = new Node(item);
        connectNodes(last, newNode);
        last = newNode;
        
        if (first == null)
            first = last;
 
        size++;
    }
   
    // remove and return the item from the front
    // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; 
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("empty");
       
        Node temp = first;
        first = temp.next;
        if (first != null)
        {
            first.previous = null;
        }
        else
        {
            last = null;
        }
        size--;
        return temp.item;
    }
   
    // remove and return the item from the end
    // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; 
    public Item removeLast()                 
    {
        if (isEmpty())
            throw new NoSuchElementException("empty");
       
        Node temp = last;
        last = temp.previous;
        if (last != null)
        {
            last.next = null;    
        }
        else
        {
            first = null;
        }
        size--;
        return temp.item; 
    }
   
   
    public Iterator<Item> iterator()  
    {
        return new DequeIterator(first);
    }
   
    // unit testing
    public static void main(String[] args)
    {
       
    }
}
