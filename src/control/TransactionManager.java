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
	
	
	/**
	 * Creates the transaction.
	 * reads all the transactions into an arrayList
	 * check for duplicated TransactionID
	 * creates a Transaction and add it to this ArrayList
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
	public boolean createTransaction(double transactionAmount,
			String name,
			String mobileNumber,
			String email,
			LocalDateTime dateTime,
			int movieID,
			String cinemaCode,
			ArrayList<Integer> seatIDList);
	
	/**
	 * Writes an ArrayList of Transaction to file
	 *
	 * @param transactionList the transaction list
	 */
	public void writeToFile(ArrayList<Transaction> transactionList);
	
	/**
	 * Find and returns a Transaction by transactionID.
	 *
	 * @param transactionID the transactionID
	 * @return  a Transaction object with given transactionID
	 */
	public Transaction findByTID(String transactionID);
	
	
	/**
	 * Find and returns a Transaction by MovieGoer name.
	 * MovieGoer name is retrieved using the current account ,currentAcc that is logged in.
	 * @param name the target name
	 * @return the array list
	 */
	public ArrayList<Transaction> findByName(String name); 
	
	/**
	 * Delete transaction.
	 * finds a transaction by transactionID and deletes it.
	 * Save the changes to the file.
	 * @param transactionID the transaction ID
	 * @return true, if successful
	 */
		public boolean deleteTransaction(String transactionID);
	
}
