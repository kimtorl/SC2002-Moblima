package boundary;

import java.io.Serializable;

import control.MovieManager;
import control.TransactionManager;

public class Top5MovieByTicketSale implements Capability, Serializable {

	private static final long serialVersionUID = 32L;

	private MovieManager movieMgr;
	private TransactionManager transactionMgr;
	
	public Top5MovieByTicketSale(MovieManager movieMgr, TransactionManager transactionMgr) {
		this.movieMgr = movieMgr;
		this.transactionMgr = transactionMgr;
	}
	
	@Override
	public void performCapability() {
		// TODO Auto-generated method stub
		
	}
	
	
	public String toString() {
		String str = "List Top 5 Movies by Ticket Sales";
		return str;
	}

}
