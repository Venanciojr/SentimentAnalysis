package ie.atu.sw;

import java.util.Scanner;

public class Runner {
	
	   public static int choice;
	   public static boolean KeepRunning;

	public static void main(String[] args) throws Exception {
		
		Menu mn = new Menu();
		mn.ProgressMeter();
		
		
		Scanner sc = new Scanner (System.in);
		 
		mn.Start();
		
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-6]>");
		
		choice = sc.nextInt();
		sc.close();
		
		
		switch (choice) {
		
		case 1:
			System.out.println("option 1");
	        break;
	    case 2:
	        System.out.println("option 2");
	        break;
		default:
			System.out.println("[error] Invalid Selection");
		    break;
		
		}
		
		
		}
		
		
		
		
}
