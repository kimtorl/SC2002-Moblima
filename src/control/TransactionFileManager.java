/*
 * 
 */
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Transaction;

public class TransactionFileManager implements TransactionManager, Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 25L;
	
	/** The Constant FILENAME. */
	public static final String FILENAME = "Database/transactions.txt";
	
	
	/**
	 * Instantiates a new transaction file manager.
	 */
	//Empty Constructor
	public TransactionFileManager() {
		
	}
	
	//returns all the Transactions in an ArrayList
	/**
	 * Gets the all transaction.
	 *
	 * @return the all transaction
	 */
	//returns an empty list if file is not found
	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> getAllTransaction(){
		ArrayList<Transaction> transactionList = new ArrayList<Transaction> ();
		
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fis);
			transactionList = (ArrayList<Transaction>) in.readObject();
			in.close();
			
		}catch(IOException ex) {
			System.out.println("File not found!");
			ex.printStackTrace();
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return transactionList;
		
	}
	
	
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
	public boolean createTransaction(
			double transactionAmount,
			String name,
			String mobileNumber,
			String email,
			LocalDateTime dateTime,
			int movieID,
			String cinemaCode,
			ArrayList<Integer> seatIDList)
	{	
		ArrayList<Transaction> transactionList;
		
		File f = new File(FILENAME);
		
		if(f.exists())
			transactionList  = getAllTransaction();
		else
			transactionList = new ArrayList<Transaction>();
		
		Transaction txn = new Transaction(transactionAmount, name, mobileNumber, email, dateTime, movieID, cinemaCode, seatIDList);
		
		//check for duplicates TID
		for(Transaction t : transactionList) {
			if(t.getTransactionID().equals(txn.getTransactionID())){
				System.out.println("Duplicated TID");
				return false;
			}
		}
		
		//append the Transaction
		transactionList.add(txn);
		
		//save to file
		writeToFile(transactionList);
		return true;
	}
	
	
	/**
	 * Write to file.
	 *
	 * @param transactionList the transaction list
	 */
	//writes an ArrayList of Transaction to file
	public void writeToFile(ArrayList<Transaction> transactionList) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(transactionList);
			out.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	//finds and return a transaction by transactionID
	/**
	 * Find by TID.
	 *
	 * @param transactionID the transaction ID
	 * @return the transaction
	 */
	//returns null if not found
	public Transaction findByTID(String transactionID) {
		ArrayList<Transaction> transactionList = getAllTransaction();
		
		for(int i=0; i<transactionList.size(); i++){
			String tid = transactionList.get(i).getTransactionID();
			if(tid.equals(transactionID))
				return transactionList.get(i);
		}
		
		return null;
	}
	
	//finds and return a transaction from a given transactionList by transactionID
	//public Transaction findByTID(ArrayList<Transaction> transactionList, int transactionID) {}
	
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the array list
	 */
	//returns all the Transactions with the given name
	public ArrayList<Transaction> findByName(String name) {
		ArrayList<Transaction> transactionList = getAllTransaction();
		ArrayList<Transaction> tList = new ArrayList<Transaction>();
		
		for(int i=0; i<transactionList.size(); i++){
			String transactionName = transactionList.get(i).getName();
			if(transactionName.equals(name))
				tList.add(transactionList.get(i));
		}
		
		return tList;
	}
	
	/**
	 * Delete transaction.
	 *
	 * @param transactionID the transaction ID
	 * @return true, if successful
	 */
	//delete the Transaction in the file with given TID
	public boolean deleteTransaction(String transactionID) {
		ArrayList<Transaction> transactionList = getAllTransaction();
		
		for(int i=0; i<transactionList.size(); i++){
			String tid = transactionList.get(i).getTransactionID();
			if(tid.equals(transactionID)) {
				transactionList.remove(i);
				writeToFile(transactionList);
				return true;
			}
		}
		return false;
	}
	
	
}
