package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualTweeterParser {

    private ConcurrentHashMap<String, Integer> lexicon = new ConcurrentHashMap<>();
    private int positiveCount = 0;
    private int negativeCount = 0;

    public VirtualTweeterParser(String lexiconFile) {
        go(lexiconFile);
    }

    private void go(String lexiconFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(lexiconFile))) {
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

    public String processTweetFile(String tweetFile) {
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
        
        System.out.println();
        System.out.println("Total positive words: " + positiveCount);
        System.out.println("Total negative words: " + negativeCount);
        
        String resultMessage = "";
        
        if (positiveCount > negativeCount) {
            System.out.println("The tweet is positive. \uD83D\uDE00");
            resultMessage = "The tweet is positive. \uD83D\uDE00";
        } else if (negativeCount > positiveCount) {
            System.out.println("The tweet is negative. \u2639\uFE0F");
            resultMessage = "The tweet is negative. \u2639\uFE0F";
        } else {
            System.out.println("The tweet is neutral. \uD83D\uDE10");
            resultMessage = "The tweet is neutral. \uD83D\uDE10";
        }
        
       
        return resultMessage += "\n" + "Total positive words: " + positiveCount + "\n" +
                "Total negative words: " + negativeCount;

    }
    

}
