package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	//Attributes
	private String cinemaCode;
	private ClassOfCinema cinemaClass;
	private ArrayList<Showtime> showtimeList;
	
	//Constructor
	public Cinema(String cinemaCode, ClassOfCinema cinemaClass, ArrayList<Showtime> showtimeList) {
		this.cinemaCode = cinemaCode;
		this.cinemaClass = cinemaClass;
		this.showtimeList = showtimeList;
	}

	
	//getters and setters
	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public ClassOfCinema getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(ClassOfCinema cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

	public ArrayList<Showtime> getShowtimeList() {
		return showtimeList;
	}

	public void setShowtimeList(ArrayList<Showtime> showtimeList) {
		this.showtimeList = showtimeList;
	}
	
	
}
