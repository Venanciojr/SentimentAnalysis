package ie.atu.sw;



public abstract class DataTweetImpl implements DataTweet {

	Tweeter tweets;
	
	public void setTweets(String tweets) {
		this.tweets = Tweeter.valueOf(tweets);
		
	}

	@Override
	public String getTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void optionTweet() {
		
		
	}

	@Override
	public boolean wrongTweet(Tweeter tweets) {
		// TODO Auto-generated method stub
		return false;
	}
    
	

}
