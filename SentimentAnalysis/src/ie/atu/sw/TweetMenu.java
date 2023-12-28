package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TweetMenu extends DataTweetImpl {

    public TweetMenu(Tweeter tweeter) {
        super(tweeter);
    }

    public static void displayTweetContent(Tweeter tweeter) {
    	String directory = "tweets";
    	String tweetFileName = directory + "/" + tweeter.name().toLowerCase() + ".txt"; // Caminho completo para o arquivo
        
        try {
            FileReader fileReader = new FileReader(tweetFileName);
            BufferedReader reader = new BufferedReader(fileReader);
            System.out.println();
            System.out.println("Tweet Content for " + tweeter + ":");
            System.out.println();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            reader.close(); // É sempre bom fechar o BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    
}
