package control;

import java.util.ArrayList;

import entity.Account;

public interface AccountManager {
	
	public ArrayList<Account> getAllAccount();
	
	public Account verifyLogin(String username, String password);
}
