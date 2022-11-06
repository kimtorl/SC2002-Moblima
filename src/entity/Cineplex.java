package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Attributes
	private int cineplexID;
	private String cineplexName;
	private ArrayList<Cinema> cinemaList;
	
	
	//Constructor
	public Cineplex(int cineplexID, String cineplexName, ArrayList<Cinema> cinemaList) {
		this.cineplexID = cineplexID;
		this.cineplexName = cineplexName;
		this.cinemaList = cinemaList;
	}


	//toString
	public String toString() {
		return cineplexName + " CineplexID: " + cineplexID;
	}
	
	//getters and setters
	public int getCineplexID() {
		return cineplexID;
	}


	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}


	public String getCineplexName() {
		return cineplexName;
	}


	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}


	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}


	public void setCinemaList(ArrayList<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}
	
	
	
}
