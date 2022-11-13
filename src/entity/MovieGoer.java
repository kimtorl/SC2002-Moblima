/*
 * 
 */
package entity;

import java.util.ArrayList;

import boundary.Capability;

/**
 * @author Cross
 *
 */
public class MovieGoer extends Account{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 13L;
	//Attributes
	/** The name. */
	private String name;
	
	/** The mobile number. */
	private String mobileNumber;
	
	/** The email. */
	private String email;
	
	/**
	 * Instantiates a new movie goer.
	 */
	public MovieGoer() {
		
	}
	
	/**
	 * Instantiates a new movie goer.
	 *
	 * @param username the username
	 * @param password the password
	 * @param accountType the account type
	 * @param capabilities the capabilities
	 * @param name the name
	 * @param mobileNumber the mobile number
	 * @param email the email
	 */
	public MovieGoer(String username, String password, AccountType accountType, ArrayList<Capability> capabilities, 
			String name, String mobileNumber, String email) 
	{
		super(username, password, accountType, capabilities);
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	
	/**
	 * Display capabilities.
	 */
	@Override
	public void displayCapabilities() {
		//print out list of capabilities
		for(int i=0; i < getCapabilities().size();i++) {
			System.out.println((i+1) + ". " + getCapabilities().get(i));
		}
	}

	/**
	 * Perform select capability.
	 *
	 * @param choice the choice
	 */
	@Override
	public void performSelectCapability(int choice) {
		//error checking
		if(choice<1 || choice >getCapabilities().size()) return;
		getCapabilities().get(choice-1).performCapability();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the mobile number.
	 *
	 * @return the mobile number
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Sets the mobile number.
	 *
	 * @param mobileNumber the new mobile number
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
