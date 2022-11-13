/*
 * 
 */
package entity;

import java.util.ArrayList;

import boundary.Capability;

/**
 * @author Cross
 * Represents the Admin Account
 */
public class Admin extends Account {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 12L;

	/**
	 * Instantiates a new admin.
	 */
	public Admin() {
	}
	
	/**
	 * Instantiates a new admin.
	 *
	 * @param username the username
	 * @param password the password
	 * @param accountType the account type
	 * @param capabilities the capabilities
	 */
	public Admin(String username, String password, AccountType accountType, ArrayList<Capability> capabilities) {
		super(username, password, accountType, capabilities);
	}
	
	/**
	 * Display list of capabilities.
	 */
	@Override
	public void displayCapabilities() {
		//print out list of capabilities
		int i;
		for(i=0; i < getCapabilities().size();i++) {
			System.out.println((i+1) + ". " + getCapabilities().get(i));
		}
		
	}

	/**
	 * Perform selected capability.
	 *
	 * @param choice the choice
	 */
	@Override
	public void performSelectCapability(int choice) {
		//error checking
		if(choice<1 || choice >getCapabilities().size()) return;
		getCapabilities().get(choice-1).performCapability();
	}

}
