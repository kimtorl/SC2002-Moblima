package boundary;

import java.io.Serializable;

import control.InputManager;
import control.MovieManager;

public class ViewMovieDetail implements Capability, Serializable {

	private static final long serialVersionUID = 36L;
	
	
	private MovieManager movieMgr;
	
	public ViewMovieDetail(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

	@Override
	public void performCapability() {
		System.out.println("------------------------------");
		System.out.println("Enter movieID to view:");
		int choice = InputManager.getInt();
		if(movieMgr.findMovie(choice) == null) System.out.println("Movie cannot be found! Please try again!");
		movieMgr.displayMovieInfo(choice);

	}
	

	
	public String toString() {
		String str = "View Movie Details";
		return str;
	}

}
