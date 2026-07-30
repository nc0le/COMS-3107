import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;


/*
 * These are the tests for Part 1.2.
 * 
 * You should NOT modify this code.
 */

public class CombinerTest {

	@Test
	public void testStringIntMaps() {
		Map<String, Integer> m1 = Map.of("dog", 5, "cat", 2);
		Map<String, Integer> m2 = Map.of("dog", 6, "bear", 4);
		
		Map<String, Set<Integer>> combo = Combiner.combine(m1, m2);
		
		assertEquals(Set.of(5, 6), combo.get("dog"));
		assertEquals(Set.of(2), combo.get("cat"));
		assertEquals(Set.of(4), combo.get("bear"));
		
	}
	
	@Test
	public void testStringStringMaps() {
		Map<String, String> m1 = Map.of("dog", "A", "cat", "B");
		Map<String, String> m2 = Map.of("dog", "C", "bear", "D");
		Map<String, String> m3 = Map.of("dog", "E", "cat", "F");
		
		Map<String, Set<String>> combo = Combiner.combine(m1, m2, m3);
		
		assertEquals(Set.of("A", "C", "E"), combo.get("dog"));
		assertEquals(Set.of("B", "F"), combo.get("cat"));
		assertEquals(Set.of("D"), combo.get("bear"));
		
	}
	
	@Test
	public void testBooleanStringMaps() {
		Map<Boolean, String> m1 = Map.of(true, "A", false, "B");
		Map<Boolean, String> m2 = Map.of(true, "C", false, "D");
		Map<Boolean, String> m3 = Map.of(true, "E");
		Map<Boolean, String> m4 = Map.of(true, "A");
		
		Map<Boolean, Set<String>> combo = Combiner.combine(m1, m2, m3, m4);
		
		assertEquals(Set.of("A", "C", "E"), combo.get(true));
		assertEquals(Set.of("B", "D"), combo.get(false));
		
	}

}
