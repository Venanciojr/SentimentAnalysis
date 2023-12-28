package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualTweeterParser {

    private static final String LEXICON_FILE = "tweets/bingliu.txt";

    private ConcurrentHashMap<String, Integer> lexicon = new ConcurrentHashMap<>();
    private int positiveCount = 0;
    private int negativeCount = 0;

    public VirtualTweeterParser() {
        go();
    }

    private void go() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LEXICON_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int polarity = Integer.parseInt(parts[1]);
                    lexicon.put(parts[0].toLowerCase(), polarity);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void process(String tweetFile) {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); // Criando executor de Virtual Threads

        try (BufferedReader reader = new BufferedReader(new FileReader(tweetFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    executor.execute(() -> {
                        Integer polarity = lexicon.get(word.toLowerCase());
                        if (polarity != null) {
                            if (polarity == 1) {
                                synchronized (this) {
                                    positiveCount++;
                                }
                            } else {
                                synchronized (this) {
                                    negativeCount++;
                                }
                            }
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.close(); // Encerrando o executor após o processamento
        }

        System.out.println("Total positive words: " + positiveCount);
        System.out.println("Total negative words: " + negativeCount);

        if (positiveCount > negativeCount) {
            System.out.println("The tweet is positive.");
        } else if (negativeCount > positiveCount) {
            System.out.println("The tweet is negative.");
        } else {
            System.out.println("The tweet is neutral.");
        }
    }

    public static void main(String[] args) {
        new VirtualTweeterParser().process("tweets/DarkPiano.txt");
    }
}
