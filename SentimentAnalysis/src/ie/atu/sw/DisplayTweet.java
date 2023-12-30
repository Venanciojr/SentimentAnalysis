package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8
 * 
 *        The {@code DisplayTweet} class <b>extends</b> the
 *        {@code DataTweetImpl} abstract class and provides functionality to
 *        display the content of a tweet file associated with a specific Twitter
 *        handle.
 */

public class DisplayTweet extends DataTweetImpl {
	/**
	 * Constructs a DisplayTweet object with a specified Twitter handle.
	 *
	 * @param twitter The Twitter handle associated with this DisplayTweet instance.
	 */

	public DisplayTweet(Twitter twitter) {
		super(twitter);
	}

	/**
	 * Displays the content of the tweet associated with the provided Twitter
	 * handle.
	 * 
	 *
	 * @param twitter The Twitter handle for which tweet content will be displayed.
	 */

	public static void displayTweetContent(Twitter twitter) {
		String directory = "tweets"; // Directory where tweet files are stored

		// Constructing the file path for the tweet associated with the Twitter handle
		String tweetFileName = directory + "/" + twitter.name().toLowerCase() + ".txt";

		try {
			// Creating FileReader and BufferedReader to read the tweet file
			FileReader fileReader = new FileReader(tweetFileName); // Opening a FileReader for the tweet file
			BufferedReader reader = new BufferedReader(fileReader);// Wrapping the FileReader in a BufferedReader for
																	// efficient reading

			System.out.println();
			System.out.println("Tweet Content for " + twitter + ":");
			System.out.println();
			String line;
			// Reading and printing each line of the tweet file until the end
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			// Closing the BufferedReader to release system resources
			reader.close();

		} catch (IOException e) { // Exception handling in case of issues while reading the tweet file
			e.printStackTrace();
		}
	}

	/**
	 * <b> Big-O notation</b> Complexity Time - O(n) where 'n' is the size of the
	 * tweet file being read.
	 * 
	 */

}
