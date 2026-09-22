/**
 * @author Nicole Cui
 *
 * This class contains a method for reading from a file and creating Sentence objects
 * for a sentiment analysis program.
 */

import java.io.*;
import java.util.*;

public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file; null if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		if (filename == null) {
			return null;
		}

		Set<Sentence> sentences = new HashSet<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

				try (Scanner lineScanner = new Scanner(line)) {
					if (!lineScanner.hasNextInt()) {
						System.out.println("Missing or invalid score: " + line);
						continue;
					}
					
					int score = lineScanner.nextInt();

					if (score < -2 || score > 2) {
						System.out.println("Score is too large: " + score);
						continue;
					}

					if (!lineScanner.hasNext()) {
						System.out.println("Missing text: " + line);
						continue;
					}
					
					String text = lineScanner.nextLine().trim();

					if (text.isEmpty()) {
						System.out.println("Missing text: " + line);
						continue;
					}
	
					sentences.add(new Sentence(score, text));
				}
			}
		} catch (FileNotFoundException e) {
            return null;
        }
		return sentences;
	}

    /**
     * Use this main() method for testing your Reader.readFile method with different inputs.
     * Note that this is _NOT_ the main() method for the whole sentiment analysis program!
     * Just use it for testing this class. It is not considered for grading.
     */
    public static void main(String[] args) {
		Set<Sentence> sentences = readFile(args[0]);
		for (Sentence sentence : sentences) {
			System.out.println(sentence.getScore() + " " + sentence.getText());
		}
    }
}
