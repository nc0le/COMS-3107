import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

/*
 * These are the tests for Part 3.1.
 * 
 * You should NOT modify these tests.
 */
public class PortfolioTest {

	@Test
	public void testList() {

		String a = "A";
		String b = "B";
		String c = "C";
		Portfolio p = new Portfolio(List.of(a, b, c));
		
		Iterator<String> it = p.iterator();

		assertTrue(it.hasNext());
		assertEquals("A", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("B", it.next());

		assertTrue(it.hasNext());
		assertEquals("C", it.next());

		assertFalse(it.hasNext());		
		
	}
	
	@Test
	public void testArrayOfFiveStrings() {

		String a = "A";
		String b = "B";
		String c = "C";
		String d = "D";
		String e = "E";
		Portfolio p = new Portfolio(new String[] {a, b, c, d, e});
		
		Iterator<String> it = p.iterator();

		assertTrue(it.hasNext());
		assertEquals("A", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("B", it.next());

		assertTrue(it.hasNext());
		assertEquals("C", it.next());

		assertTrue(it.hasNext());
		assertEquals("D", it.next());

		assertTrue(it.hasNext());
		assertEquals("E", it.next());

		assertFalse(it.hasNext());		
		
	}
	
	@Test
	public void testArrayOfTwoStrings() {

		String a = "A";
		String b = "B";
		Portfolio p = new Portfolio(new String[] {a, b});
		
		Iterator<String> it = p.iterator();

		assertTrue(it.hasNext());
		assertEquals("A", it.next());
		
		assertTrue(it.hasNext());
		assertEquals("B", it.next());

		assertFalse(it.hasNext());		
		
	}
	
	@Test
	public void testEmptyArray() {

		Portfolio p = new Portfolio(new String[] {});
		
		Iterator<String> it = p.iterator();

		assertFalse(it.hasNext());		
		
	}

}
