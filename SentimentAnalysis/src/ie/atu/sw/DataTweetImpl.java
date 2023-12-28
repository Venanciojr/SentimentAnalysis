package ie.atu.sw;

import java.io.File;

public abstract class DataTweetImpl implements DataTweet {

	private Tweeter tweeter;
	

	    public DataTweetImpl(Tweeter tweeter) {
	        this.tweeter = tweeter;
	    }

	    public Tweeter getTweetes() {
	        return tweeter;
	    }

	    @Override
	    public void setTweets(File tweets) {
	        // Implementação genérica para receber um arquivo
	        System.out.println("Receiving file for " + tweeter + ": " + tweets.getName());
	        // Aqui você pode adicionar lógica específica para processar o arquivo conforme necessário
	    }
	

}
