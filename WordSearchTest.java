import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordSearchTest {
    private Map<String, Set<String>> wordToFiles;

    @BeforeEach
    public void setUp() {
        wordToFiles = new HashMap<>();
        wordToFiles.put("banana", files("file1.txt", "file2.txt", "file3.txt"));
        wordToFiles.put("apple", files("file2.txt"));
        wordToFiles.put("monkey", files("file1.txt", "file2.txt"));
        wordToFiles.put("cat", files("file1.txt", "file2.txt", "file3.txt"));
        wordToFiles.put("grapefruit", files("file1.txt"));
        wordToFiles.put("peach", files("file3.txt"));
        wordToFiles.put("bear", files("file3.txt"));
        wordToFiles.put("dog", files("file2.txt", "file3.txt"));
    }

    private static Set<String> files(String... names) {
        return new HashSet<>(Arrays.asList(names));
    }

    /* 1. search for single term returns results in alphabetical order */
    @Test
    public void testSingleTerm() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"cat"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("file1.txt", "file2.txt", "file3.txt"));
        
        assertEquals(expected, actual);
    }

    /* 2. all terms contained in one file result */
    @Test
    public void testSingleResult() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"peach", "bear"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Collections.singletonList("file3.txt"));
        
        assertEquals(expected, actual);
    }

    /* 3. terms with dijoint file sets return all items in the sets  */
    @Test
    public void testDisjointSets() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"monkey", "bear"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("file1.txt", "file2.txt", "file3.txt"));
        
        assertEquals(actual, expected);
    }

    /* 4. files matching more search terms come first */
    @Test
    public void testRanking() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"peach", "dog"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("file3.txt", "file2.txt"));
        
        assertEquals(expected, actual);
    }

    /* 5. files with equal match counts are ordered alphabetically */
    @Test
    public void testRankTie() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"cat", "apple"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("file2.txt", "file1.txt", "file3.txt"));

        assertEquals(expected, actual);
    }

    /* 6. unknown terms are ignored, known term results are returned */
    @Test
    public void testMixedTerms() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"aardvark", "monkey"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("file1.txt", "file2.txt"));
        
        assertEquals(expected, actual);
    }

    /* 7. map of unknown terms returns empty list  */
    @Test
    public void testUnknownTerm() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"aardvark"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Collections.emptyList());

        assertEquals(expected, actual);
    }

    /* 8. empty search array returns empty list  */
    @Test
    public void testEmptyArray() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[0], wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Collections.emptyList());

        assertEquals(expected, actual);
    }

    /* 9. null search array returns empty list */
    @Test
    public void testNullArray() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(null, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Collections.emptyList());

        assertEquals(expected, actual);
    }

    /* 10. descending match counts */
    @Test
    public void testFourTerms() {
        ArrayList<String> actual = new ArrayList<>(WordSearch.search(new String[] {"cat", "dog", "bear", "banana"}, wordToFiles));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("file3.txt", "file2.txt", "file1.txt"));

        assertEquals(expected, actual);
    }
}