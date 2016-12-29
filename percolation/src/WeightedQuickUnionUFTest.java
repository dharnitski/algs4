import org.junit.Test;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import static org.junit.Assert.*;


public class WeightedQuickUnionUFTest {

	@Test
	public void test() {
		
		//10
		//4 3
		//3 8
		//6 5
		//9 4
		//2 1
		//8 9
		//5 0
		//7 2
		//6 1
		//1 0
		//6 7
		WeightedQuickUnionUF sut = new WeightedQuickUnionUF(10);
		sut.union(4, 3);
		sut.union(3, 8);
		sut.union(6, 5);
		sut.union(9, 4);
		sut.union(2, 1);
		sut.union(8, 8);
		sut.union(5, 0);
		sut.union(7, 2);
		sut.union(6, 1);
		sut.union(1, 0);
		sut.union(6, 7);
		
		assertTrue("0 and 1", sut.connected(0, 1));
		assertTrue("1 and 2", sut.connected(1, 2));
		assertFalse("2 and 3", sut.connected(2, 3));
		assertTrue("3 and 4", sut.connected(3, 4));
		assertFalse("4 and 5", sut.connected(4, 5));
	}

}
