package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TweetResultOutput {

    public static void saveResultToFile(String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultTweet.txt"))) {
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
