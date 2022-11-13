/*
 * 
 */
package control;


import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;

public interface CineplexManager {

	/**
	 * Gets the all cineplex.
	 *
	 * @return the all cineplex
	 */
	//reads and return an ArrayList of Cineplex from the file
	public ArrayList<Cineplex> getAllCineplex();
	
	/**
	 * Write to file.
	 *
	 * @param cineplexList the cineplex list
	 */
	//writes an ArrayList of Cineplex to the file
	public void writeToFile(ArrayList<Cineplex> cineplexList);
	
	/**
	 * Creates the cineplex.
	 *
	 * @param cineplexID the cineplex ID
	 * @param cineplexName the cineplex name
	 * @param cinemaList the cinema list
	 * @return true, if successful
	 */
	//creates and save the cineplex into the file
	public boolean createCineplex(int cineplexID, String cineplexName, ArrayList<Cinema> cinemaList);
	
	
	
	/**
	 * Delete cineplex.
	 *
	 * @param cineplexName the cineplex name
	 * @return true, if successful
	 */
	//deletes a cineplex using the cineplexName
	public boolean deleteCineplex(String cineplexName);
	
	
}
