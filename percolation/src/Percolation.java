import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF data;
	private WeightedQuickUnionUF backwash;
	private int size;
	private boolean[] open;
	private int topIndex, bottomIndex;
	
	// create n-by-n grid, with all sites blocked 
	// The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.
	public Percolation(int n)  
	{
	    if (n <= 0)
  	        throw new IllegalArgumentException("Grid size should be greater than 0");
		
	    size = n;
		data = new WeightedQuickUnionUF(n*n+2);
		// no virtual site in bottom to avoid backwash
		backwash = new WeightedQuickUnionUF(n*n+1);
		
		open = new boolean[n*n+2];
		
		topIndex = n*n;
		bottomIndex = n*n + 1;
		
		open[topIndex] = true;
		open[bottomIndex] = true;
	}
	
    // Corner cases.  By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site: 
    // Throw a java.lang.IndexOutOfBoundsException if any argument to open(), isOpen(), or isFull() is outside its prescribed range. 
	private void verifyRange(int index){
	    if (index <= 0 || index > size) {
            throw new java.lang.IndexOutOfBoundsException("index " + index + " is not between 1 and " + size);
       }
	}
	
	
	   // get index of left site
	   private int GetLeft(int row, int col)
	   {
	       if (col == 1)
	           return -1;
	       else
	           return (row - 1) * size + col - 2;
	   }
	   
	   // get index of left site
       private int GetRight(int row, int col)
       {
           if (col == size)
               return -1;
           else
               return (row - 1) * size + col;
       }
       
       // get index of top site
       private int GetTop(int row, int col)
       {
           if (row == 1)
               //virtual element to connect top row
               return size*size;
           else
               return (row - 2) * size + col - 1;
       }
       
       //get index of bottom site
       private int GetBottom(int row, int col)
       {
           if (row == size)
               // virtual element to connect bottom row
               return size*size+1;
           else
               return row * size + col - 1;
       }
       
       // get index fo bottom 
       private int GetBottomBackwash(int row, int col)
       {
           if (row == size)
               // bottom virtual element not connected
               return -1;
           else
               return row * size + col - 1;
       }
	
       private int GetOpenIndex(int row, int col)
       {
           return (size * (row - 1) ) + col - 1;
       }
       
	   // open site (row, col) if it is not open already
	   public void open(int row, int col)       
	   {
	       verifyRange(row);
	       verifyRange(col);
	       
	       if (isOpen(row, col))
	           return;
	       
	       int currentIndex = GetOpenIndex(row, col);
	       open[currentIndex] = true;
	       
	       int connectedIndex;
	       
	       connectedIndex = GetLeft(row, col);
	       if (connectedIndex >= 0 && open[connectedIndex])
	       {
	           data.union(currentIndex, connectedIndex);
	           backwash.union(currentIndex, connectedIndex);
	       }
	       connectedIndex = GetRight(row, col);
           if (connectedIndex >= 0 && open[connectedIndex])
           {
               data.union(currentIndex, connectedIndex);
               backwash.union(currentIndex, connectedIndex);
           }
           connectedIndex = GetTop(row, col);
           if (connectedIndex >= 0 && open[connectedIndex])
           {
               data.union(currentIndex, connectedIndex);
               backwash.union(currentIndex, connectedIndex);
           }
           
           connectedIndex = GetBottom(row, col);
           if (connectedIndex >= 0 && open[connectedIndex])
               data.union(currentIndex, connectedIndex);
           
           connectedIndex = GetBottomBackwash(row, col);
           if (connectedIndex >= 0 && open[connectedIndex])
               backwash.union(currentIndex, connectedIndex);
           
           
	   }
	   
	   // is site (row, col) open?
	   public boolean isOpen(int row, int col)
	   {
	       verifyRange(row);
           verifyRange(col);
	       
		   return open[GetOpenIndex(row, col)];
	   }
	   
	// is site (row, col) full?
	   public boolean isFull(int row, int col)  
	   {
	       verifyRange(row);
           verifyRange(col);
           
           return backwash.connected(topIndex, GetOpenIndex(row, col));
	   }
	   
	// does the system percolate?
	   public boolean percolates()
	   {
		   return data.connected(topIndex, bottomIndex);
	   }

	// test client (optional)
	   public static void main(String[] args)
	   {
		   
	   }
	}