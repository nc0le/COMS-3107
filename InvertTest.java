import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class InvertTest {
    /* 1. Each String in the input Map is mapped to a distinct Integer, 
       i.e. all Integer values in the input Map are distinct */
    @Test
    public void testDistinctInteger() {
        Map<String, Integer> input = new HashMap<>();
        input.put("Aardvark", 5);
        input.put("Bobcat", 3);
        input.put("Chipmunk", 7);
        Map<Integer, Set<String>> actual = MapUtils.invert(input);

        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(5, Set.of("Aardvark"));
        expected.put(3, Set.of("Bobcat"));
        expected.put(7, Set.of("Chipmunk"));

        assertEquals(expected, actual);
    }

    /* 2. Some Strings in the input Map are mapped to the same Integer, 
       as in the example above */
    @Test
    public void testSameInteger() {
        Map<String, Integer> input = new HashMap<>();
        input.put("Aardvark", 5);
        input.put("Bobcat", 3);
        input.put("Chipmunk", 5);
        Map<Integer, Set<String>> actual = MapUtils.invert(input);

        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(5, Set.of("Aardvark", "Chipmunk"));
        expected.put(3, Set.of("Bobcat"));

        assertEquals(expected, actual);
    }

    /* 3. The input Map is null; the method should return an empty Map 
       in this case */
    @Test
    public void testNullInput() {
        Map<String, Integer> input = null;
        Map<Integer, Set<String>> actual = MapUtils.invert(input);

        Map<Integer, Set<String>> expected = new HashMap<>();

        assertEquals(expected, actual);
    }

    /* 4. The input Map is empty; the method should return an empty Map in 
       this case as well */
    @Test
    public void testEmptyInput() {
        Map<String, Integer> input = new HashMap<>();
        Map<Integer, Set<String>> actual = MapUtils.invert(input);

        Map<Integer, Set<String>> expected = new HashMap<>();

        assertEquals(expected, actual);
    }

    /* 5. Some Integer values in the input Map are null; these entries 
       should be ignored, i.e. the null values should not be used as keys 
       in the Map that is returned */
    @Test
    public void testNullIntegerValues() {
        Map<String, Integer> input = new HashMap<>();
        input.put("Aardvark", null);
        input.put("Bobcat", 3);
        input.put("Chipmunk", 7);
        Map<Integer, Set<String>> actual = MapUtils.invert(input);

        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(3, Set.of("Bobcat"));
        expected.put(7, Set.of("Chipmunk"));

        assertEquals(expected, actual);
    }
    
}


