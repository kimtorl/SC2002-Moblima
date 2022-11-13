/*
 * 
 */
package boundary;

import control.AccountFileManager;
import control.AccountManager;
import control.InputManager;
import entity.Account;
import entity.AccountType;

public class MainApplication {
	
	/** The Account of the current user*/
	public static Account currentAcc = null;
	
	/** The Control class for Account */
	public static AccountManager accMgr = new AccountFileManager();
	
	
	/**
	 * The main method. Run this main method to start the application.
	 * Refer to the setup guide if there are errors.
	 *	
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int choice;
		
		//get input from user
		do {
			System.out.println("|----------------------------|");
			System.out.println("|---------*MOBLIMA*----------|");
			System.out.println("|----------------------------|");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			choice = InputManager.getInt(1, 2);
			
			switch(choice) {
			case 1:
				login();
				break;
			case 2:
				System.out.println("Thank you for using MOBLIMA! Have a nice day!");
				System.out.println("Exiting MOBLIMA...");
				break;
			default:
			}
			
		}while(choice !=2);
	}
	

	/**
	 * Login.
	 * Asks for username and password
	 * AccountManager verifies account.
	 * Assigns an Account to currentAcc if verified, or null if not verified
	 * The corresponding admin or movieGoer method is called depending on the Account that was returned to currentAcc.
	 */
	//user can chooose to try again or exit.
	public static void login() {
		
		//verify the user and retrieve the user Account
		int choice=0;
		do {
			System.out.println("Please enter your username: ");
			String username = InputManager.getString();
			
			System.out.println("Please enter your password: ");
			String password = InputManager.getString();
			
			currentAcc = accMgr.verifyLogin(username, password);
			if(currentAcc==null) {
				System.out.println("Choose an option:");
				System.out.println("1. Try again");
				System.out.println("2. Exit");
				choice = InputManager.getInt(1, 2);
				if(choice == 2) return; //exit Login function
			}
		
		}while(currentAcc==null && choice == 1 );
	
		
		if(currentAcc.getAccountType() == AccountType.ADMIN) {
			adminLogin();
		}
		else if(currentAcc.getAccountType() == AccountType.MOVIEGOER) {
			movieGoerLogin();
		}
	}
	

	
	/**
	 * Admin logged in.
	 * Displays menu and prints out the array list of capabilities
	 * until Admin logs out.
	*/
	public static void adminLogin() {
		System.out.println("Welcome admin " + currentAcc.getUsername() + "!");
		int choice, numOfOptions;
		//display menu
		do {
			numOfOptions = currentAcc.getCapabilities().size() +1;
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			currentAcc.displayCapabilities();
			System.out.println(numOfOptions  + ". Logout");
			System.out.println("------------------------------");
			choice = InputManager.getInt(1, numOfOptions);
			currentAcc.performSelectCapability(choice);
			
			if(choice == numOfOptions) { //Logout chosen
				System.out.println("Logging out...");
				currentAcc = null; //remove reference to this Account
			}
		}while(choice != numOfOptions);
		
	}

	
	/**
	 * MovieGoer logged in.
	 * Displays menu and prints out the array list of capabilities
	 * 	until user chooses to logout
	 */
	public static void movieGoerLogin() {
		System.out.println("Welcome MovieGoer " + currentAcc.getUsername() + "!");
		int choice, numOfOptions;
		//display menu
		do {
			numOfOptions = currentAcc.getCapabilities().size() +1;
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			currentAcc.displayCapabilities();
			System.out.println(numOfOptions  + ". Logout");
			System.out.println("------------------------------");
			choice = InputManager.getInt(1, numOfOptions);
			currentAcc.performSelectCapability(choice);
			
			if(choice == numOfOptions) { //Logout chose
				System.out.println("Logging out...");
				currentAcc = null; //remove reference to this Account
			}
		}while(choice != numOfOptions);
	}

}
