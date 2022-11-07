package entity;

import java.util.ArrayList;
public abstract class Account {
	
	private AccountType accountType;
	private String username;
	private String password;
	private ArrayList<Capability> capabilities;
	
	public abstract void displayCapabilities();
	
	public abstract void performSelectCapability();

}