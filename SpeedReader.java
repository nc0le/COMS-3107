
/**
 * Program to implement RSVP speed reader using StdDraw library.
 *
 * This assignment originally created by Peter-Michael Osera at University of Pennsylvania.
 * 
 * @author Chris Murphy
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpeedReader {

    /*
     * This method is responsible for updating the text in the window for the speed
     * reader.
     * You will need to change the parameters as you complete this part of the
     * assignment.
     */
    public static void show(String filename, int rate) {

        // this sets up the window... don't forget to call it!
        setup();

        try {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] words = line.split("\\s+");

                for (String word : words) {
                    int strLength = word.length();
                    String midChar = String.valueOf(word.charAt(strLength / 2));

                    while (!StdDraw.isMousePressed()) {
                        StdDraw.pause(10);
                    }

                    // this places the text in the center of the screen
                    // the coordinate (50, 50) is used for the center of the text
                    StdDraw.setPenColor(StdDraw.BLACK);
                    if (strLength % 2 > 0) { // ODD LENGTH
                        StdDraw.text(50, 50, word);
                    } else { // EVEN LENGTH
                        StdDraw.text(46.25, 50, word);
                    }

                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.text(50, 50, midChar);

                    // this displays the text
                    StdDraw.show();

                    // this causes the program to wait for 500ms
                    int delay = 1000 / (rate / 60);
                    StdDraw.pause(delay);

                    // this removes everything that is being displayed
                    StdDraw.clear();
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
            return;
        }
    }

    /*
     * This method sets up the window for the speed reader.
     * You should not need to change anything here!
     * Please speak to the Instructor if you think any change is necessary.
     */
    private static void setup() {
        // this creates a window of 800x600 pixels
        StdDraw.setCanvasSize(800, 400);

        // this sets the scale of the x- and y-axis to be from 0 to 100
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);

        // this enables animation so that things don't appear jittery
        StdDraw.enableDoubleBuffering();

        // this sets the drawing color to black
        StdDraw.setPenColor(StdDraw.BLACK);

        // this sets the text font to be fixed-width
        StdDraw.setFont(new java.awt.Font("COURIER", java.awt.Font.BOLD, 100));
    }

    public static void main(String[] args) {
        // modify this code as needed in order to pass arguments to the show() method
        if (args.length < 2) {
            System.err.println("Please specify the filename and wpm");
            return;
        }

        String filename = args[0];

        int rate;
        try {
            rate = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Please specify a positive wpm");
            return;
        }

        if (rate <= 0) {
            System.err.println("Please specify a positive wpm");
            return;
        }

        show(filename, rate);
    }

}
