import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void toStringTest() {
        // Arrange
        int[][] data = new int[][]{
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
        
        // Act
        Board sut = new Board(data);
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
        int[][] data = new int[][]{
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
        
        // Act
        Board sut = new Board(data);
        
        // Assert
        assertTrue(sut.isGoal());
    }
    
    @Test
    public void isGoalNegativeTest() {
        // Arrange
        int[][] data = new int[][]{
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 0, 8 }
        };
        
        // Act
        Board sut = new Board(data);
        
        // Assert
        assertFalse(sut.isGoal());
    }

}
