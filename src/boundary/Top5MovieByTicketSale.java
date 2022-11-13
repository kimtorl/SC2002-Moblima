/*
 * 
 */
package boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import control.MovieManager;
import control.TransactionManager;
import entity.Movie;
import entity.Transaction;

public class Top5MovieByTicketSale implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 32L;

	/** The movie mgr. */
	private MovieManager movieMgr;
	
	/** The transaction mgr. */
	private TransactionManager transactionMgr;
	
	/**
	 * Instantiates a new top 5 movie by ticket sale.
	 *
	 * @param movieMgr the movie mgr
	 * @param transactionMgr the transaction mgr
	 */
	public Top5MovieByTicketSale(MovieManager movieMgr, TransactionManager transactionMgr) {
		this.movieMgr = movieMgr;
		this.transactionMgr = transactionMgr;
	}
	
	/**
	 * Perform capability.
	 */
	@Override
	public void performCapability() {
		ArrayList<Transaction> allTrans;
		allTrans = transactionMgr.getAllTransaction();
		
		ArrayList<Movie> allMovies;
		allMovies = movieMgr.getAllMovie();
		
		
		for(int i = 0; i < allTrans.size(); i++) {
			
			int movieID = allTrans.get(i).getMovieID();
			
			for(int j = 0; j<allMovies.size();j++){
				if(allMovies.get(j).getMovieID() == movieID) {
					//add number of tickets sold
					for(int k=0;k<allTrans.get(i).getSeatIDList().size();k++) {
						allMovies.get(j).plusOneSales();
					}			
				}
			}
		}
		
		allMovies.sort(new Comparator<Movie>() {
			public int compare(Movie m1, Movie m2) {
				return m2.getTicketSales() - m1.getTicketSales();
			}
		});
		
		System.out.println("Top 5 ticket sales are: ");
		for(int i = 0; i < 5; i++) {
			System.out.printf("Top %d. %-50s | %-20s | Ticket Sales: %d\n", i+1,
					allMovies.get(i).getMovieTitle(),
					allMovies.get(i).getMovieType().toString(),
					allMovies.get(i).getTicketSales());
		}
		
		
	}
	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "List Top 5 Movies by Ticket Sales";
		return str;
	}

}
