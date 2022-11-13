/*
 * This class responsibilty is to get various types of inputs from the user and perform error checking on them.
 * Input Manager does not modify any value, it only returns value based on user input.
 * The methods are static.
 */
                                                                                                                             
package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputManager {
	
	/** Scanner initialised as static*/
	private static Scanner userInput = new Scanner(System.in);
	
	
	/**
	 * gets an integer from User within a specified range.
	 * gets an integer bounded by a range from User, range is lower to upper inclusive.
	 * if user inputs integer out of range, prompt the user for another input
	 * 
	 * @param lower the lower bound
	 * @param upper the upper bound
	 * @return user input of type integer
	 */
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
		}while(choice<lower || choice>upper);
		
		return choice;
	}
	
	/**
	 * 
	 * Overloaded method with no bounds to get integer value. Should take note to check for invalid input when using this method.
	 * @return the int
	*/
	public static int getInt() {
		String message = "Invalid input! Please enter an integer!";
		int choice=-1;
		boolean invalid = true;
		while(invalid){
			if (userInput.hasNextInt()) {
				choice = userInput.nextInt();
				invalid = false;
				userInput.nextLine(); //remove newline char

			}
			else {
				System.out.println(message);
				userInput.nextLine();
			}
			
		}
		return choice;
	}
	 
	/**
	 * Gets a double bounded within a range from the user. It is inclusive of the lower and upper bound
	 * If input exceeds the range, prompt user for the input again.
	 *
	 * @param lower the lower bound
	 * @param upper the upper bound
	 * @return user input of type double
	 */
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
		}while(choice<lower || choice>upper);
		
		return choice;
	}
	
	
	/**
	 * Gets a boolean value.
	 * Gets user to input Y,y,N,n to return a boolean value.
	 * 
	 * @return boolean value
	 */
	public static boolean getY_or_N() {
		boolean invalid = true, result = false;
		String message = "Please only enter Y or N!", input;
		while(invalid) {
			input = userInput.nextLine();
			if(input.equals("Y") || input.equals("y")) {
				result = true;
				invalid = false;
			}
			else if(input.equals("N") || input.equals("n")) {
				result = false;
				invalid = false;
			}
			else System.out.println(message);	
		}
		
		return result;
	}
	
	
	/**
	 * Gets a non empty string.
	 *
	 * @return the string
	 */
	public static String getString() {
		String str;
		do {
			str = userInput.nextLine();
			if(str.isEmpty()) System.out.println("Input cannot be empty! Please try again!");
		}while(str.isEmpty());
		return str;
	}
	
	//used when input error
	//return true if user wants to input again
	/**
	 * Try again.
	 * Used when there is input error.
	 * returns true if user wants to input again
	 * returns false otherwise
	 * @return boolean value 
	 */
	public static boolean tryAgain() {
		System.out.println("Choose an option:");
		System.out.println("1. Try again");
		System.out.println("2. Exit");
		int choice = getInt(1,2);
		if(choice ==1) return true;
		else return false;
	}
	
	
	
	/**
	 * Gets a local date as input, format is yyyy mm dd.
	 * 
	 * @return the local date
	 */
	public static LocalDate getLocalDate() {
		LocalDate date=null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		boolean valid = false;
		
		do {
			System.out.println("Enter a date in this format yyyy-MM-dd: ");
			try {
				date = LocalDate.parse(userInput.nextLine(), format);
				valid = true;
			}catch(DateTimeParseException e) {
				System.out.println("Date given in wrong format! Please try again!");
			}
			
		}while(!valid);
		
		return date;
	}
	
	
	/**
	 * Gets the local date time.
	 * Format is yyyy MM dd HH:mm
	 * @return the local date time
	 */
	//gets a LocalDateTime as input
	public static LocalDateTime getLocalDateTime() {
		LocalDateTime dateTime =null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		boolean valid = false;
		
		do {
			System.out.println("Enter a date and time in this format yyyy-MM-dd HH:mm ");
			try {
				dateTime = LocalDateTime.parse(userInput.nextLine(), format);
				valid = true;
			}catch(DateTimeParseException e) {
				System.out.println("DateTime given in wrong format! Please try again!");
			}
			
		}while(!valid);
		
		return dateTime;
	}
}
