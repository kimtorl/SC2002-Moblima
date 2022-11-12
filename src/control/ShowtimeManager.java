package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showtime;

public interface ShowtimeManager {
	//reads and return all Showtime available from all Cineplexes in an ArrayList
		public ArrayList<Showtime> getAllShowtime();
			
		
		//returns true if successfully created
		//returns false if failed
		public boolean createShowtime(int cineplexID, String cinemaCode, Movie movie, LocalDateTime dateTime);
			
		//checks if showtime clashes with showtime to be added
		//Showtimes within a cinema cannot clash as one cinema can only show one movie at a time
		public boolean hasClashes(ArrayList<Showtime> showtimeList, Movie movie, LocalDateTime dateTime);
		
		//Showtimes within a cineplex should not have duplicates
		//i.e. A cineplex shouldn't have two showtimes with the same Movie and dateTime across cinemas
		//cinemaList contains cinemas from the same Cineplex
		public boolean isDuplicate(ArrayList<Cinema> cinemaList, Movie movie, LocalDateTime dateTime);
		
		
		//return all the showtime Available for a movie across cineplexes
		public ArrayList<Showtime> findAllShowtimeByMovie(int movieID);
		
		//return all the showtime available by given date across cineplexes
		public ArrayList<Showtime> findAllShowtimeByDate(LocalDate date);
		
		
		//returns the corresponding showtime
		//returns null if not found
		public Showtime findShowtime(ArrayList<Cineplex> cineplexList, int cineplexID, String cinemaCode, LocalDateTime dateTime);
		
		//updates a showtime with a new movie and save to file
		//the correct showtime to be updated is found using dateTime as its unique in a cinema
		public boolean updateShowtimeMovie(int cineplexID, String cinemaCode, LocalDateTime dateTime, Movie newMovie);
		
		
		//updates a showtime with a new dateTime and save to file
		public boolean updateShowtimeDateTime(int cineplexID, String cinemaCode, LocalDateTime oldDateTime, LocalDateTime newDateTime);
		
		
		//deletes all showtimes in a cinema that has this movieID
		public boolean deleteShowTimeByMovieID(int cineplexID, String cinemaCode, int movieID);
		
		
		//deletes a specific showtime in a cinema specified by dateTime
		public boolean deleteSpecificShowtime(int cineplexID, String cinemaCode, LocalDateTime dateTime);
		
		public void populateAllCinemaShowtime();
		
}
