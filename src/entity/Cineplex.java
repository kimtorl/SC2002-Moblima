/*
 * Cineplex class represents a Cathay Cineplex building and the list of cinema represents the cinema halls.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Cross
 * Relating to cineplex, can get and set the ID and names, as well as the 
 * cinemaList, or anything to do with the information of cineplex.
 */
public class Cineplex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The cineplex ID. */
	//Attributes
	private int cineplexID;
	
	/** The cineplex name. */
	private String cineplexName;
	
	/** The cinema list. */
	private ArrayList<Cinema> cinemaList;
	
	
	/**
	 * Instantiates a new cineplex.
	 *
	 * @param cineplexID the cineplex ID
	 * @param cineplexName the cineplex name
	 * @param cinemaList the cinema list
	 */
	//Constructor
	public Cineplex(int cineplexID, String cineplexName, ArrayList<Cinema> cinemaList) {
		this.cineplexID = cineplexID;
		this.cineplexName = cineplexName;
		this.cinemaList = cinemaList;
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	//toString
	public String toString() {
		return cineplexName + " CineplexID: " + cineplexID;
	}
	
	/**
	 * Gets the cineplex ID.
	 *
	 * @return the cineplex ID
	 */
	//getters and setters
	public int getCineplexID() {
		return cineplexID;
	}


	/**
	 * Sets the cineplex ID.
	 *
	 * @param cineplexID the new cineplex ID
	 */
	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}


	/**
	 * Gets the cineplex name.
	 *
	 * @return the cineplex name
	 */
	public String getCineplexName() {
		return cineplexName;
	}


	/**
	 * Sets the cineplex name.
	 *
	 * @param cineplexName the new cineplex name
	 */
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}


	/**
	 * Gets the cinema list.
	 *
	 * @return the cinema list
	 */
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}


	/**
	 * Sets the cinema list.
	 *
	 * @param cinemaList the new cinema list
	 */
	public void setCinemaList(ArrayList<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}
	
	
	
}
