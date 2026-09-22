
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
				if (word.isEmpty() || !Character.isLetter(word.charAt(0))) {
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
	private static void check(boolean condition, String testName) {
		if (condition) {
			System.out.println("PASS: " + testName);
		} else {
			System.out.println("FAIL: " + testName);
		}
	}
	
	private static void checkScore(
			Map<String, Double> scores,
			String word,
			double expected) {
	
		Double actual = scores.get(word);
	
		check(
			actual != null && Math.abs(actual - expected) < 0.0001,
			word + " expected " + expected + ", got " + actual
		);
	}
	
	public static void main(String[] args) {
		// 1. Null input should return null
		check(
			calculateWordScores(null) == null,
			"null input"
		);
	
		// 2. Empty set should return an empty, non-null map
		Set<Sentence> emptySet = new HashSet<>();
		Map<String, Double> emptyScores =
				calculateWordScores(emptySet);
	
		check(
			emptyScores != null && emptyScores.isEmpty(),
			"empty input set"
		);
	
		// 3. Basic score
		Set<Sentence> basicSentences = new HashSet<>();
		basicSentences.add(new Sentence(2, "I like dogs"));
	
		Map<String, Double> basicScores =
				calculateWordScores(basicSentences);
	
		checkScore(basicScores, "dogs", 2.0);
	
		// 4. Weighted averages and repeated words
		Set<Sentence> weightedSentences = new HashSet<>();
	
		weightedSentences.add(new Sentence(
			2,
			"I like cake and could eat cake all day ."
		));
	
		weightedSentences.add(new Sentence(
			1,
			"I hope the dog does not eat my cake ."
		));
	
		Map<String, Double> weightedScores =
				calculateWordScores(weightedSentences);
	
		checkScore(weightedScores, "dog", 1.0);
		checkScore(weightedScores, "eat", 1.5);
		checkScore(weightedScores, "cake", 5.0 / 3.0);
	
		// 5. Case-insensitivity
		Set<Sentence> caseSentences = new HashSet<>();
		caseSentences.add(new Sentence(2, "Dog DOG dog"));
	
		Map<String, Double> caseScores =
				calculateWordScores(caseSentences);
	
		checkScore(caseScores, "dog", 2.0);
	
		check(
			!caseScores.containsKey("Dog") &&
			!caseScores.containsKey("DOG"),
			"words are stored only in lowercase"
		);
	
		// 6. Tokens not starting with letters should be ignored
		Set<Sentence> punctuationSentences = new HashSet<>();
		punctuationSentences.add(
			new Sentence(1, "It 's fun . !invalid")
		);
	
		Map<String, Double> punctuationScores =
				calculateWordScores(punctuationSentences);
	
		checkScore(punctuationScores, "it", 1.0);
		checkScore(punctuationScores, "fun", 1.0);
	
		check(
			!punctuationScores.containsKey("'s") &&
			!punctuationScores.containsKey(".") &&
			!punctuationScores.containsKey("!invalid"),
			"tokens not starting with letters are ignored"
		);
	
		// 7. Invalid Sentence objects should be ignored
		Set<Sentence> invalidSentences = new HashSet<>();
	
		invalidSentences.add(new Sentence(1, "valid sentence"));
		invalidSentences.add(new Sentence(3, "score too high"));
		invalidSentences.add(new Sentence(-3, "score too low"));
		invalidSentences.add(new Sentence(1, null));
		invalidSentences.add(new Sentence(1, ""));
	
		Map<String, Double> invalidScores =
				calculateWordScores(invalidSentences);
	
		checkScore(invalidScores, "valid", 1.0);
		checkScore(invalidScores, "sentence", 1.0);
	
		check(
			!invalidScores.containsKey("high") &&
			!invalidScores.containsKey("low"),
			"sentences with invalid scores are ignored"
		);
	
		// 8. Non-empty set containing only invalid Sentences
		Set<Sentence> allInvalid = new HashSet<>();
		allInvalid.add(new Sentence(10, "invalid"));
		allInvalid.add(new Sentence(0, null));
		allInvalid.add(new Sentence(0, ""));
	
		Map<String, Double> allInvalidScores =
				calculateWordScores(allInvalid);
	
		check(
			allInvalidScores != null &&
			allInvalidScores.isEmpty(),
			"all invalid Sentences produce an empty map"
		);
	}

}
