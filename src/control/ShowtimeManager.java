/*
 * 
 */
package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.SeatLayout;
import entity.Showtime;

/**
 * The Interface ShowtimeManager.
 */
public interface ShowtimeManager {
	
	/**
	 * Gets the all showtime.
	 *
	 * @return the all showtime
	 */
	//reads and return all Showtime available from all Cineplexes in an ArrayList
		public ArrayList<Showtime> getAllShowtime();
			
		
		//returns true if successfully created
		/**
		 * Creates the showtime.
		 *
		 * @param cineplexID the cineplex ID
		 * @param cinemaCode the cinema code
		 * @param movie the movie
		 * @param dateTime the date time
		 * @return true, if successful
		 */
		//returns false if failed
		public boolean createShowtime(int cineplexID, String cinemaCode, Movie movie, LocalDateTime dateTime);
			
		//checks if showtime clashes with showtime to be added
		/**
		 * Checks for clashes.
		 *
		 * @param showtimeList the showtime list
		 * @param movie the movie
		 * @param dateTime the date time
		 * @return true, if successful
		 */
		//Showtimes within a cinema cannot clash as one cinema can only show one movie at a time
		public boolean hasClashes(ArrayList<Showtime> showtimeList, Movie movie, LocalDateTime dateTime);
		
		//Showtimes within a cineplex should not have duplicates
		//i.e. A cineplex shouldn't have two showtimes with the same Movie and dateTime across cinemas
		/**
		 * Checks if is duplicate.
		 *
		 * @param cinemaList the cinema list
		 * @param movie the movie
		 * @param dateTime the date time
		 * @return true, if is duplicate
		 */
		//cinemaList contains cinemas from the same Cineplex
		public boolean isDuplicate(ArrayList<Cinema> cinemaList, Movie movie, LocalDateTime dateTime);
		
		
		/**
		 * Find all showtime by movie.
		 *
		 * @param movieID the movie ID
		 * @return the array list
		 */
		//return all the showtime Available for a movie across cineplexes
		public ArrayList<Showtime> findAllShowtimeByMovie(int movieID);
		
		/**
		 * Find all showtime by date.
		 *
		 * @param date the date
		 * @return the array list
		 */
		//return all the showtime available by given date across cineplexes
		public ArrayList<Showtime> findAllShowtimeByDate(LocalDate date);
		
		
		//returns the corresponding showtime
		/**
		 * Find showtime.
		 *
		 * @param cineplexList the cineplex list
		 * @param cineplexID the cineplex ID
		 * @param cinemaCode the cinema code
		 * @param dateTime the date time
		 * @return the showtime
		 */
		//returns null if not found
		public Showtime findShowtime(ArrayList<Cineplex> cineplexList, int cineplexID, String cinemaCode, LocalDateTime dateTime);
		
		//updates a showtime with a new movie and save to file
		/**
		 * Update showtime movie.
		 *
		 * @param cineplexID the cineplex ID
		 * @param cinemaCode the cinema code
		 * @param dateTime the date time
		 * @param newMovie the new movie
		 * @return true, if successful
		 */
		//the correct showtime to be updated is found using dateTime as its unique in a cinema
		public boolean updateShowtimeMovie(int cineplexID, String cinemaCode, LocalDateTime dateTime, Movie newMovie);
		
		
		/**
		 * Update showtime date time.
		 *
		 * @param cineplexID the cineplex ID
		 * @param cinemaCode the cinema code
		 * @param oldDateTime the old date time
		 * @param newDateTime the new date time
		 * @return true, if successful
		 */
		//updates a showtime with a new dateTime and save to file
		public boolean updateShowtimeDateTime(int cineplexID, String cinemaCode, LocalDateTime oldDateTime, LocalDateTime newDateTime);
		
		/**
		 * Update showtime seat layout.
		 *
		 * @param cinemaCode the cinema code
		 * @param dateTime the date time
		 * @param seatLayout the seat layout
		 * @return true, if successful
		 */
		//update a showtime with a new seatLayout and save to file
		public boolean updateShowtimeSeatLayout(String cinemaCode, LocalDateTime dateTime, SeatLayout seatLayout);
		
		
		/**
		 * Delete show time by movie ID.
		 *
		 * @param cineplexID the cineplex ID
		 * @param cinemaCode the cinema code
		 * @param movieID the movie ID
		 * @return true, if successful
		 */
		//deletes all showtimes in a cinema that has this movieID
		public boolean deleteShowTimeByMovieID(int cineplexID, String cinemaCode, int movieID);
		
		
		/**
		 * Delete specific showtime.
		 *
		 * @param cineplexID the cineplex ID
		 * @param cinemaCode the cinema code
		 * @param dateTime the date time
		 * @return true, if successful
		 */
		//deletes a specific showtime in a cinema specified by dateTime
		public boolean deleteSpecificShowtime(int cineplexID, String cinemaCode, LocalDateTime dateTime);
		
		/**
		 * Populate all cinema showtime.
		 *
		 * @param dateTime the date time
		 */
		public void populateAllCinemaShowtime(LocalDateTime dateTime);
		
}
