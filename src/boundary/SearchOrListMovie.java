package boundary;

import java.io.Serializable;

import control.InputManager;
import control.MovieManager;

public class SearchOrListMovie implements Capability, Serializable {

	private static final long serialVersionUID = 35L;
	
	private MovieManager movieMgr;
	
	public SearchOrListMovie(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}


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
				searchMovieByID();
				break;
			case 3:
				searchMovieByTitle();
			default:
					
			}
			
		}while(choice != 4);

	}


	
	public String toString() {
		String str = "Search/List Movies";
		return str;
	}
	
	
	//NEED TO CHECK MOVIE'S SHOWING STATUS WHEN LISTING!
	public void listMovie() {
		System.out.println("------------------------------");
		movieMgr.listAllMovies();
		System.out.println("------------------------------");
	}
	
	public void searchMovieByID() {
		System.out.println("Enter a movieID: ");
		int movieID = InputManager.getInt();

		if(movieMgr.findMovie(movieID) ==null) {
			System.out.println("Movie cannot be found! Please try again!");
		}
		else System.out.println(movieMgr.findMovie(movieID));
	}
	
	public void searchMovieByTitle() {
		System.out.println("Enter Movie Title: ");
		
	}
	
}
