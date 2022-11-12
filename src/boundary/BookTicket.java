package boundary;

import java.io.Serializable;

import control.MovieManager;
import control.ShowtimeManager;
import control.TransactionManager;

public class BookTicket implements Capability, Serializable {

	private static final long serialVersionUID = 37L;
	
	private ShowtimeManager showtimeMgr;
	private MovieManager movieMgr;
	private TransactionManager transactionMgr;
	
	public BookTicket(ShowtimeManager showtimeMgr, MovieManager movieMgr, TransactionManager transactionMgr){
		this.showtimeMgr = showtimeMgr;
		this.movieMgr = movieMgr;
		this.transactionMgr = transactionMgr;
	}


	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}


	
	public String toString() {
		String str = "Book Tickets";
		return str;
	}
}
