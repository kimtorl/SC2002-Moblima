package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import boundary.Capability;
import entity.Account;
import entity.AccountType;
import entity.Admin;
import entity.MovieGoer;


public class AccountFileManager implements AccountManager{
	
	//File managed by this class
	public final static String FILENAME = "Database/accounts.txt";
	
	//Empty constructor 
	public AccountFileManager() {
	}
	
	
	//returns all the Accounts from the file
	//returns an null if file not found
	@SuppressWarnings("unchecked")
	public ArrayList<Account> getAllAccount(){
		ArrayList<Account> accountList = null;
		
		File f = new File(FILENAME);
		if (f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(FILENAME);
				ObjectInputStream in = new ObjectInputStream(fis);
				accountList = (ArrayList<Account>) in.readObject();
				in.close();
				
			}catch(IOException ex) {
				System.out.println("File not found!");
				ex.printStackTrace();
			}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		
		return accountList;
	}
	
	
	//returns the verified Account
	//return null if no corresponding account can be found
	public Account verifyLogin(String username, String password) {
		ArrayList<Account> accountList = getAllAccount();
		if(accountList==null) return null;
		
		for(Account acc : accountList) {
			String validUsername = acc.getUsername();
			String validPassword = acc.getPassword();
			if(username.equals(validUsername) && password.equals(validPassword)) {
				System.out.println("Login successful! Welcome " + acc.getAccountType() + "!"); 
				return acc;
			}
		}
		
		System.out.println("Invalid username or password! Try again!");
		return null;
	}
	
	//@param operation "add" to add a capability, "del" to delete a capability
	//updates the capabilities of all Accounts of given accountType.
	public boolean updateAccountCapability(AccountType accountType, String operation, Capability capability) {
		ArrayList<Account> accountList = getAllAccount();
		if (accountList==null) return false;
		ArrayList<Capability> capabilities;
		
		switch(operation) {
		case "add": //for all accounts of accountType, add capability
			for(Account acc : accountList) { 
				if(acc.getAccountType() == accountType) {
					capabilities = acc.getCapabilities();
					for(Capability c:capabilities) {
						if(c.getClass() == capability.getClass()) {
							System.out.println("Duplicated Capability!");
							return false;
						}
					}
					capabilities.add(capability);
				}
			}
			break;
		case "del":
			for(Account acc : accountList) {
				if(acc.getAccountType() == accountType) {
					capabilities = acc.getCapabilities();
					if(!capabilities.removeIf(c -> (c.getClass()==capability.getClass()))) {
						System.out.println("Capability not found!");
						return false;
					}
				}
			}
			break;	
		default:
			System.out.println("Invalid operation! Please pass in 'add' or 'del' only!");
			return false;
		}
		
		writeToFile(accountList);
		return true;
	}
	
	
	
	//ONLY USED IN INITIALISING DATA
	//creates an ADMIN Account
	public boolean createAdminAccount(String username, String password, AccountType accountType, ArrayList<Capability> capabilities) {
		File f = new File(FILENAME);
		ArrayList<Account> accountList;
		
		//retrieve list of Accounts
		if(f.exists()) accountList = getAllAccount();
		else accountList = new ArrayList<Account>();
		
		//check for duplicate username
		for(Account acc : accountList) {
			if (username.equals(acc.getUsername())) {
				System.out.println("Username has been used!");
				return false;
			}
		}
		
		//create new account, append it and save the file
		accountList.add(new Admin(username, password, accountType, capabilities));
		writeToFile(accountList);
		System.out.println("Admin account " +username + " created successfully!");
		return true;
	}
	
	//ONLY USED IN INITIALISING DATA
	//creates a MOVIEGOER Account
	public boolean createMovieGoerAccount(String username, String password, AccountType accountType, ArrayList<Capability> capabilities, 
			String name, String mobileNumber, String email) 
	{	
		File f = new File(FILENAME);
		ArrayList<Account> accountList;
		
		//retrieve list of Accounts
		if(f.exists()) accountList = getAllAccount();
		else accountList = new ArrayList<Account>();
		
		//check for duplicate username
		for(Account acc : accountList) {
			if (username.equals(acc.getUsername())) {
				System.out.println("Username has been used!");
				return false;
			}
		}
		
		//create new account, append it and save the file
		accountList.add(new MovieGoer(username, password, accountType, capabilities, name, mobileNumber, email));
		writeToFile(accountList);
		System.out.println("MovieGoer account " +username + " created successfully!");
		return true;
	}
	
	
	//writes an ArrayList of Accounts to the file
	public void writeToFile(ArrayList<Account> accountList) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(accountList);
			out.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
