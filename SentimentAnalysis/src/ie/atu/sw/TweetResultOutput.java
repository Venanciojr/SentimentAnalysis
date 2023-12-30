package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8 
 * 
 * The {@code TweetResultOutput} class provides functionality to save
 * tweet analysis results to a txt file.
 */
public class TweetResultOutput {

	/**
	 * Saves the provided result string to a file named "resultTweet.txt".
	 *
	 * @param result The string containing the tweet analysis result to be saved.
	 */
	public static void saveResultToFile(String result) {
		/**
		 * The try-catch block writes the provided result string to a file named
		 * "resultTweet.txt". If an IOException occurs during the file writing process,
		 * it is caught and its stack trace is printed.
		 * 
		 */
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultTweet.txt"))) {
			writer.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * <b> Big-O notation</b> saveResultToFile Method: Time Complexity: O(1) + O(n)
	 * O(1) for setting up the BufferedWriter and FileWriter. O(n) where 'n' is the
	 * length of the 'result' string for writing it to the file.
	 */
}
