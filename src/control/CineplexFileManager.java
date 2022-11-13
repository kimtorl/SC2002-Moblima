/*
 * 
 */
package control;

import entity.Cinema;
import entity.Cineplex;


import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CineplexFileManager implements CineplexManager, Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 23L;
	
	/** The Constant FILENAME. */
	//Attribute 
	private static final String FILENAME = "Database/cineplex.txt"; //stores an array of Cineplexes
	
	
	/**
	 * Instantiates a new cineplex file manager.
	 */
	//Constructor
	public CineplexFileManager() {
		//empty constructor
	}
	
	
	
	/**
	 * Gets the all cineplex.
	 *
	 * @return the all cineplex
	 */
	//reads and return an ArrayList of Cineplex from the file
	@SuppressWarnings("unchecked")
	public ArrayList<Cineplex> getAllCineplex(){
		ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
		
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fis);
			cineplexList = (ArrayList<Cineplex>) in.readObject();
			in.close();
			
		}catch(IOException ex) {
			System.out.println("File not found!");
			ex.printStackTrace();
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return cineplexList;
	}
	
	/**
	 * Write to file.
	 *
	 * @param cineplexList the cineplex list
	 */
	//writes an ArrayList of Cineplex to the file
	public void writeToFile(ArrayList<Cineplex> cineplexList) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(cineplexList);
			out.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Creates the cineplex.
	 *
	 * @param cineplexID the cineplex ID
	 * @param cineplexName the cineplex name
	 * @param cinemaList the cinema list
	 * @return true, if successful
	 */
	//creates and save the cineplex into the file
	public boolean createCineplex(int cineplexID, String cineplexName, ArrayList<Cinema> cinemaList) {
		ArrayList<Cineplex> cineplexList;
		File f = new File(FILENAME);
		
		//if file exists
		if(f.exists()) {
			cineplexList = getAllCineplex();
		}
		else {
			cineplexList = new ArrayList<Cineplex>();
		}
		
		//check for duplicates
		for (int i=0; i<cineplexList.size();i++) {
			if(cineplexList.get(i).getCineplexID() == cineplexID || cineplexList.get(i).getCineplexName().equals(cineplexName))
				return false;
		}
		
		//create and append new cineplex
		cineplexList.add(new Cineplex(cineplexID, cineplexName, cinemaList));
		
		//save cineplexList to file
		writeToFile(cineplexList);
		return true;
	}
	
	
	
	/**
	 * Delete cineplex.
	 *
	 * @param cineplexName the cineplex name
	 * @return true, if successful
	 */
	//deletes a cineplex using the cineplexName
	public boolean deleteCineplex(String cineplexName) {
		File f = new File(FILENAME);
		
		if(f.exists()) {
			ArrayList<Cineplex> cineplexList = getAllCineplex();
			for(int i=0; i<cineplexList.size();i++) {
				if (cineplexList.get(i).getCineplexName().equals(cineplexName)) {
					cineplexList.remove(i);
					writeToFile(cineplexList);
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
