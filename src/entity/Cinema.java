/*
 * 
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2L;
	
	/** The cinema code. */
	//Attributes
	private String cinemaCode; //cinemaCode is unique across Cineplexes
	
	/** The cinema class. */
	private ClassOfCinema cinemaClass;
	
	/** The showtime list. */
	private ArrayList<Showtime> showtimeList;
	
	/**
	 * Instantiates a new cinema.
	 *
	 * @param cinemaCode the cinema code.
	 * @param cinemaClass the cinema class.
	 * @param showtimeList the showtime list.
	 */
	//Constructor
	public Cinema(String cinemaCode, ClassOfCinema cinemaClass, ArrayList<Showtime> showtimeList) {
		this.cinemaCode = cinemaCode;
		this.cinemaClass = cinemaClass;
		this.showtimeList = showtimeList;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Cinema Code: " + cinemaCode + " Cinema Class: " + cinemaClass;
	}
	
	
	/**
	 * Gets the cinema code.
	 *
	 * @return the cinema code
	 */
	//getters and setters
	public String getCinemaCode() {
		return cinemaCode;
	}

	/**
	 * Sets the cinema code.
	 *
	 * @param cinemaCode the new cinema code
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	/**
	 * Gets the cinema class.
	 *
	 * @return the cinema class
	 */
	public ClassOfCinema getCinemaClass() {
		return cinemaClass;
	}

	/**
	 * Sets the cinema class.
	 *
	 * @param cinemaClass the new cinema class
	 */
	public void setCinemaClass(ClassOfCinema cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

	/**
	 * Gets the showtime list.
	 *
	 * @return the showtime list
	 */
	public ArrayList<Showtime> getShowtimeList() {
		return showtimeList;
	}

	/**
	 * Sets the showtime list.
	 *
	 * @param showtimeList the new showtime list
	 */
	public void setShowtimeList(ArrayList<Showtime> showtimeList) {
		this.showtimeList = showtimeList;
	}
	
	
}
