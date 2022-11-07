package entity;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Showtime implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	//Attributes
	private Movie movie;
	private LocalDateTime dateTime;
	private SeatLayout seatLayout;
	
	
	//Constructor
	public Showtime(Movie movie, LocalDateTime dateTime, SeatLayout seatLayout) {
		this.movie = movie;
		this.dateTime = dateTime;
		this.seatLayout = seatLayout;
	}

	
	//checks if this Showtime is on a weekend i.e. Fri 6pm to Sun
	public boolean isWeekend() {
		DayOfWeek day = dateTime.getDayOfWeek();
		System.out.println(day);
		if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || 
				(day == DayOfWeek.FRIDAY && dateTime.getHour()>=18)) {
			return true;
		}
		
		return false;
	}
	
	//getters and setters
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
