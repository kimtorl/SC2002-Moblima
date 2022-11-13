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

/**
 * @author Cross
 * Represents TransactionFileManager which implements methods of transaction manager
 * Allows us to modify and retrieve transactions.
 */
public class TransactionFileManager implements TransactionManager, Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 25L;
	
	/** The Constant FILENAME. */
	public static final String FILENAME = "Database/transactions.txt";
	
	
	/**
	 * Instantiates a new transaction file manager.
	 */
	public TransactionFileManager() {
		
	}
	
	/**
	 * Gets the all transaction.
	 * returns an empty list if file is not found
	 *
	 * @return the all transaction
	 */
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
	 * Writes an ArrayList of Transaction to file
	 *
	 * @param transactionList the transaction list
	 */
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
	
	
	/**
	 * Find and returns a Transaction by transactionID.
	 * returns null if not found
	 * @param transactionID the transactionID
	 * @return  a Transaction object with given transactionID
	 */
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
	 * Find and returns a Transaction by MovieGoer name.
	 * MovieGoer name is retrieved using the current account ,currentAcc that is logged in.
	 * @param name the target name
	 * @return the array list
	 */
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
	 * finds a transaction by transactionID and deletes it.
	 * Save the changes to the file.
	 * @param transactionID the transactionID of Transaction to be removed
	 * @return true, if successful. 
	 */
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
