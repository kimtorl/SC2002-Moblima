package boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import control.InputManager;
import control.MovieManager;
import control.ShowtimeManager;

public class EditMovieShowtime implements Capability, Serializable{

	private static final long serialVersionUID = 31L;

	private ShowtimeManager showtimeMgr;
	private MovieManager movieMgr;
	
	public EditMovieShowtime(ShowtimeManager showtimeMgr, MovieManager movieMgr) {
		this.showtimeMgr = showtimeMgr;
		this.movieMgr = movieMgr;
	}
	
	@Override
	public void performCapability() {
		int choice;
		
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. Add a movie Showtime");
			System.out.println("2. Update a movie Showtime's movie");
			System.out.println("3. Update a movie Showtime's dateTime");
			System.out.println("4. Remove a movie Showtime");
			System.out.println("5. Remove all Showtimes of a movie");
			System.out.println("6. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 6);
			
			switch(choice) {
			case 1:
				createShowtime();
				break;
			case 2:
				updateShowtimeMovie();
				break;
			case 3:
				updateShowtimeDateTime();
				break;
			case 4:
				removeSpecificShowtime();
				break;
			case 5:
				removeAllShowtimeOfMovie();
			default:
					
			}
			
		}while(choice != 6);
		
		

	}
	
	
	public String toString() {
		String str = "Edit Movie Showtime";
		return str;
	}
	
	public void createShowtime() {
		System.out.println("Enter cineplexID: ");
		int cineplexID = InputManager.getInt();
		
		System.out.println("Enter cinema code: ");
		String cinemaCode = InputManager.getString();
		
		System.out.println("Enter movieID: ");
		int movieID = InputManager.getInt();
		
		LocalDateTime dateTime = InputManager.getLocalDateTime();
		
		if(showtimeMgr.createShowtime(cineplexID, cinemaCode, movieMgr.findMovie(movieID), dateTime))
			System.out.println("Showtime successfully created!");
		else
			System.out.println("Please try again!");
		
	}
	
	public void updateShowtimeMovie() {
		System.out.println("Enter cineplexID: ");
		int cineplexID = InputManager.getInt();
		
		System.out.println("Enter cinema code: ");
		String cinemaCode = InputManager.getString();
		
		LocalDateTime dateTime = InputManager.getLocalDateTime();
		
		System.out.println("Enter new movieID: ");
		int newMovieID = InputManager.getInt();
		
		if(showtimeMgr.updateShowtimeMovie(cineplexID, cinemaCode, dateTime, movieMgr.findMovie(newMovieID)))
			System.out.println("Showtime successfully updated!");
		else
			System.out.println("Please try again!");
		
	}
	
	public void updateShowtimeDateTime() {
		System.out.println("Enter cineplexID: ");
		int cineplexID = InputManager.getInt();
		
		System.out.println("Enter cinema code: ");
		String cinemaCode = InputManager.getString();
		
		System.out.println("Current Showtime date and time?");
		LocalDateTime dateTime = InputManager.getLocalDateTime();
		
		System.out.println("New date and time for Showtime? ");
		LocalDateTime newDateTime = InputManager.getLocalDateTime();
		
		
		if(showtimeMgr.updateShowtimeDateTime(cineplexID, cinemaCode, dateTime, newDateTime))
			System.out.println("Showtime successfully updated!");
		else
			System.out.println("Please try again!");
	}
	
	public void removeSpecificShowtime() {
		System.out.println("Enter cineplexID: ");
		int cineplexID = InputManager.getInt();
		
		System.out.println("Enter cinema code: ");
		String cinemaCode = InputManager.getString();
		
		System.out.println("Date and time of Showtime to be deleted");
		LocalDateTime dateTime = InputManager.getLocalDateTime();
	
		
		if(showtimeMgr.deleteSpecificShowtime(cineplexID, cinemaCode, dateTime))
			System.out.println("Showtime successfully deleted!");
		else
			System.out.println("Please try again!");	
	}
	
	
	public void removeAllShowtimeOfMovie() {
		System.out.println("Enter cineplexID: ");
		int cineplexID = InputManager.getInt();
		
		System.out.println("Enter cinema code: ");
		String cinemaCode = InputManager.getString();
		
		System.out.println("Enter movieID: ");
		int movieID = InputManager.getInt();
		
		if(showtimeMgr.deleteShowTimeByMovieID(cineplexID, cinemaCode, movieID))
			System.out.println("Showtime successfully deleted!");
		else
			System.out.println("Please try again!");	
	
	}
	
}
