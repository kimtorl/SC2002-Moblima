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
	
	
	//Constructor
	/**
	 * @param cinemaCode
	 * @param movie
	 * @param dateTime
	 * @param seatLayout
	 */
	public Showtime(String cinemaCode, Movie movie, LocalDateTime dateTime, SeatLayout seatLayout) {
		this.cinemaCode = cinemaCode;
		this.movie = movie;
		this.dateTime = dateTime;
		this.seatLayout = seatLayout;
	}

	
	
	/**
	 * checks if this Showtime is on a weekend i.e. Fri 6pm to Sun
	 * @return
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
	 * prints out the date and time to in a specific format as a string
	 * @return
	 */
	public String dateTimeToString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dateTime.format(format);
	}
	
	
	
	/**
	 * gets the CinemaCode
	 * @return
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}


	/**
	 * sets CinemaCode
	 * @param cinemaCode
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	/**
	 * gets Movie
	 * @return
	 */
	public Movie getMovie() {
		return movie;
	}


	/**
	 * sets Movie
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	/**
	 * gets the Date and Time
	 * @return
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}


	/**
	 * sets the Date and Time
	 * @param dateTime
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	/**
	 * gets the seatLayout
	 * @return
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
