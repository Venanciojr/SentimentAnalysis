package ie.atu.sw;

import java.util.Scanner;

public class Runner {
	
	   public static int choice;
	   public static boolean KeepRunning;

	public static void main(String[] args) throws Exception {
		
		Menu mn = new Menu();
		mn.ProgressMeter();
		
		
	
		Scanner sc = new Scanner (System.in);
		
		boolean keepRunning = true;
		
		
		while (keepRunning) { // Loop para manter o menu em execução
	        mn.Start();

	        System.out.print("Select Option [1-6]> ");
	        choice = sc.nextInt();

	        switch (choice) {
	            case 1:
	                Tweeter selectedTweet = getInputForTweet();
	                if (selectedTweet != null) {
	                    TweetMenu.displayTweetContent(selectedTweet);
	                } else {
	                	System.out.println();
	                    System.out.println("[error] Invalid Selection");
	                }
	                break;
	            // Adicione outras opções e cases conforme necessário
	            case 2:
	                System.out.println("Exiting..."); // Indica que o usuário está saindo
	                keepRunning = false; // Fecha o scanner antes de sair
	                return; // Encerra o programa
	            

	            default:
	            	System.out.println();
	                System.out.println("[error] Invalid Option");
	                break;
	        }
	    }
		 sc.close();
		
		}
		
	public static Tweeter getInputForTweet()  {
		
		   @SuppressWarnings("resource")
		   Scanner scanner = new Scanner(System.in);

		   System.out.println();
	       System.out.println("Here is a list of tweet:");

	        for (Tweeter tweet : Tweeter.values()) {
	            System.out.println("(" + (tweet.ordinal() + 1) + ") " + tweet.name());
	        }

	        System.out.println();
	        System.out.print("Type a number of tweet: ");
	        
	        int userChoice = scanner.nextInt();

	        // Fechando o scanner após uso

	        if (userChoice > 0 && userChoice <= Tweeter.values().length) {
	            return Tweeter.values()[userChoice - 1];
	        } else {
	        	
	            return null;
	        }
	    }
		
	}
		
		

