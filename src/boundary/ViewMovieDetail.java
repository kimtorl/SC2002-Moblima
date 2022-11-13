/*
 * 
 */
package boundary;

import java.io.Serializable;

import control.InputManager;
import control.MovieManager;

/**
 * @author Cross
 * This class allows us to view movie details
 */
public class ViewMovieDetail implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 36L;
	
	
	/** The movie mgr. */
	private MovieManager movieMgr;
	
	/**
	 * Instantiates a new view movie detail.
	 *
	 * @param movieMgr the movie mgr
	 */
	public ViewMovieDetail(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

	/**
	 * Allows user to choose which movie to view in more details.
	 * User to input movieID.
	 */
	@Override
	public void performCapability() {
		System.out.println("------------------------------");
		System.out.println("Enter movieID to view:");
		int choice = InputManager.getInt();
		if(movieMgr.findMovie(choice) == null) System.out.println("Movie cannot be found! Please try again!");
		movieMgr.displayMovieInfo(choice);

	}
	

	
	/**
	 * Overrides toString method for printing the capability
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "View Movie Details";
		return str;
	}

}
