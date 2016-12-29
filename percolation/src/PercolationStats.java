import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private double[] thresholds;
    
	// perform trials independent experiments on an n-by-n grid
    // The constructor should throw a java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
   public PercolationStats(int n, int trials)    
   {
	   if (n <= 0 || trials <= 0)
	       throw new java.lang.IllegalArgumentException("Illegal arguments");
	   
	   thresholds = new double[trials]; 
	   
	   for (int i = 0; i < thresholds.length; i++) {
          thresholds[i] = getThreshold(n);
       } 
   }
   
   private double getThreshold(int n)
   {
       Percolation p = new Percolation(n);
       while (!p.percolates()) {
         p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
       }
       
       int openCount = 0;
       
       for (int i = 1; i <= n; i++) {
           for (int j = 1; j <= n; j++) {
               if (p.isOpen(i, j))
                   openCount++;
           }
       }
       
       double result = (double)openCount / (n * n);
       return result;
   }
   
// sample mean of percolation threshold
   public double mean()                
   {
       return StdStats.mean(thresholds);
   }
   
// sample standard deviation of percolation threshold
   public double stddev()      
   {
       return StdStats.stddev(thresholds);
   }
   
// low  endpoint of 95% confidence interval
   public double confidenceLo()            
   {
	   return mean() - (1.96 * stddev()) / Math.sqrt(thresholds.length);
   }
   
// high endpoint of 95% confidence interval
   public double confidenceHi()            
   {
       return mean() + (1.96 * stddev()) / Math.sqrt(thresholds.length);
   }

// test client (described below)
   public static void main(String[] args) 
   {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       
       PercolationStats p = new PercolationStats(n, trials);
       StdOut.println("mean                    = " + p.mean());
       StdOut.println("stddev                  = " + p.stddev());
       StdOut.println("95% confidence interval = " + p.confidenceLo() + ", " + p.confidenceHi());
   }
}
