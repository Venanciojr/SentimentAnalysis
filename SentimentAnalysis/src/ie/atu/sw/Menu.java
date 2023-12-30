package ie.atu.sw;

/**
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8
 * 
 *        The Menu class provides methods to <b> display a menu </b> and show a
 *        progress meter. It uses ANSI escape sequences to control console text
 *        color and provides a method to visualize a progress meter with a given
 *        size.
 */

public class Menu {

	/**
	 * The method <b>Start</b> displays the main menu with options for the sentiment
	 * analysis system.
	 */
	public void Start() {
		System.out.println();
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*             Virtual Threaded Sentiment Analyser          *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Choose a Tweet");
		System.out.println("(2) Execute a Tweet Sentiment Analysis and Generate a Report");
		System.out.println("(3) Tweet analysed Output File (default: ./resultTweet.txt)");
		System.out.println("(4) Quit");

	}

	/**
	 * The method <b>ProgressMeter</b> displays a progress meter in the console
	 * using a loop to simulate a processing sequence.
	 *
	 * @throws Exception if any exception occurs during the meter display
	 */

	public void ProgressMeter() throws Exception {

		System.out.print(ConsoleColour.YELLOW); // Change the colour of the console text
		int size = 100; // The size of the meter. 100 equates to 100%
		for (int i = 0; i < size; i++) { // The loop equates to a sequence of processing steps
			printProgress(i + 1, size); // After each (some) steps, update the progress meter
			Thread.sleep(10); // Slows things down so the animation is visible
		}
	}

	/**
	 * The method <b>printProgressPrints</b> a progress meter showing the completion
	 * percentage.
	 *
	 * @param index the current index in the progress
	 * @param total the total size of the progress
	 */

	public static void printProgress(int index, int total) {
		if (index > total)
			return; // Out of range
		int size = 50; // Must be less than console width
		char done = '█'; // Change to whatever you like.
		char todo = '░'; // Change to whatever you like.

		// Compute basic metrics for the meter
		int complete = (100 * index) / total;
		int completeLen = size * complete / 100;

		/*
		 * A StringBuilder should be used for string concatenation inside a loop.
		 * However, as the number of loop iterations is small, using the "+" operator
		 * may be more efficient as the instructions can be optimized by the compiler.
		 * Either way, the performance overhead will be marginal.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append((i < completeLen) ? done : todo);
		}

		/*
		 * The line feed escape character "\r" returns the cursor to the start of the
		 * current line. Calling print(...) overwrites the existing line and creates the
		 * illusion of an animation.
		 */
		System.out.print("\r" + sb + "] " + complete + "%");

		if (done == total)
			System.out.println("\n");// Once the meter reaches its max, move to a new line.
	}
	/**
	 * <b> Big-O notation</b> comments for the printProgress method Time Complexity: O(N)
	 */
}
