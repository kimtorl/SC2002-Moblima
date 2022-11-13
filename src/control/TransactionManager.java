/*
 * 
 */
package control;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Transaction;

public interface TransactionManager {
	
	/**
	 * Gets the all transaction.
	 *
	 * @return the all transaction
	 */
	public ArrayList<Transaction> getAllTransaction();
	
	
	//reads all the transactions into an arrayList
	//check for duplicated TransactionID
	//creates a Transaction and add it to this ArrayList
	/**
	 * Creates the transaction.
	 *
	 * @param transactionAmount the transaction amount
	 * @param name the name
	 * @param mobileNumber the mobile number
	 * @param email the email
	 * @param dateTime the date time
	 * @param movieID the movie ID
	 * @param cinemaCode the cinema code
	 * @param seatIDList the seat ID list
	 * @return true, if successful
	 */
	//saves ArrayList
	public boolean createTransaction(double transactionAmount,
			String name,
			String mobileNumber,
			String email,
			LocalDateTime dateTime,
			int movieID,
			String cinemaCode,
			ArrayList<Integer> seatIDList);
	
	/**
	 * Write to file.
	 *
	 * @param transactionList the transaction list
	 */
	//writes an ArrayList of Transaction to file
	public void writeToFile(ArrayList<Transaction> transactionList);
	
	/**
	 * Find by TID.
	 *
	 * @param transactionID the transaction ID
	 * @return the transaction
	 */
	//finds and return a transaction by transactionID
	public Transaction findByTID(String transactionID);
	
	//finds and return a transaction from a given transactionList by transactionID
	
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the array list
	 */
	public ArrayList<Transaction> findByName(String name); 
	
	/**
	 * Delete transaction.
	 *
	 * @param transactionID the transaction ID
	 * @return true, if successful
	 */
	//delete the Transaction in the file with given TID
		public boolean deleteTransaction(String transactionID);
	
}
