import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.*;
import org.junit.jupiter.api.Test;

public class FilterTest {

    /* 1. input List is null */
    @Test 
    public void nullInputList() {
        Source source = () -> Collections.singletonList("a");

        assertThrows(IllegalArgumentException.class, 
            () -> Filter.filter(null, source)); // lambda expressions allows exception to be caught and compared
    }

    /* 2. input Source is null */
    @Test 
    public void nullInputSource() {
        assertThrows(IllegalArgumentException.class, 
            () -> Filter.filter(Collections.singletonList("a"), null));
    }

    /* 3. input List is empty */
    @Test
    public void emptyInputList() {
        Source source = () -> Collections.singletonList("a");

        assertEquals(Collections.emptySet(), Filter.filter(Collections.emptyList(), source));
    }

    /* 4. Source.get() returns an empty list */
    @Test
    public void emptySourceList() {
        Source source = () -> Collections.emptyList();

        assertEquals(Collections.emptySet(), Filter.filter(Collections.singletonList("a"), source));
    }

    /* 5. Source.get() returns null */
    @Test 
    public void nullSourceList() {
        Source source = () -> null;

        assertEquals(Collections.emptySet(), Filter.filter(Collections.singletonList("a"), source));
    }

    /* 6. single string */
    @Test 
    public void singleString() {
        Source source = () -> Arrays.asList("a", "c");

        assertEquals(Collections.singleton("a"), Filter.filter(Collections.singletonList("a"), source));
    }

    /* 7. multiple strings */
    @Test 
    public void multipleStrings() {
        Source source = () -> Arrays.asList("a", "b", "c");

        List<String> inputList = Arrays.asList("a", "b", "d", "e");
        Set<String> expected = new HashSet<>(Arrays.asList("a", "b"));

        assertEquals(expected, Filter.filter(inputList, source));
    }

    /* 8. no strings in common */
    @Test 
    public void noStrings() {
        Source source = () -> Arrays.asList("a", "b", "c");

        List<String> inputList = Arrays.asList("d", "e", "f");

        assertEquals(Collections.emptySet(), Filter.filter(inputList, source));
    }
    
}
