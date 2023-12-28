package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

    public static void main(String[] args) {
        
    	System.out.println();
        System.out.println("Here is a list of tweet:");

        for (Tweeter tweet : Tweeter.values()) {
            System.out.println("(" + (tweet.ordinal() + 1) + ") " + tweet.name());
        }

        // Ler a escolha do usuário
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Type a number of tweet: ");
        
        int userChoice = scanner.nextInt();

        if (userChoice > 0 && userChoice <= Tweeter.values().length) {
            Tweeter selectedTweet = Tweeter.values()[userChoice - 1];

            displayTweetContent(selectedTweet);
        } else {
            System.out.println("[error] Invalid Selection");
        }

        scanner.close(); // Fechar o scanner quando não for mais usado
    }
    
}
