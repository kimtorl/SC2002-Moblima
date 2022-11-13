package control;


import java.io.Serializable;
import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.ClassOfCinema;
import entity.Showtime;

public class CinemaFileManager implements CinemaManager, Serializable{

	private static final long serialVersionUID = 22L;
	//Attributes
	private CineplexManager cineplexMgr;
	
	
	//Constructor
	public CinemaFileManager(CineplexManager cineplexMgr) {
		this.cineplexMgr = cineplexMgr;
	}
	
	
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
	//returns null if not found
	public Cinema findCinema(String cinemaCode) {
		ArrayList<Cinema> cinemaList = getAllCinema();
		
		int cinemaIndex = findCinemaIndex(cinemaList, cinemaCode);
		if(cinemaIndex == -1) return null; //cinema not found
		
		return cinemaList.get(cinemaIndex);
	}
	
	
	
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
	//return false if failed to delete
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
	//returns -1 if not found
	public int findCinemaIndex(ArrayList<Cinema> cinemaList,String cinemaCode) {
		if(cinemaList == null) return -1;
		
		for(int i=0; i<cinemaList.size(); i++ ) {
			if(cinemaList.get(i).getCinemaCode().equals(cinemaCode))
				return i;
		}
		return -1;
	}
	
	public void writeToFile(ArrayList<Cineplex> cineplexList) {
		cineplexMgr.writeToFile(cineplexList);
	}
	
	public ArrayList<Cineplex> getAllCineplex(){
		return cineplexMgr.getAllCineplex();
	}
}
