package entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Showtime implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	//Attributes
	private String cinemaCode;
	private Movie movie;
	private LocalDateTime dateTime;
	private SeatLayout seatLayout;
	
	
	//Constructor
	public Showtime(String cinemaCode, Movie movie, LocalDateTime dateTime, SeatLayout seatLayout) {
		this.cinemaCode = cinemaCode;
		this.movie = movie;
		this.dateTime = dateTime;
		this.seatLayout = seatLayout;
	}

	
	//checks if this Showtime is on a weekend i.e. Fri 6pm to Sun
	public boolean isWeekend() {
		DayOfWeek day = dateTime.getDayOfWeek();
		if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || 
				(day == DayOfWeek.FRIDAY && dateTime.getHour()>=18)) {
			return true;
		}
		
		return false;
	}
	
	public String dateTimeToString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return dateTime.format(format);
	}
	
	
	//getters and setters
	public String getCinemaCode() {
		return cinemaCode;
	}


	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public SeatLayout getSeatLayout() {
		return seatLayout;
	}


	public void setSeatLayout(SeatLayout seatLayout) {
		this.seatLayout = seatLayout;
	}
	
	
	
	
	
	
}
