/*
 * 
 */
package control;

import java.util.ArrayList;

import boundary.Capability;
import entity.Account;
import entity.AccountType;

public interface AccountManager {
	
	/**
	 * Gets the all account.
	 *
	 * @return the all account
	 */
	public ArrayList<Account> getAllAccount();
	
	/**
	 * Verify login.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the account
	 */
	public Account verifyLogin(String username, String password);
	
	/**
	 * Update account capability.
	 *
	 * @param accountType the account type
	 * @param operation the operation
	 * @param capability the capability
	 * @return true, if successful
	 */
	public boolean updateAccountCapability(AccountType accountType, String operation, Capability capability);
	
	/**
	 * Creates the admin account.
	 *
	 * @param username the username
	 * @param password the password
	 * @param accountType the account type
	 * @param capabilities the capabilities
	 * @return true, if successful
	 */
	public boolean createAdminAccount(String username, String password, AccountType accountType, ArrayList<Capability> capabilities);
	
	/**
	 * Creates the movie goer account.
	 *
	 * @param username the username
	 * @param password the password
	 * @param accountType the account type
	 * @param capabilities the capabilities
	 * @param name the name
	 * @param mobileNumber the mobile number
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean createMovieGoerAccount(String username, String password, AccountType accountType, ArrayList<Capability> capabilities, 
			String name, String mobileNumber, String email);
	
	
}
