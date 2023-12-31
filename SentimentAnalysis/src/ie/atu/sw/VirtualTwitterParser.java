package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The {@code VirtualTwitterParser} class processes tweet files using a
 * sentiment lexicon to determine their overall sentiment.
 * 
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8
 * 
 * 
 */
public class VirtualTwitterParser {

	/**
	 * 
	 * Data structure to store words and their associated polarities from the
	 * sentiment lexicon <b> ConcurrentHashMap</b> is chosen for thread-safe
	 * operations in a concurrent environment. It allows multiple threads to read
	 * from and write to the map concurrently
	 */
	private ConcurrentHashMap<String, Integer> lexicon = new ConcurrentHashMap<>();

	// Counts for positive and negative words in the tweet file
	private int positiveCount = 0;
	private int negativeCount = 0;

	/**
	 * Constructs a VirtualTwitterParser object and initiates lexicon processing
	 * from the given file.
	 *
	 * @param lexiconFile The file containing the sentiment lexicon.
	 */
	public VirtualTwitterParser(String lexiconFile) {
		go(lexiconFile); // Initiating lexicon processing
	}

	// Method to process the sentiment lexicon file
	private void go(String lexiconFile) {

		try (BufferedReader reader = new BufferedReader(new FileReader(lexiconFile))) {
			String line;
			// Reading each line of the lexicon file and populating the lexicon map
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					int polarity = Integer.parseInt(parts[1]);
					lexicon.put(parts[0].toLowerCase(), polarity);
				}
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Processes the tweet file to determine its sentiment using the lexicon.
	 *
	 * @param tweetFile The file containing the tweet text.
	 * @return A message indicating the sentiment analysis results.
	 */

	public String processTweetFile(String tweetFile) {
		/**
		 * Executor to manage parallel processing using virtual threads
		 * <b>newVirtualThreadPerTaskExecutor()</b> creates an ExecutorService
		 * specifically designed to manage virtual threads that lightweight threads that
		 * leverage the underlying Java Virtual Machine (JVM) for efficient
		 * multitasking.
		 */
		ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

		try (BufferedReader reader = new BufferedReader(new FileReader(tweetFile))) {
			String line;
			// Reading each line of the tweet file
			while ((line = reader.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					executor.execute(() -> {
						// Retrieving polarity of the word from the lexicon
						Integer polarity = lexicon.get(word.toLowerCase());
						if (polarity != null) {
							// Updating positive or negative count based on the word's polarity
							if (polarity == 1) {
								synchronized (this) {
									positiveCount++;
								}
							} else {
								synchronized (this) {
									negativeCount++;
								}
							}

						}

					});

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			executor.close(); // Closing the executor after processing
		}
		// Printing and constructing result message based on positive and negative
		// counts
		System.out.println();
		System.out.println("Total positive words: " + positiveCount);
		System.out.println("Total negative words: " + negativeCount);

		String resultMessage = "";
		// Determining overall sentiment based on counts
		if (positiveCount > negativeCount) {
			System.out.println("The tweet is positive. \uD83D\uDE00");
			resultMessage = "The tweet is positive. \uD83D\uDE00";
		} else if (negativeCount > positiveCount) {
			System.out.println("The tweet is negative. \u2639\uFE0F");
			resultMessage = "The tweet is negative. \u2639\uFE0F";
		} else {
			System.out.println("The tweet is neutral. \uD83D\uDE10");
			resultMessage = "The tweet is neutral. \uD83D\uDE10";
		}

		// Constructing and returning final result message with counts
		return resultMessage += "\n" + "Total positive words: " + positiveCount + "\n" + "Total negative words: "
				+ negativeCount;

	}
	/**
	 * <b> Big-O notation</b> go Method (Lexicon Processing): Time Complexity: O(n)
	 * where 'n' is the number of lines in the lexicon file. <b> Big-O notation</b>
	 * processTweetFile Method (Tweet Processing): Time Complexity: O(k * m) where
	 * 'k' is the average number of words per tweet, and 'm' is the number of unique
	 * words in the lexicon.
	 * 
	 */

}
