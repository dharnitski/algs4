import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BoardTest {

    private int[][] getGoal()
    {
        return new int[][]{
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
    }
    
    private int[][] getSmallGoal()
    {
        return new int[][]{
            { 1, 2 },
            { 3, 0 }
        };
    }
    
    private int[][] getSmallPrevious()
    {
        return new int[][]{
            { 1, 2 },
            { 0, 3 }
        };
    }
    
    private int[][] getPrevious()
    {
        return new int[][]{
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 0, 8 }
        };
    }
    
    private int[][] getDistnce()
    {
        return new int[][]{
            { 8, 1, 3 },
            { 4, 0, 2 },
            { 7, 6, 5 }
        };
    }
    
    private int[][] getDistnce2()
    {
        return new int[][]{
            { 0, 1, 3 },
            { 4, 2, 5 },
            { 7, 8, 6 }
        };
    }
    
    @Test
    public void toStringTest() {
        // Arrange        
        // Act
        Board sut = new Board(getGoal());
        String[] lines = sut.toString().split(System.lineSeparator());
        
        // Assert
        assertEquals("3", lines[0]);
        assertEquals("1 2 3 ", lines[1]);
        assertEquals("4 5 6 ", lines[2]);
        assertEquals("7 8 0 ", lines[3]);
    }
    
    @Test
    public void isGoalPositiveTest() {
        // Arrange
        // Act
        Board sut = new Board(getGoal());
        
        // Assert
        assertTrue(sut.isGoal());
    }
    
    @Test
    public void isGoalNegativeTest() {
        // Arrange
        // Act
        Board sut = new Board(getPrevious());
        
        // Assert
        assertFalse(sut.isGoal());
    }
    
    @Test
    public void equalsNegativeTest() {
        // Arrange
        // Act
        Board sut = new Board(getPrevious());
        Board that = new Board(getGoal());
        
        // Assert
        assertFalse(sut.equals(that));
    }
    
    @Test
    public void equalsPositiveTest() {
        // Arrange
        // Act
        Board sut = new Board(getPrevious());
        Board that = new Board(getPrevious());
        
        // Assert
        assertTrue(sut.equals(that));
    }
    
    @Test
    public void hummingGoalTest() {
        // Arrange
        Board sut = new Board(getGoal());
        
        // Act
        int actual = sut.hamming();
        
        // Assert
        assertEquals(0, actual);
    }
    
    @Test
    public void hummingTest1() {
        // Arrange
        Board sut = new Board(getPrevious());
        
        // Act
        int actual = sut.hamming();
        
        // Assert
        assertEquals(1, actual);
    }
    
    @Test
    public void manhattanTest2() {
        // Arrange
        Board sut = new Board(getDistnce());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(10, actual);
    }
    
    @Test
    public void manhattanGoalTest() {
        // Arrange
        Board sut = new Board(getGoal());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(0, actual);
    }
    
    @Test
    public void manhattanSmallGoalTest() {
        // Arrange
        Board sut = new Board(getSmallGoal());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(0, actual);
    }
    
    @Test
    public void manhattanSmallPreviousTest() {
        // Arrange
        Board sut = new Board(getSmallPrevious());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(1, actual);
    }
    
    @Test
    public void manhattanTest1() {
        // Arrange
        Board sut = new Board(getPrevious());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(1, actual);
    }
    
    @Test
    public void manhattanTest3() {
        // Arrange
        Board sut = new Board(getDistnce2());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(4, actual);
    }
    
    @Test
    public void hummingTest2() {
        // Arrange
        Board sut = new Board(getDistnce());
        
        // Act
        int actual = sut.hamming();
        
        // Assert
        assertEquals(5, actual);
    }
    
    @Test
    public void twinTest() {
        // Arrange
        Board sut = new Board(getSmallGoal());
        
        // Act
        Board actual = sut.twin();
        
        // Assert
        assertFalse(actual.isGoal());
    }
    
    @Test
    public void neighborsTest() {
        // Arrange
        Board sut = new Board(getSmallPrevious());
        
        // Act
        List<Board> actual = new ArrayList<>();
        sut.neighbors().iterator().forEachRemaining(actual::add);
        
        // Assert
        assertEquals(2, actual.size());
        
        Board board1 = new Board(new int[][]{
            { 0, 2 },
            { 1, 3 }
        });
        assertEquals(board1, actual.get(0));
        
        Board board2 = new Board(new int[][]{
            { 1, 2 },
            { 3, 0 }
        });
        assertEquals(board2, actual.get(1));
    }
}
