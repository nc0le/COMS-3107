import java.io.*;
import java.util.*;

/*
 * Implements a text search engine for a collection of documents in the same directory.
 */

public class WordSearch {
	
	public static Map<String, Set<String>> buildMap(String dirName) {
		File dir = new File(dirName);	// create a File object for this directory
		
		// make sure it exists and is actually a directory
		if (dir.exists() == false || dir.isDirectory() == false) {
            // this tells the caller "you gave me bad input"
			throw new IllegalArgumentException(dirName + " does not exist or is not a directory");
		}
		
		File[] files = dir.listFiles();		// get the Files in the specified directory

		Map<String, Set<String>> map = new HashMap<>();

		for (File file : files) {

			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNext()) {
					String word = scanner.next().toLowerCase();

					if (!map.containsKey(word)) {
						map.put(word, new HashSet<>());
					}
					map.get(word).add(file.getName());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace(); 
			}
		}

		return map;
	}
	
	public static List<String> search(String[] terms, Map<String, Set<String>> map) {
		ArrayList<String> result = new ArrayList<>();
		Map<String, Integer> countMap = new HashMap<>();

		for (String term : terms) {
			term = term.toLowerCase();

			if (map.containsKey(term)) {
				HashSet<String> files = (HashSet) map.get(term);

				for (String file : files) {
					countMap.put(file, countMap.getOrDefault(file, 0) + 1);
				}
			}
		}

		List<Map.Entry<String, Integer>> fileCounts = new ArrayList<>(countMap.entrySet());

		fileCounts.sort((entry1, entry2) -> {
			int countComparison = entry2.getValue().compareTo(entry1.getValue());
			if (countComparison == 0) {
				return entry1.getKey().compareTo(entry2.getKey());
			}
			return countComparison;
		});

		for (Map.Entry<String, Integer> file : fileCounts) {
			result.add(file.getKey());
		}
		return result;
	}
	
	public static void main(String[] args) {
		Map<String, Set<String>> map = buildMap(args[0]);
		System.out.println(map); 					// for debugging purposes
		
		System.out.print("Enter a term to search for: ");
		
		try (Scanner in = new Scanner(System.in)) { // create a Scanner to read from stdin
			String input = in.nextLine();			// read the entire line that was entered
			String[] terms = input.split(" ");		// separate tokens based on a single whitespace
			List<String> list = search(terms, map);	// search for the tokens in the Map
			for (String file : list) {				// print the results
				System.out.println(file);
			}
		}
		catch (Exception e) {
			// oops! something went wrong
			e.printStackTrace();
		}
	}

}
