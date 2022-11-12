package boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import control.MovieManager;
import control.TransactionManager;
import entity.Movie;
import entity.Transaction;

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
		ArrayList<Transaction> allTrans;//new?
		allTrans = transactionMgr.getAllTransaction();
		
		ArrayList<Movie> allMovies;
		allMovies = movieMgr.getAllMovie();
		
		
		for(int i = 0; i < allTrans.size(); i++) {
			
			int movieID = allTrans.get(i).getMovieID();
			for(int j = 0; j<allMovies.size();j++)
			{
				if(allMovies.get(j).getMovieID() == movieID) {
					allMovies.get(j).plusOneSales();
				}
			}
		}
		
		allMovies.sort(new Comparator<Movie>() {
			public int compare(Movie m1, Movie m2) {
				return m1.getTicketSales() - m2.getTicketSales();
			}
		});
		
		System.out.println("Top 5 ticket sales are: ");
		for(int i = 0; i < 5; i++) {
			System.out.println(allMovies.get(i).getMovieTitle()
					+"	|	"
					+allMovies.get(i).getMovieType().toString()
					+"	|	Ticket Sales: "
					+allMovies.get(i).getTicketSales()
					+"\n");
		}
		
		
	}
	
	
	public String toString() {
		String str = "List Top 5 Movies by Ticket Sales";
		return str;
	}

}
