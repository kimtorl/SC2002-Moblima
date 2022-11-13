/*
 * 
 */
package boundary;

import java.io.Serializable;
import java.util.ArrayList;

import control.InputManager;
import control.MovieManager;
import entity.Movie;

public class SearchOrListMovie implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 35L;
	
	/** The movie mgr. */
	private MovieManager movieMgr;
	
	/**
	 * Instantiates a new search or list movie.
	 *
	 * @param movieMgr the movie mgr
	 */
	public SearchOrListMovie(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}


	/**
	 * Perform capability.
	 */
	@Override
	public void performCapability() {
		int choice;
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. List All Movies");
			System.out.println("2. Search Movie By MovieID");
			System.out.println("3. Search Movie By Movie Title");
			System.out.println("4. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 4);
			
			switch(choice) {
			case 1:
				listMovie();
				break;
			case 2:
				searchMovieByMovieID();
				break;
			case 3:
				searchMovieByMovieTitle();
			default:
					
			}
			
		}while(choice != 4);

	}


	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "Search/List Movies";
		return str;
	}
	
	
	/**
	 * List movie.
	 */
	//NEED TO CHECK MOVIE'S SHOWING STATUS WHEN LISTING!
	public void listMovie() {
		System.out.println("------------------------------");
		movieMgr.listAllMovies();
		System.out.println("------------------------------");
	}
	
	/**
	 * Search movie by movie ID.
	 */
	public void searchMovieByMovieID() {
		System.out.println("------------------------------");
		System.out.println("Enter a movieID: ");
		int movieID = InputManager.getInt();
		
		Movie movie = movieMgr.findMovie(movieID);
		if (movie == null) {
			System.out.println("Movie cannot be found! Please try again!");
		}
		else System.out.println(movie);
		
	}
	
	/**
	 * Search movie by movie title.
	 */
	public void searchMovieByMovieTitle() {
		String str = InputManager.getString();
		
		ArrayList<Movie> movieList = movieMgr.findAllMovieOfTitle(movieMgr.getAllMovie(), str);
		
		if(movieList.size() == 0) {
			System.out.println("No movies found!");
			return;
		}
		
		for(Movie m: movieList) {
			System.out.println(m);
		}	
	}
	
}
