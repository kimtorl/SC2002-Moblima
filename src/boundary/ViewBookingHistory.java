/*
 * 
 */
package boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import control.TransactionManager;
import entity.MovieGoer;
import entity.Transaction;

public class ViewBookingHistory implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 38L;
	
	/** The transaction mgr. */
	TransactionManager transactionMgr;
	
	/**
	 * Instantiates a new view booking history.
	 *
	 * @param transactionMgr the transaction mgr
	 */
	public ViewBookingHistory(TransactionManager transactionMgr) {
		this.transactionMgr = transactionMgr;
	}

	/**
	 * Displays the transaction history for user to view
	 * The transaction history is found based on the name of the current acount logged in.
	 */
	@Override
	public void performCapability() {
		ArrayList<Transaction> transList;
		transList = transactionMgr.getAllTransaction();
		
		MovieGoer acc = (MovieGoer)MainApplication.currentAcc;
		String name = acc.getName();
		
		for(int i = 0; i < transList.size(); i++)
		{
			if(name.equals(transList.get(i).getName()))
			{
				System.out.println(transList.get(i).toString());
				
			}
		}

	}

	

	
	/**
	 *Overrides toString method for printing the capability
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "View Booking History";
		return str;
	}
	
}
