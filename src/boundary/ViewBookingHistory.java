package boundary;

import java.io.Serializable;
import java.util.ArrayList;

import control.TransactionManager;
import entity.MovieGoer;
import entity.Transaction;

public class ViewBookingHistory implements Capability, Serializable {

	private static final long serialVersionUID = 38L;
	
	TransactionManager transactionMgr;
	
	public ViewBookingHistory(TransactionManager transactionMgr) {
		this.transactionMgr = transactionMgr;
	}

	@Override
	public void performCapability() {
		// TODO Auto-generated method stub
		ArrayList<Transaction> transList;
		transList = transactionMgr.getAllTransaction();
		
		MovieGoer acc = (MovieGoer)MainApplication.currentAcc;
		String name = acc.getName();
		
		for(int i = 0; i < transList.size(); i++)
		{
			if(name.equals(transList.get(i).getName()))
			{
				printTransaction(transList.get(i));
			}
		}

	}

	

	
	public String toString() {
		String str = "View Booking History";
		return str;
	}
	
	public void printTransaction(Transaction t) {
		System.out.println("Transaction ID: "
				+t.getTransactionID()
				+"\nTransaction amount: "
				+t.getTransactionAmount()
				+"\nName: "
				+t.getName()
				+"\nMobile Number: "
				+t.getMobileNumber()
				+"\nEmail "
				+t.getEmail()
				+"\nDate & Time: "
				+t.getDateTime()
				+"\nMovie ID: "
				+t.getMovieID()
				+"\nCinema Code: "
				+t.getCinemaCode()
				+"\nSeat ID List: "
				);
		printSeatIDList(t);
		System.out.println("______________________________");
	}
	
	public void printSeatIDList(Transaction t) {
		ArrayList<Integer> seatList = t.getSeatIDList();
		for(int i = 0; i < seatList.size(); i++) {
			System.out.print(seatList.get(i)+" | ");
		}
		return;
	}
}
