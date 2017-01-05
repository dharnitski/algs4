public class Board {
    
    
    private int[][] blocks;
    private int dimention;
    private int lastBlock; 
    
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) 
    {
        this.blocks = new int[blocks.length][blocks[0].length]; 
        
        this.dimention = blocks.length;
        lastBlock = dimention * dimention;
        
        for(int i=0; i < blocks.length; i++)
            for(int j=0; j < blocks[i].length; j++)
              this.blocks[i][j]=blocks[i][j];    
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
        for(int i=1; i < blocks.length - 1; i++)
        {
            for(int j=1; j < blocks[i].length - 1; j++)
            {
               if (index != lastBlock)
               {
                   int block = this.blocks[i][j];
                   
                   if (block == 0)
                   {
                       result += dimention - i;
                       result += dimention - j;
                   }
                   else
                   {
                       int goalX = block / dimention;
                       int goalY = block - goalX * dimention;
                   
                       result += Math.abs(goalY - i);
                       result += Math.abs(goalX - i);
                   }
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
        return null;
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
        return null;
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