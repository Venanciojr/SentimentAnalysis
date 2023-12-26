package ie.atu.sw;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwitterSentimentAnalyzer {
	private Map<Integer, String> subject = new ConcurrentSkipListMap<>();

    public static void main(String[] args) {
        // Assume you have loaded lexicon data into a Map
        Map<String, Integer> lexiconData = Map.of(
                "good", 2,
                "bad", -1
                // Add more sentiment words and their scores here
        );

        // Assume you have loaded Twitter data into a List
        List<String> twitterData = List.of("/twitter/5toSucceed.txt");

        // Create an executor with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Process Twitter data concurrently using CompletableFuture
        CompletableFuture<Integer>[] sentimentTasks = twitterData.stream()
                .map(tweet -> CompletableFuture.supplyAsync(() -> analyzeSentiment(tweet, lexiconData), executor))
                .toArray(CompletableFuture[]::new);

        // Combine all sentiment analysis CompletableFuture objects
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(sentimentTasks);

        // When all tasks are completed, calculate overall sentiment
        allTasks.thenRun(() -> {
            int overallSentiment = calculateOverallSentiment(sentimentTasks);
            System.out.println("Overall sentiment score: " + overallSentiment);
        });

        // Shutdown the executor once all tasks are completed
        allTasks.thenRun(executor::shutdown);
    }

    // Function to analyze sentiment of a tweet using lexicon data
    private static int analyzeSentiment(String tweet, Map<String, Integer> lexicon) {
        // Perform sentiment analysis logic using lexicon
        // Replace this with your sentiment analysis algorithm
        int sentimentScore = 0;
        for (String word : tweet.split("\\s+")) {
            if (lexicon.containsKey(word)) {
                sentimentScore += lexicon.get(word);
            }
        }
        return sentimentScore;
    }

    // Function to calculate overall sentiment from completed tasks
    private static int calculateOverallSentiment(CompletableFuture<Integer>[] sentimentTasks) {
        int overallSentiment = 0;
        for (CompletableFuture<Integer> task : sentimentTasks) {
            try {
                overallSentiment += task.get(); // Retrieve sentiment score from completed task
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return overallSentiment;
    }
}
