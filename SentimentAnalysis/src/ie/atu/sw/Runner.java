package ie.atu.sw;

import java.util.Scanner;

/**
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8 The <b>Runner class</b> manages user inputs and controls the
 *        sentiment analysis system's execution flow.
 */

public class Runner {

	public static int choice; // Represents the user's choice
	public static boolean KeepRunning; // Indicates if the program is running
	public static Tweeter selectedTweet = null; // Holds the selected tweet
	public static String resultTweetParse; // Holds the result of tweet parsing
	public static String result; // Holds the final result

	/**
	 * The <b>main method</b> starts and controls the sentiment analysis system's
	 * execution flow.
	 *
	 * @param args The command line arguments
	 * @throws Exception If an exception occurs during execution
	 */

	public static void main(String[] args) throws Exception {

		Menu mn = new Menu(); // Instantiating a Menu object to manage the program's menu functionalities
		mn.ProgressMeter(); // Initiating and displaying a progress meter, indicating the loading or
							// progress of a process
		Scanner sc = new Scanner(System.in); // Setting up a Scanner to receive user inputs from the console
		boolean keepRunning = true; // Boolean variable to control the program's continuous execution based on user
									// input

		while (keepRunning) { // Entering a loop to continuously display the menu and interact with the user
			mn.Start(); // // Displaying the initial menu options for the user to choose from
			System.out.println();
			System.out.print("Select Option [1-4]> ");
			choice = sc.nextInt(); // Prompting the user for an input choice within a specified range

			switch (choice) {
			// Case 1: Allows the user to choose a tweet
			case 1:
				selectedTweet = getInputForTweet();
				if (selectedTweet != null) {
					DisplayTweet.displayTweetContent(selectedTweet);

				} else {
					System.out.println();
					System.out.println("[error] Invalid Selection");
				}
				break;
			// Case 2: Executes sentiment analysis on the selected tweet
			case 2:
				if (selectedTweet != null) {
					System.out.println(ConsoleColour.YELLOW_BOLD);
					System.out.println();
					System.out.println("The tweet that was chosen is: " + selectedTweet);
					String tweetFileName = "tweets/" + selectedTweet.name().toLowerCase() + ".txt";
					String lexiconFile = "tweets/bingliu.txt";

					VirtualTweeterParser parser = new VirtualTweeterParser(lexiconFile);
					resultTweetParse = parser.processTweetFile(tweetFileName);

				} else {
					System.out.println();
					System.out.println("[error] No tweet selected");
				}
				break;

			// Case 3: Saves the sentiment analysis result to a file

			case 3:
				if (selectedTweet != null) {

					System.out.println("the chosen tweet is: " + selectedTweet);
					System.out.println("The Sentiment Analysis result is stored in a file: resultTweet.txt");

					result = "the chosen tweet is: " + selectedTweet.name() + "\n\n" + resultTweetParse;

					TweetResultOutput.saveResultToFile(result);
				} else {
					System.out.println();
					System.out.println("[error] No tweet selected");
				}
				break;

			// Case 4: Exits the program
			case 4:
				System.out.println("Exiting...");
				keepRunning = false;
				return;

			default:
				System.out.println();
				System.out.println("[error] Invalid Option");
				break;
			}
		}
		sc.close();

	}

	/**
	 * The method <b>getInputForTweet</b> gets input for the selected tweet from the
	 * user.
	 *
	 * @return The selected tweet
	 */

	public static Tweeter getInputForTweet() {

		@SuppressWarnings("resource") // Suppressing warnings related to resource management for the Scanner
		Scanner scanner = new Scanner(System.in);// Initializing a Scanner to read user inputs from the console

		System.out.println();
		System.out.println(ConsoleColour.BLUE_BOLD_BRIGHT);
		System.out.println("Here is a list of tweets:");

		for (Tweeter tweet : Tweeter.values()) { // Iterating through the available tweets and displaying them with
													// their respective indices

			System.out.println("(" + (tweet.ordinal() + 1) + ") " + tweet.name());
		}

		System.out.println();

		System.out.print("Type a number of tweet: "); // Prompting the user to enter a tweet number

		int userChoice = scanner.nextInt(); // Reading the user's choice from the console

		// Validating the user input against the available tweet options and returning
		// the selected tweet
		if (userChoice > 0 && userChoice <= Tweeter.values().length) {
			return Tweeter.values()[userChoice - 1];
		} else {

			return null;
		}
	}
	/**
	 * <b> Big-O notation</b> comments for main loop operates with a time complexity
	 * of O(1) since it processes user input based on a fixed number of menu
	 * options, independent of data structure size.
	 */

	/**
	 * <b> Big-O notation</b> for The method getInputForTweet() is time complexity
	 * is O(n) iterates through the available Tweeter values to display them for
	 * user selection.
	 */
}
