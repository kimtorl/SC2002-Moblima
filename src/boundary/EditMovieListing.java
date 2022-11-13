/*
 * 
 */
package boundary;

import java.io.Serializable;
import java.util.ArrayList;

import control.InputManager;
import control.MovieManager;
import entity.ShowingStatus;
import entity.TypeOfMovie;

/**
 * The Class EditMovieListing, which allows the Admin account to
 * edit movie listings, such as creating and adding new movies, updating movie information
 * or removing movies.
 */
public class EditMovieListing implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 30L;
	
	/** The movie manager. */
	private MovieManager movieMgr;
	
	/**
	 * Instantiates a new edits the movie listing.
	 *
	 * @param movieMgr the movie manager
	 */
	public EditMovieListing(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

	/**
	 * Perform capability, and display menu.
	 */
	@Override
	public void performCapability() {
		int choice;
		
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. Add a Movie");
			System.out.println("2. Update a Movie");
			System.out.println("3. Remove a Movie");
			System.out.println("4. Display all movies");
			System.out.println("5. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 5);
			
			switch(choice) {
			case 1:
				createMovie();
				break;
			case 2:
				updateMovie();
				break;
			case 3:
				removeMovie();
				break;
			case 4:
				listMovie();
				break;
			default:
					
			}
			
		}while(choice != 5);
		
		

	}
	
	/**
	 * Our own tostring method
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "Edit Movie Listing";
		return str;
	}
	
	/**
	 * Lists all the movies that are of ShowingStatus Preview or Now Showing
	 */
	public void listMovie() {
		System.out.println("------------------------------");
		movieMgr.listAllMovies();
		System.out.println("------------------------------");
	}
	
	
	/**
	 * Creates a movie. It will ask for
	 * a movieID. We will check in our list of movies if that ID already exists.
	 * If it does, we will not create the Movie.
	 * If such a movieID does not exist, we can use the movieID to create a movieID. This
	 * ensures no duplicates of movieID.
	 * It will ask for inputs for the Movie Title, the TypeOfMovie(enum) via integer input with the values() method,
	 * The language the movie is in
	 * The duration in minutes
	 * The showing status(enum) via integer input with values() method
	 * Synopsis,Directors,and an ArrayList of Cast which we will keep appending with inputs
	 * from the user until the user inputs negative one as a string.
	 * It will also ask for past reviews and we store it in an ArrayList of Strings
	 * It will ask for past ratings and store inputs of integers into an ArrayList of integers
	 * It will then create a new movie based on all these inputs
	 */
	public void createMovie() {
		System.out.println("Enter the Movie information");
		System.out.println("ID: ");
		int movieID = InputManager.getInt();
		if(movieMgr.findMovie(movieID)!=null) {
			System.out.println("Movie already exists!");
			return;
		}
		
		System.out.println("Title: ");
		String title = InputManager.getString();
		//
		System.out.println("Enter option for the type of Movie: "
				+ "\n1.BLOCKBUSTER_2D\n2.BLOCKBUSTER_3D\n3.NONBLOCKBUSTER_2D\n4.NONBLOCKBUSTER_3D");
		int typeInt = InputManager.getInt(1,4);
		TypeOfMovie type = TypeOfMovie.values()[typeInt-1];
		//
		System.out.println("Language: ");
		String language = InputManager.getString();
		//
		System.out.println("Duration(min)");
		int duration = InputManager.getInt(0, 99999);
		//
		System.out.println("Enter option for the showing status of the Movie: "
				+ "\n1.Coming Soon\n2.Preview\n3.Now Showing\n4.End Of Showing");
		int statusINT = InputManager.getInt(1,4);
		ShowingStatus status = ShowingStatus.values()[statusINT-1];
		//
		System.out.println("Synopsis: ");
		String synopsis = InputManager.getString();
		//
		System.out.println("Director: ");
		String director = InputManager.getString();
		//
		System.out.println("Enter the cast members: \nInput -1 to end");
		ArrayList<String> casts = new ArrayList<String>();
		while(true) {
			String cast = InputManager.getString();
			if(cast.equals("-1"))
				break;
			else
				casts.add(cast);
		}
		//
		System.out.println("Past Reviews: \nInput -1 to end");
		ArrayList<String> reviews = new ArrayList<String>();
		while(true) {
			String review = InputManager.getString();
			if(review.equals("-1"))
				break;
			else
				reviews.add(review);
		}
		//
		System.out.println("Past Ratings: \nInput 0 to end");
		ArrayList<Integer> ratings = new ArrayList<Integer>();
		while(true) {
			int r = InputManager.getInt(0,5);
			if(r == 0)
				break;
			else
				ratings.add(r);
		}
		//
		movieMgr.createMovie(movieID, title, type, language, duration, status, synopsis, director, casts, reviews, ratings);
	}
	
	
	
	
	/**
	 * Updates movie information, such as ID,title,type,language etc
	 * for movieID, it will take in an old movie ID to be replaced and a new movieID to replace
	 * It will look for the old movieID, if it doesnt exist, then nothing happens. If it is found,
	 * we will check if the new movieID to replace it already exists, if the new movieID exists,
	 * nothing will be done, this is to prevent duplication. If the new movieID does not exists,
	 * i.e has not been used, then movie ID is successfully updated.
	 * 
	 * updating movie title takes in a movieID to be updated, then finds all the movies with
	 * the same title as that movie and updates it with the new title.
	 * 
	 * update movie type takes in a movieID and and integer for the input for its enum type
	 * 
	 * update movie language takes in an id, and updates its language
	 * update duration takes in an id, finds all movies with the same title as that movie with the
	 * id, and then updates all the duration.
	 * 
	 * update showing status takes in a movie id, finds movies with same title, and updates all
	 * of its showig status.
	 * 
	 * update synopsis with id, all movies with same title has synposis updated
	 * update director with id, all movies with same title has director updated
	 * update movie cast with id all movies with the same title is updated with
	 * an arraylist of strings representing cast members names.
	 * 
	 * reviews and ratings are updated withh an arraylist of strings and integers respectively,
	 * similarly using a movieid to find a movie and finding all other movies who has the same
	 * title as that.
	 */
	public void updateMovie() {
		System.out.println("1. Update movie ID"
				+ "\n2. Update movie title"
				+ "\n3. Update movie type"
				+ "\n4. Update movie language"
				+ "\n5. Update movie duration"
				+ "\n6. Update movie showing status"
				+ "\n7. Update movie synopsis"
				+ "\n8. Update movie director"
				+ "\n9. Update movie cast"
				+ "\n10. Update movie reviews"
				+ "\n11. Update movie ratings"
				+ "\n12. Exit");
		int choice;
		do {
			int movieID1, movieID2, value;
			String str;
			choice = InputManager.getInt(1,12);
			switch(choice) {
			case 1:
				System.out.println("Enter the old Movie ID to be replaced: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new Movie ID to replace it: ");
				movieID2 = InputManager.getInt();
				if(movieMgr.updateMovieID(movieID1, movieID2))
					System.out.println("Successful!ly updated!");
				else
					System.out.println("Failed to update!");
					
				break;
			case 2:
				System.out.println("Enter the old Movie ID to be replaced: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new Movie title to replace it:");
				str = InputManager.getString();
				movieMgr.updateMovieTitle(movieMgr.findMovie(movieID1).getMovieTitle(), str);
				break;
			case 3:
				System.out.println("Enter the Movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new movie type,\n1.BLOCKBUSTER_2D, 2.BLOCKBUSTER_3D,"
						+ " 3.NONBLOCKBUSTER_2D,"
						+ " 4.BLOCKBUSTER_3D");
				int typeInt = InputManager.getInt(1,4);
				movieMgr.updateMovieType(movieID1, TypeOfMovie.values()[typeInt-1]);
				break;
			case 4:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new dubbed language: ");
				str = InputManager.getString();
				movieMgr.updateMovieLanguage(movieID1, str);
				break;
			case 5:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new duration in minutes.");
				value = InputManager.getInt();
				movieMgr.updateMovieDuration(movieMgr.findMovie(movieID1).getMovieTitle(), value);
				break;
			case 6:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new showing status,");
				System.out.println("1.COMING_SOON, 2.PREVIEW, 3.NOW_SHOWING, 4.END_OF_SHOWING");
				int statusInt = InputManager.getInt(1, 4);
				movieMgr.updateMovieShowingStatus(movieMgr.findMovie(movieID1).getMovieTitle(), ShowingStatus.values()[statusInt-1]);
				break;
			case 7:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the the new synopsis.");
				str = InputManager.getString();
				movieMgr.updateMovieSynopsis(movieMgr.findMovie(movieID1).getMovieTitle(), str);
				break;
			case 8:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new director");
				str = InputManager.getString();
				movieMgr.updateMovieDirector(movieMgr.findMovie(movieID1).getMovieTitle(), str);
				break;
			case 9:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new cast members: \n Input -1 to end.");
				ArrayList<String> cast = new ArrayList<String>();
				while(true) {
					str = InputManager.getString();
					if(str.equals("-1"))
						break;
					else
						cast.add(str);
				}
				movieMgr.updateMovieCast(movieMgr.findMovie(movieID1).getMovieTitle(), cast);
				break;
			case 10:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new reviews, enter -1 to end.");
				ArrayList<String> reviews = new ArrayList<String>();
				while(true) {
					str = InputManager.getString();
					if(str.equals("-1"))
						break;
					else
						reviews.add(str);
				}
				movieMgr.updateMovieReview(movieMgr.findMovie(movieID1).getMovieTitle(), reviews);
				break;
			case 11:
				System.out.println("Enter the movie ID: ");
				movieID1 = InputManager.getInt();
				System.out.println("Enter the new ratings 1-5, enter 0 to stop.");
				ArrayList<Integer> ratings = new ArrayList<Integer>();
				while(true) {
					int r = InputManager.getInt(0, 5);
					if(r == 0)
						break;
					else
						ratings.add(r);
				}
				movieMgr.updateMovieRating(movieMgr.findMovie(movieID1).getMovieTitle(), ratings);
				break;
			default:
				break;
				
			}
			
			if(choice != 12) {
				System.out.println("1. Update movie ID"
						+ "\n2. Update movie title"
						+ "\n3. Update movie type"
						+ "\n4. Update movie language"
						+ "\n5. Update movie duration"
						+ "\n6. Update movie showing status"
						+ "\n7. Update movie synopsis"
						+ "\n8. Update movie director"
						+ "\n9. Update movie cast"
						+ "\n10. Update movie reviews"
						+ "\n11. Update movie ratings"
						+ "\n12. Exit");
			}
		}while(choice!=12);

	}
	
	/**
	 * Removes the movie based off its movieID. 
	 */
	public void removeMovie() {
		System.out.println("Enter the movie ID to remove");
		boolean deleted = movieMgr.deleteMovie(InputManager.getInt());
		if(deleted)
			System.out.println("Successfully removed");
		else
			System.out.println("Could not find the movie ID");
	}
	
	
}
