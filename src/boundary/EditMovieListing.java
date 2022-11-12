package boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import control.InputManager;
import control.MovieManager;
import entity.ShowingStatus;
import entity.TypeOfMovie;

public class EditMovieListing implements Capability, Serializable {

	private static final long serialVersionUID = 30L;
	private static Scanner sc = new Scanner(System.in);
	private MovieManager movieMgr;
	
	public EditMovieListing(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

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
	
	public String toString() {
		String str = "Edit Movie Listing";
		return str;
	}
	
	public void listMovie() {
		System.out.println("------------------------------");
		movieMgr.listAllMovies();
		System.out.println("------------------------------");
	}
	
	
	public void createMovie() {
		System.out.println("Enter the Movie information");
		System.out.println("ID: ");
		int id = sc.nextInt();
		if(movieMgr.findMovie(id)!=null) {
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
		ArrayList<String> cast = new ArrayList<String>();
		while(true) {
			String dog = InputManager.getString();
			if(dog.equals("-1"))
				break;
			else
				cast.add(dog);
		}
		//
		System.out.println("Past Reviews: \nInput -1 to end");
		ArrayList<String> reviews = new ArrayList<String>();
		while(true) {
			String cat = InputManager.getString();
			if(cat.equals("-1"))
				break;
			else
				reviews.add(cat);
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
		movieMgr.createMovie(id, title, type, language, duration, status, synopsis, director, cast, reviews, ratings);
	}
	
	
	
	
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
			choice = InputManager.getInt(1,12);
			switch(choice) {
			case 1:
				System.out.println("Enter the old Movie ID to be replaced followed by the new Movie ID to replace it.");
				if(movieMgr.updateMovieID(InputManager.getInt(), InputManager.getInt()))
					System.out.println("Successful!ly updated!");
				else
					System.out.println("Failed to update!");
					
				break;
			case 2:
				System.out.println("Enter the old Movie title to be replaced followed by the new Movie title to replace it.");
				movieMgr.updateMovieTitle(InputManager.getString(), InputManager.getString());
				break;
			case 3:
				System.out.println("Enter the Movie ID followed by the new movie type,\n1.BLOCKBUSTER_2D, 2.BLOCKBUSTER_3D,"
						+ " 3.NONBLOCKBUSTER_2D,"
						+ " 4.BLOCKBUSTER_3D");
				//int typeInt = InputManager.getInt(1,4);
				movieMgr.updateMovieType(InputManager.getInt(), TypeOfMovie.values()[InputManager.getInt(1,4)-1]);
				break;
			case 4:
				System.out.println("Enter movie ID followed by the new dubbed language.");
				movieMgr.updateMovieLanguage(InputManager.getInt(), InputManager.getString());
				break;
			case 5:
				System.out.println("Enter the title of the movie followed by the new duration in minutes.");
				movieMgr.updateMovieDuration(InputManager.getString(), InputManager.getInt());
				break;
			case 6:
				System.out.println("Enter movie title followed by the new showing status,");
				System.out.println("1.COMING_SOON, 2.PREVIEW, 3.NOW_SHOWING, 4.END_OF_SHOWING");
				int statusInt = InputManager.getInt(1, 4);
				movieMgr.updateMovieShowingStatus(InputManager.getString(), ShowingStatus.values()[statusInt-1]);
				break;
			case 7:
				System.out.println("Enter the movie title followed by the new synopsis.");
				movieMgr.updateMovieSynopsis(InputManager.getString(),InputManager.getString());
				break;
			case 8:
				System.out.println("Enter the movie title followed by the new director");
				movieMgr.updateMovieDirector(InputManager.getString(), InputManager.getString());
				break;
			case 9:
				System.out.println("Enter movie title: ");
				String title = InputManager.getString();
				System.out.println("Enter the new cast members: \n Input -1 to end.");
				ArrayList<String> cast = new ArrayList<String>();
				while(true) {
					String dog = InputManager.getString();
					if(dog.equals("-1"))
						break;
					else
						cast.add(dog);
				}
				movieMgr.updateMovieCast(title, cast);
				break;
			case 10:
				System.out.println("Enter movie title: ");
				String title2 = InputManager.getString();
				System.out.println("Enter the new reviews, enter -1 to end.");
				ArrayList<String> reviews = new ArrayList<String>();
				while(true) {
					String cat = InputManager.getString();
					if(cat.equals("-1"))
						break;
					else
						reviews.add(cat);
				}
				movieMgr.updateMovieReview(title2, reviews);
				break;
			case 11:
				System.out.println("Enter movie title: ");
				String title3 = InputManager.getString();
				System.out.println("Enter the new ratings 1-5, enter 0 to stop.");
				ArrayList<Integer> ratings = new ArrayList<Integer>();
				while(true) {
					int r = InputManager.getInt(0, 5);
					if(r == 0)
						break;
					else
						ratings.add(r);
				}
				movieMgr.updateMovieRating(title3, ratings);
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
	
	public void removeMovie() {
		System.out.println("Enter the movie ID to remove");
		boolean deleted = movieMgr.deleteMovie(InputManager.getInt());
		if(deleted)
			System.out.println("Successfully removed");
		else
			System.out.println("Could not find the movie ID");
	}
	
	
}
