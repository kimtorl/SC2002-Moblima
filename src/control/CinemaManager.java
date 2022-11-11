package control;

import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.ClassOfCinema;
import entity.Showtime;

public interface CinemaManager {

	//reads and returns all the Cinema in an ArrayList
	public ArrayList<Cinema> getAllCinema();
		
	//reads and return a specific cinema specified by the cinemaCode from a given cineplexList
	//returns null if not found
	public Cinema findCinema(ArrayList<Cineplex> cineplexList,int cineplexID, String cinemaCode);
	
	
	
	//creates a single Cinema object for a specific cineplex
	public boolean createCinema(int cineplexID, String cinemaCode, ClassOfCinema cinemaClass, ArrayList<Showtime> showtime);
	
	
	//deletes a cinema using cinemaCode string
	//return false if failed to delete
	//return true if deleted successfully
	public boolean deleteCinema(int cineplexID, String cinemaCode);
	
	//returns the index of the cinema that matches the cinemaCode
	//returns -1 if not found
	public int findCinemaIndex(ArrayList<Cinema> cinemaList,String cinemaCode);
	
	public void writeToFile(ArrayList<Cineplex> cineplexList);
	
	public ArrayList<Cineplex> getAllCineplex();

}
