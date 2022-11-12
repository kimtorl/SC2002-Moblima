package control;

import java.util.ArrayList;

import boundary.Capability;
import entity.Account;
import entity.AccountType;

public interface AccountManager {
	
	public ArrayList<Account> getAllAccount();
	
	public Account verifyLogin(String username, String password);
	
	public boolean updateAccountCapability(AccountType accountType, String operation, Capability capability);
	
	public boolean createAdminAccount(String username, String password, AccountType accountType, ArrayList<Capability> capabilities);
	
	public boolean createMovieGoerAccount(String username, String password, AccountType accountType, ArrayList<Capability> capabilities, 
			String name, String mobileNumber, String email);
	
	
}
