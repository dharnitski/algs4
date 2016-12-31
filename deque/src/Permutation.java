import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    
    public static void main(String[] args)
    {
        // System.out.println("Working Directory = " +
        //        System.getProperty("user.dir"));
        
        int n = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> items = new RandomizedQueue<String>();
        
       
        //String[] input = "A B C D E F G H I".split(" ");
        //String[] input = "AA BB BB BB BB BB CC CC".split(" ");
        
        // for (String string : input) {
        //     items.enqueue(string);
        //}
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            items.enqueue(item);
        }
        
        for (int i = 0; i < n; i++) {
            StdOut.println(items.dequeue());
        }
        
    }
}
