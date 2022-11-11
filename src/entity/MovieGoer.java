package entity;

import java.util.ArrayList;

public class MovieGoer extends Account{

	private static final long serialVersionUID = 13L;
	//Attributes
	private String name;
	private String mobileNumber;
	private String email;
	
	public MovieGoer() {
		
	}
	
	public MovieGoer(String username, String password, AccountType accountType, ArrayList<Capability> capabilities, 
			String name, String mobileNumber, String email) 
	{
		super(username, password, accountType, capabilities);
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	
	@Override
	public void displayCapabilities() {
		//print out list of capabilities
		System.out.println("Choose an option: ");
		for(int i=0; i < getCapabilities().size();i++) {
			System.out.println("Option " + i + ". " + getCapabilities().get(i));
		}
	}

	@Override
	public void performSelectCapability(int choice) {
		getCapabilities().get(choice-1).performCapability();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}