import static org.junit.Assert.*;

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
    public void manhattanTest1() {
        // Arrange
        Board sut = new Board(getPrevious());
        
        // Act
        int actual = sut.manhattan();
        
        // Assert
        assertEquals(1, actual);
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
}
