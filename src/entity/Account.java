package entity;

import java.io.Serializable;
import java.util.ArrayList;

import boundary.Capability;
public abstract class Account implements Serializable{

	
	private static final long serialVersionUID = 11L;
	
	private AccountType accountType;
	private String username;
	private String password;
	private ArrayList<Capability> capabilities;
	
	//Empty Constructor
	public Account() {
	}
	
	//Constructor
	public Account(String username, String password, AccountType accountType, ArrayList<Capability> capabilities) {
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.capabilities = capabilities;
	}

	public abstract void displayCapabilities();
	
	public abstract void performSelectCapability(int choice);

	//checks if this account is an Admin
	public boolean isAdmin() {
		return (this.accountType == AccountType.ADMIN);
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Capability> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(ArrayList<Capability> capabilities) {
		this.capabilities = capabilities;
	}
	
	

}