package entity;

import java.util.ArrayList;

import boundary.Capability;

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
		int i;
		for(i=0; i < getCapabilities().size();i++) {
			System.out.println((i+1) + ". " + getCapabilities().get(i));
		}
		
	}

	@Override
	public void performSelectCapability(int choice) {
		//error checking
		if(choice<1 || choice >getCapabilities().size()) return;
		getCapabilities().get(choice-1).performCapability();
	}

}
