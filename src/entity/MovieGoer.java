package entity;

import java.util.ArrayList;

import boundary.Capability;

/**
 * @author Cross
 *
 */
public class MovieGoer extends Account{

	/**
	 * 
	 */
	private static final long serialVersionUID = 13L;
	//Attributes
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String mobileNumber;
	/**
	 * 
	 */
	private String email;
	
	/**
	 * 
	 */
	public MovieGoer() {
		
	}
	
	/**
	 * @param username
	 * @param password
	 * @param accountType
	 * @param capabilities
	 * @param name
	 * @param mobileNumber
	 * @param email
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
	 *
	 */
	@Override
	public void displayCapabilities() {
		//print out list of capabilities
		for(int i=0; i < getCapabilities().size();i++) {
			System.out.println((i+1) + ". " + getCapabilities().get(i));
		}
	}

	/**
	 *
	 */
	@Override
	public void performSelectCapability(int choice) {
		//error checking
		if(choice<1 || choice >getCapabilities().size()) return;
		getCapabilities().get(choice-1).performCapability();
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
