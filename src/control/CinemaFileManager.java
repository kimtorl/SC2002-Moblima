package control;


import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.ClassOfCinema;
import entity.Showtime;

public class CinemaFileManager {

	//No attributes
	
	
	//Empty Constructor
	public CinemaFileManager() {
		
	}
	
	
	
	

	
	//reads and returns all the Cinema in an ArrayList
	public static ArrayList<Cinema> getAllCinema(){
		ArrayList<Cineplex> cineplexList = CineplexFileManager.getAllCineplex();
		
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
		
		for(int i=0; i < cineplexList.size(); i++) {
			if(cineplexList.get(i).getCinemaList()!= null)
				cinemaList.addAll(cineplexList.get(i).getCinemaList()); //append each cinemaList to a single ArrayList
		}
		
		return cinemaList;	
	}
	
	
	//reads and return a specific cinema specified by the cinemaCode from a given cineplexList
	//returns null if not found
	public static Cinema findCinema(ArrayList<Cineplex> cineplexList,int cineplexID, String cinemaCode) {
		
		ArrayList<Cinema> cinemaList = cineplexList.get(cineplexID).getCinemaList(); //retrieve cinemaList
		
		int cinemaIndex = CinemaFileManager.findCinemaIndex(cinemaList, cinemaCode);
		if(cinemaIndex == -1) return null; //cinema not found
		
		return cinemaList.get(cinemaIndex);
	}
	
	
	
	//creates a single Cinema object for a specific cineplex
	public static boolean createCinema(int cineplexID, String cinemaCode, ClassOfCinema cinemaClass, ArrayList<Showtime> showtime) {
		ArrayList<Cineplex> cineplexList = CineplexFileManager.getAllCineplex();
		
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
		
		CineplexFileManager.writeToFile(cineplexList);
		return true;
	}
	
	
	//deletes a cinema using cinemaCode string
	//return false if failed to delete
	//return true if deleted successfully
	public static boolean deleteCinema(int cineplexID, String cinemaCode) {
		ArrayList<Cineplex> cineplexList = CineplexFileManager.getAllCineplex();
		
		ArrayList<Cinema> cinemaList = cineplexList.get(cineplexID).getCinemaList();
		
		if(cinemaList == null) return false;
		
		int cinemaIndex = findCinemaIndex(cinemaList,cinemaCode);
		
		if (cinemaIndex == -1) return false;
		
		cinemaList.remove(cinemaIndex);
		
		CineplexFileManager.writeToFile(cineplexList);
		return true;
	}
	
	
	//returns the index of the cinema that matches the cinemaCode
	//returns -1 if not found
	public static int findCinemaIndex(ArrayList<Cinema> cinemaList,String cinemaCode) {
		if(cinemaList == null) return -1;
		
		for(int i=0; i<cinemaList.size(); i++ ) {
			if(cinemaList.get(i).getCinemaCode().equals(cinemaCode))
				return i;
		}
		return -1;
	}
	
	
	
	
	/*
	//do we even need this?
	//writes an ArrayList of Cinema to the file
	public static void writeToFile(int cineplexID, ArrayList<Cinema> cinemaList) {
			//reads the cineplexList 
			ArrayList<Cineplex> cineplexList = CineplexFileManager.getAllCineplex();
			
			//modifies the cinemaList of a particular cineplex
			cineplexList.get(cineplexID).setCinemaList(cinemaList);
			
			//saves file
			CineplexFileManager.writeToFile(cineplexList);		
	}
	*/
}
