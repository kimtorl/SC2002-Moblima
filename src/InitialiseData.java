import control.*;
import boundary.*;
import entity.*;
import java.time.*;
import java.util.*;


public class InitialiseData {

	public static void main(String[] args) {
		
		//creates 3 Cineplex
		CineplexFileManager.createCineplex(0, "Jem", null);
		CineplexFileManager.createCineplex(1, "Cineleisure Orchard", null);
		CineplexFileManager.createCineplex(2, "AMK Hub", null);
		
		//creates 3 cinema for each Cineplex
		CinemaFileManager.createCinema(0, "C00", ClassOfCinema.STANDARD, null);
		CinemaFileManager.createCinema(0, "C01", ClassOfCinema.STANDARD, null);
		CinemaFileManager.createCinema(0, "C02", ClassOfCinema.PLATINUM, null);
		CinemaFileManager.createCinema(1, "C10", ClassOfCinema.STANDARD, null);
		CinemaFileManager.createCinema(1, "C11", ClassOfCinema.STANDARD, null);
		CinemaFileManager.createCinema(1, "C12", ClassOfCinema.PLATINUM, null);
		CinemaFileManager.createCinema(2, "C20", ClassOfCinema.STANDARD, null);
		CinemaFileManager.createCinema(2, "C21", ClassOfCinema.STANDARD, null);
		CinemaFileManager.createCinema(2, "C22", ClassOfCinema.PLATINUM, null);
		
		//creates Showtimes for each Cinema based on current System time
		LocalDateTime now = LocalDateTime.now();
		
		//get List of movies
		//MovieFileManager.getAllMovies();
		
		ShowtimeFileManager.createShowtime(0, "C00", null, now);
		
		
		
		
		
		
		
	}//end of main
	
	
	
}
