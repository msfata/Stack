import static org.junit.Assert.*;
import org.junit.Test;
import com.google.java.contract.PreconditionError;


public class StackTest {
	private static final Integer[] someInts = new Integer[] { 3, 2, 1 };
	
	@Test
	public void testCtrPost() {
		Stack a = new Stack();
		assertTrue(a.isEmpty());
	}
	
	@Test
	public void testCopyCtrPost() {
		Stack a = new Stack();
		for(int i : someInts) { a.push(i); }
		Stack b = new Stack(a);
		assertEquals(a, b);
	}

	@Test(expected=PreconditionError.class)
	public void testPeekPre() {
		Stack a = new Stack();
		a.peek();
	}

	@Test
	public void testPeekPost() {
		Stack a = new Stack();
		for(int i : someInts) { a.push(i); }
		Stack b = new Stack(a);
		assertEquals(1, a.peek());
		assertEquals(b, a);
	}

	@Test
	public void testLengthPost() {
		Stack a = new Stack();
		for(int i : someInts) { a.push(i); }
		Stack b = new Stack(a);
		assertEquals(3, a.length());
		assertEquals(b, a);
	}

	@Test(expected=PreconditionError.class)
	public void testPopPre() {
		Stack a = new Stack();
		a.pop();
	}

	@Test
	public void testPopPost() {
		Stack a = new Stack();
		for(int i : new Integer[] { 3, 2, 1}) { a.push(i); }
		
		Stack b = new Stack();
		for(int i : new Integer[] { 3, 2}) { b.push(i); }
		
		int i = a.pop();
		
		assertEquals(1, i);
		assertEquals(b, a);
	}
	
	@Test
	public void testPushPost() {
		Stack a = new Stack();
		for(int i : new Integer[] { 3, 2, 1}) { a.push(i); }
		
		Stack b = new Stack();
		for(int i : new Integer[] { 3, 2}) { b.push(i); }
		
		b.push(1);
		
		assertEquals(a, b);
	}
}
