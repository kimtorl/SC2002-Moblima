/*
 * 
 */
package control;

import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.ClassOfCinema;
import entity.Showtime;

/**
 * The Interface CinemaManager. Manages, access and modifies Cinema files.
 */
public interface CinemaManager {

	/**
	 * Gets the all cinema.
	 * It reads and returns all the Cinema in an ArrayList
	 * @return the all cinema
	 */
	//reads and returns all the Cinema in an ArrayList
	public ArrayList<Cinema> getAllCinema();
		
	//reads and return a specific cinema specified by the cinemaCode from a given cineplexList
	/**
	 * Finds cinema.
	 * reads and return a specific cinema specified by the cinemaCode from a given cineplexList
	 * returns null if not found
	 * @param cineplexList the cineplex list
	 * @param cineplexID the cineplex ID
	 * @param cinemaCode the cinema code
	 * @return the cinema
	 */
	//returns null if not found
	public Cinema findCinema(ArrayList<Cineplex> cineplexList,int cineplexID, String cinemaCode);
	
	/**
	 * Finds cinema based on its cinemaCode
	 *
	 * @param cinemaCode the cinema code
	 * @return the cinema
	 */
	//overloaded method
	public Cinema findCinema(String cinemaCode);
	
	
	/**
	 * Creates a single Cinema object for a specific cineplex, with its cinemaCOde
	 * cinemaClass, and showtimes
	 *
	 * @param cineplexID the cineplex ID
	 * @param cinemaCode the cinema code
	 * @param cinemaClass the cinema class
	 * @param showtime the showtime
	 * @return true, if successful
	 */
	//creates a single Cinema object for a specific cineplex
	public boolean createCinema(int cineplexID, String cinemaCode, ClassOfCinema cinemaClass, ArrayList<Showtime> showtime);
	
	
	//deletes a cinema using cinemaCode string
	//return false if failed to delete
	/**
	 * deletes a cinema using cinemaCode string
	 * return false if failed to delete
	 * @param cineplexID the cineplex ID
	 * @param cinemaCode the cinema code
	 * @return true, if successful
	 */
	//return true if deleted successfully
	public boolean deleteCinema(int cineplexID, String cinemaCode);
	
	//returns the index of the cinema that matches the cinemaCode
	/**
	 * returns the index of the cinema that matches the cinemaCode
	 *
	 * @param cinemaList the cinema list
	 * @param cinemaCode the cinema code
	 * @return the int
	 */
	//returns -1 if not found
	public int findCinemaIndex(ArrayList<Cinema> cinemaList,String cinemaCode);
	
	/**
	 * Write to file.
	 *
	 * @param cineplexList the cineplex list
	 */
	public void writeToFile(ArrayList<Cineplex> cineplexList);
	
	/**
	 * Gets the all cineplex by reading from an ArrayList of cineplexes
	 *
	 * @return the all cineplex
	 */
	public ArrayList<Cineplex> getAllCineplex();

}
