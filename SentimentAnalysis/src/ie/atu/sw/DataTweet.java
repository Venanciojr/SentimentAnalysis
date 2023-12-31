package ie.atu.sw;

import java.io.File;

/**
 * The {@code DataTweet} interface represents a contract for classes handling
 * tweet data. Classes implementing this interface should provide functionality
 * to set tweet data from a file.
 * 
 * @author Venancio Moraes
 * @version 1.0
 * @since 1.8
 * 
 *
 */

public interface DataTweet{
	 /**
     * Sets the tweet data from a file.
     *
     * @param tweets The file containing tweet data to be set.
     */
          
         public void setTweets(File tweets);
         
}
