/**
 * @author Nicole Cui
 *
 * This class holds the main() method for the sentiment analysis program.
 */
import java.util.*;


public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("no input file");
            return;
        }

        Set<Sentence> sentences = Reader.readFile(args[0]);

        if (sentences == null) {
            System.out.println("bad input file");
            return;
        }
        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a sentence: ");
            String sentence = scanner.nextLine();
            if (sentence.equals("quit")) {
                break;
            }
            double score = Analyzer.calculateSentenceScore(wordScores, sentence);
            System.out.println(score);
        }

        scanner.close();

    }
}
