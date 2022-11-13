/*
 * 
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;

import boundary.Capability;
public abstract class Account implements Serializable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 11L;
	
	/** The account type. */
	private AccountType accountType;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The capabilities. */
	private ArrayList<Capability> capabilities;
	
	/**
	 * Instantiates a new account.
	 */
	//Empty Constructor
	public Account() {
	}
	
	/**
	 * Instantiates a new account.
	 *
	 * @param username the username
	 * @param password the password
	 * @param accountType the account type
	 * @param capabilities the capabilities
	 */
	//Constructor
	public Account(String username, String password, AccountType accountType, ArrayList<Capability> capabilities) {
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.capabilities = capabilities;
	}

	/**
	 * Display capabilities.
	 */
	public abstract void displayCapabilities();
	
	/**
	 * Perform select capability.
	 *
	 * @param choice the choice
	 */
	public abstract void performSelectCapability(int choice);

	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	//checks if this account is an Admin
	public boolean isAdmin() {
		return (this.accountType == AccountType.ADMIN);
	}
	
	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Sets the account type.
	 *
	 * @param accountType the new account type
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the capabilities.
	 *
	 * @return the capabilities
	 */
	public ArrayList<Capability> getCapabilities() {
		return capabilities;
	}

	/**
	 * Sets the capabilities.
	 *
	 * @param capabilities the new capabilities
	 */
	public void setCapabilities(ArrayList<Capability> capabilities) {
		this.capabilities = capabilities;
	}
	
	

}