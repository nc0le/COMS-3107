import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {

    @Test
    public void readReturnsNull() {
        DataSource source = new DataSource("mock") {
            @Override
            public List<String> read() {
                return null;
            }
        };

        WordCounter counter = WordCounter.getInstance();
        counter.setDataSource(source);

        assertEquals(Collections.emptyMap(), counter.countWords());
    }

    @Test
    public void readReturnsEmptyList() {
        DataSource source = new DataSource("mock") {
            @Override
            public List<String> read() {
                return Collections.emptyList();
            }
        };

        WordCounter counter = WordCounter.getInstance();
        counter.setDataSource(source);

        assertEquals(Collections.emptyMap(), counter.countWords());
    }

    @Test
    public void countsMultipleStrings() {
        DataSource source = new DataSource("mock") {
            @Override
            public List<String> read() {
                return List.of("dog cat dog", "dog");
            }
        };

        WordCounter counter = WordCounter.getInstance();
        counter.setDataSource(source);

        assertEquals(
            Map.of("dog", 3, "cat", 1),
            counter.countWords()
        );
    }

    @Test
    public void countsDogRegardlessOfCase() {
        DataSource source = new DataSource("mock") {
            @Override
            public List<String> read() {
                return List.of("dog", "DOG");
            }
        };

        WordCounter counter = WordCounter.getInstance();
        counter.setDataSource(source);

        assertEquals(Map.of("dog", 2), counter.countWords());
    }
}