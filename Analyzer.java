
/**
 * @author Nicole Cui
 *
 * This class contains the methods used for conducting a simple sentiment analysis.
 */

import java.util.*;

public class Analyzer {

	/**
	 * This method calculates the weighted average for each word in all the
	 * Sentences.
	 * This method is case-insensitive and all words should be stored in the Map
	 * using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; null if input is null
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		if (sentences == null) {
			return null;
		}
		if (sentences.isEmpty()) {
			return new HashMap<>();
		}

		HashMap<String, Double> wordScores = new HashMap<>();
		HashMap<String, Integer> wordCounts = new HashMap<>();

		for (Sentence sentence : sentences) {
			int score = sentence.getScore();
			String text = sentence.getText();

			if (score < -2 || score > 2) {
				continue;
			}
			if (text == null || text.isEmpty()) {
				continue;
			}

			String[] words = text.toLowerCase().split("\\s+");

			for (String word : words) {
				if (!word.isEmpty() && Character.isLetter(word.charAt(0))) {
					continue;
				}
				wordScores.put(word, wordScores.getOrDefault(word, 0.0) + score);
				wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
			}
		}
		for (Map.Entry<String, Double> entry : wordScores.entrySet()) {
			String word = entry.getKey();
			double totalScore = entry.getValue();
			int count = wordCounts.get(word);
			wordScores.put(word, totalScore / count);
		}

		return wordScores;
	}

	/**
	 * This method determines the sentiment of the input sentence using the average
	 * of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence   Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; null if
	 *         either input is null
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		/*
		 * Implement this method in Step 3
		 */
		return 0;
	}

	/**
	 * Use this main() method for testing your calculateWordScores and
	 * calculateSentenceScore methods with different inputs.
	 * Note that this is _NOT_ the main() method for the whole sentiment analysis
	 * program!
	 * Just use it for testing this class. It is not considered for grading.
	 */
	public static void main(String[] args) {

	}

}
