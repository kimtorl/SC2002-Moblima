/*
 * 
 */
package control;


import java.io.Serializable;
import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.ClassOfCinema;
import entity.Showtime;

public class CinemaFileManager implements CinemaManager, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 22L;
	
	/** The cineplex mgr. */
	//Attributes
	private CineplexManager cineplexMgr;
	
	
	/**
	 * Instantiates a new cinema file manager.
	 *
	 * @param cineplexMgr the cineplex manager
	 */
	//Constructor
	public CinemaFileManager(CineplexManager cineplexMgr) {
		this.cineplexMgr = cineplexMgr;
	}
	
	
	/**
	 * Gets the all cinema by reading and 
	 * returning all the Cinema in an ArrayList of Cinema
	 *
	 * @return the all cinema
	 */
	//reads and returns all the Cinema in an ArrayList
	public ArrayList<Cinema> getAllCinema(){
		ArrayList<Cineplex> cineplexList = cineplexMgr.getAllCineplex();
		
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
		
		for(int i=0; i < cineplexList.size(); i++) {
			if(cineplexList.get(i).getCinemaList()!= null)
				cinemaList.addAll(cineplexList.get(i).getCinemaList()); //append each cinemaList to a single ArrayList
		}
		
		return cinemaList;	
	}
	
	
	//reads and return a specific cinema specified by the cinemaCode from a given cineplexList
	/**
	 * Find cinema.
	 * reads and return a specific cinema specified by the cinemaCode from a given cineplexList
	 * returns null if not found
	 * @param cineplexList the cineplex list
	 * @param cineplexID the cineplex ID
	 * @param cinemaCode the cinema code
	 * @return the cinema
	 */
	//returns null if not found
	public Cinema findCinema(ArrayList<Cineplex> cineplexList,int cineplexID, String cinemaCode) {
		if(cineplexID >= cineplexList.size()) return null;//error checking
		
		ArrayList<Cinema> cinemaList = cineplexList.get(cineplexID).getCinemaList(); //retrieve cinemaList
		
		int cinemaIndex = findCinemaIndex(cinemaList, cinemaCode);
		if(cinemaIndex == -1) return null; //cinema not found
		
		return cinemaList.get(cinemaIndex);
	}
	
	//overloaded method
	//returns a cinema only by cinemaCode
	/**
	 * Find a cinema based off its cinemaCode, from a cinemaList
	 * it returns null if a cinema cannot be found
	 * overloaded method
	 * @param cinemaCode the cinema code
	 * @return the cinema
	 */
	//returns null if not found
	public Cinema findCinema(String cinemaCode) {
		ArrayList<Cinema> cinemaList = getAllCinema();
		
		int cinemaIndex = findCinemaIndex(cinemaList, cinemaCode);
		if(cinemaIndex == -1) return null; //cinema not found
		
		return cinemaList.get(cinemaIndex);
	}
	
	
	
	/**
	 * creates a single Cinema object for a specific cineplex
	 * retrieve corresponding cinemaList, check for duplicate cinemaCode
	 * @param cineplexID the cineplex ID
	 * @param cinemaCode the cinema code
	 * @param cinemaClass the cinema class
	 * @param showtime the showtime
	 * @return true, if successful
	 */
	//creates a single Cinema object for a specific cineplex
	public boolean createCinema(int cineplexID, String cinemaCode, ClassOfCinema cinemaClass, ArrayList<Showtime> showtime) {
		ArrayList<Cineplex> cineplexList = cineplexMgr.getAllCineplex();
		
		//retrieve corresponding cinemaList
		ArrayList<Cinema> cinemaList = cineplexList.get(cineplexID).getCinemaList();
		
		//If ArrayList doesn't exist, create a new ArrayList and store it to cineplex
		if(cinemaList == null) {
			cinemaList = new ArrayList<Cinema>();
			cineplexList.get(cineplexID).setCinemaList(cinemaList);
		}
		
		//check for duplicate cinemaCode
		if(findCinemaIndex(cinemaList, cinemaCode) != -1) 
			return false;
		
		cinemaList.add(new Cinema(cinemaCode,cinemaClass,showtime));
		
		writeToFile(cineplexList);
		return true;
	}
	
	
	//deletes a cinema using cinemaCode string
	//return false if failed to delete, return true if successful
	/**
	 * Deletes a cinema using cinemaCode string
	 * Return false if failed to delete, return true if successful
	 * @param cineplexID the cineplex ID
	 * @param cinemaCode the cinema code
	 * @return true, if successful
	 */
	//return true if deleted successfully
	public boolean deleteCinema(int cineplexID, String cinemaCode) {
		ArrayList<Cineplex> cineplexList = cineplexMgr.getAllCineplex();
		
		ArrayList<Cinema> cinemaList = cineplexList.get(cineplexID).getCinemaList();
		
		if(cinemaList == null) return false;
		
		int cinemaIndex = findCinemaIndex(cinemaList,cinemaCode);
		
		if (cinemaIndex == -1) return false;
		
		cinemaList.remove(cinemaIndex);
		
		writeToFile(cineplexList);
		return true;
	}
	
	
	
	//returns the index of the cinema that matches the cinemaCode
	/**
	 * Returns the index of the cinema that matches the cinemaCode by using a for loop
	 * and iterating through the ArrayList
	 * @param cinemaList the cinema list
	 * @param cinemaCode the cinema code
	 * @return the int
	 */
	//returns -1 if not found
	public int findCinemaIndex(ArrayList<Cinema> cinemaList,String cinemaCode) {
		if(cinemaList == null) return -1;
		
		for(int i=0; i<cinemaList.size(); i++ ) {
			if(cinemaList.get(i).getCinemaCode().equals(cinemaCode))
				return i;
		}
		return -1;
	}
	
	/**
	 * Write to file.
	 * 
	 * @param cineplexList the cineplex list
	 */
	public void writeToFile(ArrayList<Cineplex> cineplexList) {
		cineplexMgr.writeToFile(cineplexList);
	}
	
	/**
	 * Gets the all cineplex.
	 *
	 * @return the all cineplex
	 */
	public ArrayList<Cineplex> getAllCineplex(){
		return cineplexMgr.getAllCineplex();
	}
}
