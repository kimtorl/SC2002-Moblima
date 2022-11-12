package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputManager {
	
	private static Scanner userInput = new Scanner(System.in);
	
	
	//gets an integer from a range from User 
	// inclusive of lower and upper bounds
	public static int getInt(int lower, int upper) {
		String message = "Invalid input! Please enter an integer from " + lower + " to " + upper + "!";
		int choice;
		do {
			if (userInput.hasNextInt()) {
				choice = userInput.nextInt();
				if(choice<lower || choice>upper) System.out.println(message);
			}
			else {
				choice = lower -1; //ensure that loop continues
				System.out.println(message);
				userInput.nextLine();
			}
			userInput.nextLine();
		}while(choice<lower && choice>upper);
		
		return choice;
	}
	
	//overloaded: without bounds
	//gets an integer from a range from User 
	public static int getInt() {
		String message = "Invalid input! Please enter an integer!";
		boolean invalid = true;
		int choice = -1;
		
		while(invalid){
			if (userInput.hasNextInt()) {
				choice = userInput.nextInt();
				invalid=false;
				userInput.nextLine();
			}
			else {
				System.out.println(message);
				userInput.nextLine();
			}
		}
		
		return choice;
	}
	
	
	//gets a double from a range from User 
	// inclusive of lower and upper bounds
	public static double getDouble(int lower, int upper) {
		String message = "Invalid input! Please enter a double from " + lower + " to " + upper + "!";
		double choice;
		do {
			if (userInput.hasNextDouble()) {
				choice = userInput.nextDouble();
				if(choice<lower || choice>upper) System.out.println(message);
			}
			else {
				choice = lower -1; //ensure that loop continues
				System.out.println(message);
			}
			userInput.nextLine();
		}while(choice<lower && choice>upper);
		
		return choice;
	}
	
	
	public static boolean getY_or_N() {
		
		return true;
	}
	
	
	//gets a String as input, cannot be empty String
	public static String getString() {
		String str;
		do {
			str = userInput.nextLine();
			if(str.isEmpty()) System.out.println("Input cannot be empty! Please try again!");
		}while(str.isEmpty());
		return str;
	}
	
	
	
	
	
	//gets a LocalDate as input
	public static LocalDate getLocalDate() {
		LocalDate date=null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		boolean valid = false;
		
		do {
			System.out.println("Enter a date in this format YYYY-MM-DD: ");
			try {
				date = LocalDate.parse(userInput.nextLine(), format);
				valid = true;
			}catch(DateTimeParseException e) {
				System.out.println("Date given in wrong format! Please try again!");
			}
			
		}while(!valid);
		
		return date;
	}
}
