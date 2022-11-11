package control;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Transaction;

public interface TransactionManager {
	
	public ArrayList<Transaction> getAllTransaction();
	
	
	//reads all the transactions into an arrayList
	//check for duplicated TransactionID
	//creates a Transaction and add it to this ArrayList
	//saves ArrayList
	public boolean createTransaction(double transactionAmount,
			String name,
			String mobileNumber,
			String email,
			LocalDateTime dateTime,
			int movieID,
			String cinemaCode,
			ArrayList<Integer> seatIDList);
	
	//writes an ArrayList of Transaction to file
	public void writeToFile(ArrayList<Transaction> transactionList);
	
	//finds and return a transaction by transactionID
	public Transaction findByTID(String transactionID);
	
	//finds and return a transaction from a given transactionList by transactionID
	//public Transaction findByTID(ArrayList<Transaction> transactionList, String transactionID);
	
	
	public ArrayList<Transaction> findByName(String name); 
	
	//delete the Transaction in the file with given TID
		public boolean deleteTransaction(String transactionID);
	
}
