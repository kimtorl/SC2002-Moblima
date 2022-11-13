/*
 * 
 */
package entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Cross
 * Showtime class is a specific movie at a specific cinema and time with its seat layout
 *
 */
public class Showtime implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	//Attributes
	/**
	 * the cinemaCode of the Showtime
	 */
	private String cinemaCode;
	/**
	 * the Movie of the Showtime
	 */
	private Movie movie;
	/**
	 * date and time of the Showtime
	 */
	private LocalDateTime dateTime;
	/**
	 * the seatLayout of the Showtime
	 */
	private SeatLayout seatLayout;
	
	

	/**
	 * Instantiates a new showtime.
	 *
	 * @param cinemaCode the cinemaCode of the Showtime
	 * @param movie the Movie of the Showtime
	 * @param dateTime date and time of the Showtime
	 * @param seatLayout the seatLayout of the Showtime
	 */
	public Showtime(String cinemaCode, Movie movie, LocalDateTime dateTime, SeatLayout seatLayout) {
		this.cinemaCode = cinemaCode;
		this.movie = movie;
		this.dateTime = dateTime;
		this.seatLayout = seatLayout;
	}

	
	
	/**
	 * checks if this Showtime is on a weekend i.e. Fri 6pm to Sun
	 * @return true if its Fri 6pm to Sun, else return false
	 */
	public boolean isWeekend() {
		DayOfWeek day = dateTime.getDayOfWeek();
		if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || 
				(day == DayOfWeek.FRIDAY && dateTime.getHour()>=18)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * convert the LocalDateTime to a formatted String 
	 * @return a String of dateTime based on a given format
	 */
	public String dateTimeToString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dateTime.format(format);
	}
	
	
	
	/**
	 * gets the CinemaCode
	 * @return a String which is the cinemaCode 
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}



	/**
	 * Sets the cinemaCode of the Showtime.
	 *
	 * @param cinemaCode the new cinemaCode of the Showtime
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	/**
	 * gets Movie
	 * @return a Movie object
	 */
	public Movie getMovie() {
		return movie;
	}



	/**
	 * Sets the Movie of the Showtime.
	 *
	 * @param movie the new Movie of the Showtime
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	/**
	 * gets the Date and Time
	 * @return a LocalDateTime object
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}



	/**
	 * Sets the date and time of the Showtime.
	 *
	 * @param dateTime the new date and time of the Showtime
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	/**
	 * gets the seatLayout attribute
	 * @return a SeatLayout object
	 */
	public SeatLayout getSeatLayout() {
		return seatLayout;
	}


	/**
	 * sets the seatLayout
	 * @param seatLayout
	 */
	public void setSeatLayout(SeatLayout seatLayout) {
		this.seatLayout = seatLayout;
	}
	
	
	
	
	
	
}
