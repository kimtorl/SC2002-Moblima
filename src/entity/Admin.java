package entity;

import java.util.ArrayList;

public class Admin extends Account {

	
	private static final long serialVersionUID = 12L;

	public Admin() {
	}
	
	public Admin(String username, String password, AccountType accountType, ArrayList<Capability> capabilities) {
		super(username, password, accountType, capabilities);
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

}
