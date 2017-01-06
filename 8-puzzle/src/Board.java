import java.util.ArrayList;

public class Board {
    
    private int[][] blocks;
    private int dimention;
    private int lastBlock; 
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) 
    {
        this.blocks = clone(blocks); 
        
        this.dimention = blocks.length;
        lastBlock = dimention * dimention;   
    }
    
    private int[][] clone(int[][] input)
    {
        int[][] blocks = new int[input.length][input[0].length];
        
        for(int i=0; i < blocks.length; i++)
            for(int j=0; j < blocks[i].length; j++)
              blocks[i][j] = input[i][j];
        return blocks;
    }
    
    // board dimension n
    public int dimension()  
    {
        return dimention;
    }
    
    // number of blocks out of place
    public int hamming()
    {
        int result = 0;
        int i = 1;
        for (int[] line : blocks) {
            for (int block : line) {
                
                if (i != lastBlock)
                {
                    if (block != i)
                        result++; 
                    i++;
                }
            }
        } 
        
        return result;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        int result = 0;
        int index = 1;
        for(int i=0; i < blocks.length; i++)
        {
            for(int j=0; j < blocks[i].length; j++)
            {
               if (index != lastBlock)
               {
                   int block = this.blocks[i][j];
                   
                   if (block == 0)
                       continue;

                   int goalX = (block - 1) / dimention;
                   int goalY = (block - goalX * dimention) - 1;
                   
                   result += Math.abs(goalX - i);
                   result += Math.abs(goalY - j);

                   index++;
               }
            }
        }
         
        return result;
    }
    
    // is this board the goal board?
    public boolean isGoal()
    {
        return hamming() == 0;
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() 
    {
        Board twin = new Board(this.blocks);
        if (twin.blocks[0][0] != 0 && twin.blocks[0][1] != 0)
        {
            swap(twin, 0, 0, 0, 1);
        }
        else
        {
            swap(twin, 1, 0, 1, 1);
        }
        
        return twin;
    }
    
    private void swap(Board board, int i1, int j1, int i2, int j2)
    {
      int temp = board.blocks[i1][j1];
      board.blocks[i1][j1] = board.blocks[i2][j2];
      board.blocks[i2][j2] = temp; 
    }
    
    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == null)
            return false;
        
        if (!(y instanceof Board))
            return false;
        
        Board that = (Board)y;
        
        if (this.dimention != that.dimention)
            return false;
        
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
               if (this.blocks[i][j]!= that.blocks[i][j])
                   return false;
            }
        }
        
        return true;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors()     
    {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        
        int i0 = 0;
        int j0 = 0;
        
        for(int i=0; i < blocks.length; i++)
            for(int j=0; j < blocks[i].length; j++)
            {
                if (blocks[i][j] == 0)
                {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
        
        Board clone;
        
        // move down
        if (i0 != 0)
        {
            clone = new Board(blocks);
            clone.swap(clone, i0, j0, i0 - 1, j0);
            neighbors.add(clone);
        }
        
        // move right
        if (j0 != dimention - 1)
        {
            clone = new Board(blocks);
            clone.swap(clone, i0, j0, i0, j0 + 1);
            neighbors.add(clone);
        }
        
        // move up
        if (i0 != dimention - 1)
        {
            clone = new Board(blocks);
            clone.swap(clone, i0, j0, i0 + 1, j0);
            neighbors.add(clone);
        }
        
        // move left
        if (j0 != 0)
        {
            clone = new Board(blocks);
            clone.swap(clone, i0, j0, i0, j0 - 1);
            neighbors.add(clone);
        }
        
        return neighbors;
        
    }
    
    // string representation of this board (in the output format specified below)
    // 3
    //  1  2  3 
    //  4  5  6 
    //  7  8  0
    public String toString()  
    {
        StringBuilder sb = new StringBuilder();
        sb.append(dimention);
        sb.append(System.lineSeparator());
        
        for (int[] line : blocks) {
            for (int block : line) {
                sb.append(block);
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }
        
        return sb.toString();
    }
    
    // unit tests (not graded)
    public static void main(String[] args) 
    {
        
    }
}