from http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html

### COS 226 Programming Assignment

## 8 Puzzle

Write a program to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.

**The problem.** The [8-puzzle problem](http://en.wikipedia.org/wiki/Fifteen_puzzle) is a puzzle invented and popularized by Noyes Palmer Chapman in the 1870s. It is played on a 3-by-3 grid with 8 square blocks labeled 1 through 8 and a blank square. Your goal is to rearrange the blocks so that they are in order, using as few moves as possible. You are permitted to slide blocks horizontally or vertically into the blank square. The following shows a sequence of legal moves from an _initial board_ (left) to the _goal board_ (right).

<pre>

>  1  3           1     3        1  2  3        1  2  3        1  2  3
>  4  2  5   =>   4  2  5   =>   4     5   =>   4  5      =>   4  5  6
>  7  8  6        7  8  6        7  8  6        7  8  6        7  8 
> 
>  initial        1 left          2 up          5 left          goal

</pre>

**Best-first search.** Now, we describe a solution to the problem that illustrates a general artificial intelligence methodology known as the [A* search algorithm](http://en.wikipedia.org/wiki/A*_search_algorithm). We define a _search node_ of the game to be a board, the number of moves made to reach the board, and the previous search node. First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue. Then, delete from the priority queue the search node with the minimum priority, and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node). Repeat this procedure until the search node dequeued corresponds to a goal board. The success of this approach hinges on the choice of _priority function_ for a search node. We consider two priority functions:

*   _Hamming priority function._ The number of blocks in the wrong position, plus the number of moves made so far to get to the search node. Intuitively, a search node with a small number of blocks in the wrong position is close to the goal, and we prefer a search node that have been reached using a small number of moves.
*   _Manhattan priority function._ The sum of the Manhattan distances (sum of the vertical and horizontal distance) from the blocks to their goal positions, plus the number of moves made so far to get to the search node.

For example, the Hamming and Manhattan priorities of the initial search node below are 5 and 10, respectively.

<pre>

>  8  1  3        1  2  3     1  2  3  4  5  6  7  8    1  2  3  4  5  6  7  8
>  4     2        4  5  6     ----------------------    ----------------------
>  7  6  5        7  8        1  1  0  0  1  1  0  1    1  2  0  0  2  2  0  3
> 
>  initial          goal         Hamming = 5 + 0          Manhattan = 10 + 0

</pre>

We make a key observation: To solve the puzzle from a given search node on the priority queue, the total number of moves we need to make (including those already made) is at least its priority, using either the Hamming or Manhattan priority function. (For Hamming priority, this is true because each block that is out of place must move at least once to reach its goal position. For Manhattan priority, this is true because each block must move its Manhattan distance from its goal position. Note that we do not count the blank square when computing the Hamming or Manhattan priorities.) Consequently, when the goal board is dequeued, we have discovered not only a sequence of moves from the initial board to the goal board, but one that makes the fewest number of moves. (Challenge for the mathematically inclined: prove this fact.)

**A critical optimization.** Best-first search has one annoying feature: search nodes corresponding to the same board are enqueued on the priority queue many times. To reduce unnecessary exploration of useless search nodes, when considering the neighbors of a search node, don't enqueue a neighbor if its board is the same as the board of the previous search node.

<pre>

>  8  1  3       8  1  3       8  1       8  1  3     8  1  3
>  4     2       4  2          4  2  3    4     2     4  2  5
>  7  6  5       7  6  5       7  6  5    7  6  5     7  6
> 
>  previous    search node    neighbor   neighbor    neighbor
>                                       (disallow)

</pre>

**Game tree.** One way to view the computation is as a _game tree_, where each search node is a node in the game tree and the children of a node correspond to its neighboring search nodes. The root of the game tree is the initial search node; the internal nodes have already been processed; the leaf nodes are maintained in a priority queue; at each step, the A* algorithm removes the node with the smallest priority from the priority queue and processes it (by adding its children to both the game tree and the priority queue).

<center>![8puzzle game tree](8puzzle-game-tree.png)</center>

**Detecting unsolvable puzzles.** Not all initial boards can lead to the goal board by a sequence of legal moves, including the two below:

<pre>

>  1  2  3         1  2  3  4
>  4  5  6         5  6  7  8
>  8  7            9 10 11 12
>                 13 15 14 
> unsolvable
>                 unsolvable

</pre>

To detect such situations, use the fact that boards are divided into two equivalence classes with respect to reachability: (i) those that lead to the goal board and (ii) those that lead to the goal board if we modify the initial board by swapping any pair of blocks (the blank square is not a block). (Difficult challenge for the mathematically inclined: prove this fact.) To apply the fact, run the A* algorithm on _two_ puzzle instances—one with the initial board and one with the initial board modified by swapping a pair of blocks—in lockstep (alternating back and forth between exploring search nodes in each of the two game trees). Exactly one of the two will lead to the goal board.

**Board and Solver data types.** Organize your program by creating an immutable data type <tt>Board</tt> with the following API:

> <pre>**public class Board {**
>  **public Board(int[][] blocks)** <font color="gray">// construct a board from an n-by-n array of blocks</font>
>                                            <font color="gray">// (where blocks[i][j] = block in row i, column j)</font>
>  **public int dimension()** <font color="gray">// board dimension n</font>
>  **public int hamming()** <font color="gray">// number of blocks out of place</font>
>  **public int manhattan()** <font color="gray">// sum of Manhattan distances between blocks and goal</font>
>  **public boolean isGoal()** <font color="gray">// is this board the goal board?</font>
>  **public Board twin()** <font color="gray">// a board that is obtained by exchanging any pair of blocks</font>
>  **public boolean equals(Object y)** <font color="gray">// does this board equal y?</font>
>  **public Iterable<Board> neighbors()** <font color="gray">// all neighboring boards</font>
>  **public String toString()** <font color="gray">// string representation of this board (in the output format specified below)</font>
> 
>  **public static void main(String[] args)** <font color="gray">// unit tests (not graded)</font>
> }</pre>

_Corner cases. _ You may assume that the constructor receives an _n_-by-_n_ array containing the _n_<sup>2</sup> integers between 0 and _n_<sup>2</sup> − 1, where 0 represents the blank square.

_Performance requirements. _ Your implementation should support all <tt>Board</tt> methods in time proportional to _n_<sup>2</sup> (or better) in the worst case.

Also, create an immutable data type <tt>Solver</tt> with the following API:

> <pre>**public class Solver {**
>  **public Solver(Board initial)** <font color="gray">// find a solution to the initial board (using the A* algorithm)</font>
>  **public boolean isSolvable()** <font color="gray">// is the initial board solvable?</font>
>  **public int moves()** <font color="gray">// min number of moves to solve initial board; -1 if unsolvable</font>
>  **public Iterable<Board> solution()** <font color="gray">// sequence of boards in a shortest solution; null if unsolvable</font>
>  **public static void main(String[] args)** <font color="gray">// solve a slider puzzle (given below)</font>
> }
> </pre>

To implement the A* algorithm, _you must use [<tt>MinPQ</tt>](http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MinPQ.html) from algs4.jar for the priority queue(s)_.

_Corner cases. _ The constructor should throw a <tt>java.lang.NullPointerException</tt> if passed a null argument.

**Solver test client.** Use the following test client to read a puzzle from a file (specified as a command-line argument) and print the solution to standard output.

> <pre>public static void main(String[] args) {
> 
>     <font color="gray">// create initial board from file</font>
>     In in = new In(args[0]);
>     int n = in.readInt();
>     int[][] blocks = new int[n][n];
>     for (int i = 0; i < n; i++)
>         for (int j = 0; j < n; j++)
>             blocks[i][j] = in.readInt();
>     Board initial = new Board(blocks);
> 
>     <font color="gray">// solve the puzzle</font>
>     Solver solver = new Solver(initial);
> 
>     <font color="gray">// print solution to standard output</font>
>     if (!solver.isSolvable())
>         StdOut.println("No solution possible");
>     else {
>         StdOut.println("Minimum number of moves = " + solver.moves());
>         for (Board board : solver.solution())
>             StdOut.println(board);
>     }
> }
> </pre>

**Input and output formats.** The input and output format for a board is the board dimension _n_ followed by the _n_-by-_n_ initial board, using 0 to represent the blank square. As an example,

<pre>

> **% more puzzle04.txt**
> 3
>  0  1  3
>  4  2  5
>  7  8  6
> 
> % **java Solver puzzle04.txt**
> Minimum number of moves = 4
> 
> 3
>  0  1  3 
>  4  2  5 
>  7  8  6 
> 
> 3
>  1  0  3 
>  4  2  5 
>  7  8  6 
> 
> 3
>  1  2  3 
>  4  0  5 
>  7  8  6 
> 
> 3
>  1  2  3 
>  4  5  0   
>  7  8  6 
> 
> 3
>  1  2  3 
>  4  5  6 
>  7  8  0

</pre>

<pre>

> % **more puzzle3x3-unsolvable.txt**
> 3
>  1  2  3
>  4  5  6
>  8  7  0
> 
> % **java Solver puzzle3x3-unsolvable.txt**
> No solution possible

</pre>

Your program should work correctly for arbitrary _n_-by-_n_ boards (for any 2 ≤ _n_ < 128), even if it is too slow to solve some of them in a reasonable amount of time.

**Deliverables.** Submit only the files <tt>Board.java</tt> and <tt>Solver.java</tt> (with the Manhattan priority). We will supply <tt>algs4.jar</tt>. You may not call any library functions other those in <tt>java.lang</tt>, <tt>java.util</tt>, and <tt>algs4.jar</tt>. You must use [<tt>MinPQ</tt>](http://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MinPQ.html) for the priority queue(s).
