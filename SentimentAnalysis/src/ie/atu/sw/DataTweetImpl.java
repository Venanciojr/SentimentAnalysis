package ie.atu.sw;

import java.io.File;

/**
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8
 * 
 *        The {@code DataTweetImpl} <b> abstract class</b> provides an abstract
 *        implementation of the {@code DataTweet} interface. It contains
 *        functionality to set tweet data from a file and manages a specific
 *        Twitter handle.
 */

public abstract class DataTweetImpl implements DataTweet {

	private Twitter twitter;

	/**
	 * Constructs a DataTweetImpl object with a specified Twitter handle.
	 *
	 * @param twitter The Twitter handle associated with this DataTweetImpl
	 *                instance.
	 */

	public DataTweetImpl(Twitter twitter) {
		this.twitter = twitter;
	}

	/**
	 * Retrieves the Twitter handle associated with this instance.
	 *
	 * @return The Twitter handle.
	 */

	public Twitter getTweetes() {
		return twitter;
	}

	/**
	 * Sets the tweet data from a file.
	 * 
	 *
	 * @param tweets The file containing tweet data to be set.
	 */

	@Override
	public void setTweets(File tweets) {

		System.out.println("Receiving file for " + twitter + ": " + tweets.getName());

	}

	/**
	 * <b> Big-O notation</b> Time complexity could be O(n) based on file size.
	 */
}
