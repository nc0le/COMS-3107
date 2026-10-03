import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateSentenceScoreTest {
    private Map<String, Double> wordScores;
    private static final double DELTA = 0.000001;

    @BeforeEach
    public void setUp() {
        wordScores = new HashMap<>();
        wordScores.put("dogs", 2.0);
        wordScores.put("are", 0.0);
        wordScores.put("cute", 1.0);
        wordScores.put("bad", -2.0);
    }

    /* 1. a single known word returns its score */
    @Test
    public void testSingleWord() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "dogs");

        assertEquals(2.0, actual, DELTA);
    }

    /* 2. known words return their average */
    @Test
    public void testAverage() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "dogs are cute");

        assertEquals(1.0, actual, DELTA);
    }

    /* 3. repeated words count each time */
    @Test
    public void testRepeatedWords() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "dogs dogs are cute");

        assertEquals(1.25, actual, DELTA);
    }

    /* 4. average preserves fractional values */
    @Test
    public void testFraction() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "cute are are");

        assertEquals(1.0 / 3.0, actual, DELTA);
    }

    /* 5. uppercase words are converted to lowercase before lookup */
    @Test
    public void testUppercase() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "DOGS");

        assertEquals(2.0, actual, DELTA);
    }

    /* 6. mixed-case words are also converted to lowercase */
    @Test
    public void testMixedCase() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "DoGs ArE CuTe");

        assertEquals(1.0, actual, DELTA);
    }

    /* 7. an unknown word starting with a letter counts as zero */
    @Test
    public void testUnknownWord() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "dogs are funny");

        assertEquals(2.0 / 3.0, actual, DELTA);
    }

    /* 8. sentence containing only unknown words returns zero */
    @Test
    public void testAllUnknown() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "funny animals");

        assertEquals(0.0, actual, DELTA);
    }

    /* 9. words starting with punctuation are excluded from the average */
    @Test
    public void testPunctuation() {
        double actual =
                Analyzer.calculateSentenceScore(wordScores, "dogs are ?smart");

        assertEquals(1.0, actual, DELTA);
    }

    /* 10. words starting with digits are excluded from the average */
    @Test
    public void testDigitPrefix() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "dogs 123 2cute");

        assertEquals(2.0, actual, DELTA);
    }

    /* 11. sentence with no words starting with letters returns zero */
    @Test
    public void testAllIgnored() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "?smart 123 !");

        assertEquals(0.0, actual, DELTA);
    }

    /* 12. null map returns zero */
    @Test
    public void testNullMap() {
        double actual = Analyzer.calculateSentenceScore(null, "dogs are cute");

        assertEquals(0.0, actual, DELTA);
    }

    /* 13. empty map returns zero */
    @Test
    public void testEmptyMap() {
        double actual = Analyzer.calculateSentenceScore(new HashMap<>(), "dogs are cute");

        assertEquals(0.0, actual, DELTA);
    }

    /* 14. null sentence returns zero */
    @Test
    public void testNullSentence() {
        double actual = Analyzer.calculateSentenceScore(wordScores, null);

        assertEquals(0.0, actual, DELTA);
    }

    /* 15. empty sentence returns zero */
    @Test
    public void testEmptySentence() {
        double actual = Analyzer.calculateSentenceScore(wordScores, "");

        assertEquals(0.0, actual, DELTA);
    }

    /* 16. when both inputs are null, returns zero */
    @Test
    public void testBothNull() {
        double actual = Analyzer.calculateSentenceScore(null, null);

        assertEquals(0.0, actual, DELTA);
    }
}