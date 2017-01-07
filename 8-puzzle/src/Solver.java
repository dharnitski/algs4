import java.util.ArrayDeque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    
    private MinPQ<SearchNode> priorityQueue;
    private MinPQ<SearchNode> twinsQueue;
    private SearchNode goal;
    private boolean isSolvable;
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        priorityQueue = new MinPQ<SearchNode>();        
        SearchNode board = new SearchNode(initial, null, 0);
        priorityQueue.insert(board);
        
        twinsQueue = new MinPQ<SearchNode>();
        Board twin = initial.twin();
        SearchNode twinNode = new SearchNode(twin, null, 0);
        twinsQueue.insert(twinNode);
        
        processBoard();
    }
    
    private void processBoard()
    {        
        SearchNode goal; 
        goal = processOneBoard(priorityQueue);
        if (goal != null)
        {
            this.goal = goal;
            isSolvable = true;
            return;
        }
        
        goal = processOneBoard(twinsQueue);
        if (goal != null)
        {
            isSolvable = false;
            return;
        }
        
        processBoard();
    }
    
    private SearchNode processOneBoard(MinPQ<SearchNode> queue)
    {        
        SearchNode board = queue.delMin();
        
        if (board.node.isGoal())
            return board;
        
        Iterable<Board> neighbors = board.node.neighbors();
        for (Board neighbor : neighbors) {
            SearchNode sn = new SearchNode(neighbor, board, board.moves + 1);
            
            // critical optimization
            if (board.parent != null && sn.node.equals(board.parent.node))
                continue;
            
            if (sn.node.isGoal())
                return sn;
                        
            queue.insert(sn);
        }
        
        return null;
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
        return isSolvable;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() 
    {
        if (goal == null)
            return -1;
        
        return goal.moves;
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        ArrayDeque<Board> solution = new ArrayDeque<Board>();
        
        if (!isSolvable)
            return solution;
        
        SearchNode board = goal;
        while (board != null)
        {
            solution.push(board.node);
            board = board.parent;
        }
        return solution;
    }
    
    // solve a slider puzzle
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
    
    private class SearchNode implements Comparable<SearchNode>
    {
        Board node;
        SearchNode parent;
        int moves;
        
        public SearchNode(Board node, SearchNode parent, int moves)
        {
            this.node = node;
            this.parent = parent;
            this.moves = moves;
        }
        
        @Override
        public int compareTo(SearchNode o) {
            
            if (this.node.equals(o.node))
                return 0;
            
            int thisIndex = node.manhattan() + moves;
            int thatIndex = o.node.manhattan() + o.moves;
            
            if (thisIndex > thatIndex)
                return 1;
            
            return -1;
        }
        
    }

}
