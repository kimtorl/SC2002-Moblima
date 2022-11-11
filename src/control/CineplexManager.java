package control;


import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;

public interface CineplexManager {

	//reads and return an ArrayList of Cineplex from the file
	public ArrayList<Cineplex> getAllCineplex();
	
	//writes an ArrayList of Cineplex to the file
	public void writeToFile(ArrayList<Cineplex> cineplexList);
	
	//creates and save the cineplex into the file
	public boolean createCineplex(int cineplexID, String cineplexName, ArrayList<Cinema> cinemaList);
	
	
	
	//deletes a cineplex using the cineplexName
	public boolean deleteCineplex(String cineplexName);
	
	
}
