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

public class CineplexFileManager {
	
	//Attribute 
	private static final String FILENAME = "Database/cineplex.txt"; //stores an array of Cineplexes
	
	
	//Constructor
	public CineplexFileManager() {
		//empty constructor
	}
	
	
	
	//reads and return an ArrayList of Cineplex from the file
	public static ArrayList<Cineplex> getAllCineplex(){
		ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
		
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fis);
			cineplexList = (ArrayList<Cineplex>) in.readObject();
			in.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return cineplexList;
	}
	
	//writes an ArrayList of Cineplex to the file
	public static void writeToFile(ArrayList<Cineplex> cineplexList) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(cineplexList);
			out.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	//creates and save the cineplex into the file
	public static boolean createCineplex(int cineplexID, String cineplexName, ArrayList<Cinema> cinemaList) {
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
	
	
	
	//deletes a cineplex using the cineplexName
	public static boolean delete(String cineplexName) {
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
