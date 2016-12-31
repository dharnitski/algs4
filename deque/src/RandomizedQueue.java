import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    
    private class RandomizedIterator implements Iterator<Item> {
        
        private Item[] items;
        // index of current item
        private int i = 0;
        
        public RandomizedIterator(Item[] items, int size) {
            this.items = Arrays.copyOf(items, size); 
            StdRandom.shuffle(this.items); 
        }
    
        public boolean hasNext() {
          return i < items.length;
        }
    
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            
            Item item = items[i];
            i++;
            return item;
        }
    
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    private Item[] items;
    private int size = 0;
    
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        items = (Item[]) new Object[1];
    }
    
    // is the queue empty?
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    // return the number of items on the queue
    public int size()
    {
        return size;
    }
    
    
    // add the item
    // Throw a java.lang.NullPointerException if the client attempts to add a null item    
    public void enqueue(Item item)
    {
        if (item == null)
            throw new NullPointerException("item");
        
        if (size >= items.length)
        {
            Item[] newArray = Arrays.copyOf(items, items.length * 2);
            items = newArray;
        }
            
        items[size] = item;
        size++;
    }
    
    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("empty");
        
        // generate random array index
        int i = StdRandom.uniform(size);
        // get random item
        Item item = items[i];
        //put last item to position of random item to fill it gaps
        items[i] = items[size - 1];
        size--;
        
        return item;
    }
    
    // return (but do not remove) a random item
    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException("empty");
        
        int i = StdRandom.uniform(size);
        
        return items[i];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedIterator(items, size);
    }
    
    public static void main(String[] args)   // unit testing
    {
        
    }
}
