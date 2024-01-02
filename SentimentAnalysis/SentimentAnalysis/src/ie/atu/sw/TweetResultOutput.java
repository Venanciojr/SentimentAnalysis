package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code TweetResultOutput} class provides functionality to save tweet
 * analysis results to a txt file.
 * 
 * 
 * 
 * @author Venancio Moraes
 * @version 1.0
 * @since 02/01/24
 */
public class TweetResultOutput {
	 /**
     * Default constructor for the {@code TweetResultOutput} class.
     * 
     * <p>This constructor is intentionally empty as there are no specific
     * initialization requirements for the class at this time.
     */
    public TweetResultOutput() {
        // Intentionally empty constructor
    }

    /**
     * Saves the provided result string to a file named "resultTweet.txt".
     *
     * @param result The string containing the tweet analysis result to be saved.
     */
    public static void saveResultToFile(String result) {
        /*
         * The try-catch block writes the provided result string to a file named
         * "resultTweet.txt". If an IOException occurs during the file writing process,
         * it is caught and its stack trace is printed.
         */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultTweet.txt"))) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provides the Big-O notation for the saveResultToFile method.
     * 
     * <p>Time Complexity: O(1) + O(n). O(1) for setting up the BufferedWriter
     * and FileWriter. O(n) where 'n' is the length of the 'result' string for
     * writing it to the file.
     */
}
