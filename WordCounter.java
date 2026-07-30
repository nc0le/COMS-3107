import java.util.*;

/*
 * This is the starter code for Part 2.1.
 * 
 * Modify this code so that it uses the Singleton pattern.
 */

public class WordCounter {
	
	private DataSource source;
	
	public WordCounter(DataSource source) { 
		this.source = source;
	}
		
	public Map<String, Integer> countWords() {
		List<String> lines = source.read();
		if (lines == null || lines.isEmpty()) {
			return Collections.EMPTY_MAP;
		}
		Map<String, Integer> map = new TreeMap<>();
		for (String line : lines) {
			String[] words = line.split(" ");
			for (String word : words) {
				word = word.toLowerCase();
				int count = map.getOrDefault(word, 0);
				map.put(word, count + 1);
			}
		}
		return map;
	}

}
