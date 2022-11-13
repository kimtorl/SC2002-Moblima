package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import entity.ClassOfCinema;
import entity.TicketType;
import entity.TypeOfMovie;

/**
 * The Price File Manager which is able to read, edit and show prices of the different movies 
 * <p>This class handles the CRUD operations of the Price File Manager, our databse being a CSV file </p>
 * @author Lin Kai 
 */

public class PriceFileManager implements PriceManager, Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String FILENAME = "Database/prices.txt";
	
	
    /**
	 * Trie is represented as t.
	 * This method serializes the trie and writes it to a file 
	 * @param t trie 
	 */
    public void writeToFile(Trie t) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(t);
			out.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Gets the previously constructed trie and outputs it back 
	 * @return trie t 
	 */
    
    public Trie readFile() {
    	Trie t = null;  
    	try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fis);
			t = (Trie) in.readObject();
			in.close();
			
		}catch(IOException ex) {
			System.out.println("File not found!");
			ex.printStackTrace();
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
    	return t;
    }
    
    
	/**
	 * Reads from a databse of files and initializes the price in a Trie data structure 
	 */
    public void initialisePrice() {
    	String dataPath = "Database/MoviePrice.csv";
    	String line = "";
    	
    	Trie priceTrie = new Trie();
    	
        try (BufferedReader br = new BufferedReader(new FileReader(dataPath))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");
                // insert prices in
                if (currentLine.length != 0) {
                    priceTrie.insertInformation(currentLine);

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File f = new File(FILENAME);
        if(f.exists()) return; //do not overwrite file
        
        
        writeToFile(priceTrie);//save Trie to file
    	
    }
    
    /**
	 * @param ticketType the type of ticket 
	 * @param movieType the type of movie 
	 * @param cinemaClass which class of cinema does it belong to 
	 * @param dateTime the current time, so if its before 6 or after 6 
	 * This method takes in the queries as the parameters and then reads the trie to generate the price of the query seen
	 */
    public double getPrice(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime) {
    	String[] queries = generateQuery(ticketType, movieType, cinemaClass, dateTime);
    	
    	Trie t = readFile(); //retrieve Trie
    	double price = Double.parseDouble(t.readPrice(queries)); //read Price
    	return price;
    }
    
	/**
	 * @param ticketType the type of ticket 
	 * @param movieType the type of movie 
	 * @param cinemaClass which class of cinema does it belong to 
	 * @param dateTime the current time, so if its before 6 or after 6 
	 * @param newPrice
	 * @return
	 */
    public boolean updatePrice(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime, double newPrice) {
    	String price = String.valueOf(newPrice);//convert price to String
    	
    	String[] queries = generateQuery(ticketType, movieType, cinemaClass, dateTime);
    	
    	Trie t = readFile(); 
    	
    	t.setPrice(queries, price); //change the price
    	
    	writeToFile(t); //save changes to file
    	return true;
    }
    
    /**
	 * Some queries have different lengths, this is to format the query such that it is neat.
	 * @param ticketType the type of ticket 
	 * @param movieType the type of movie 
	 * @param cinemaClass which class of cinema does it belong to 
	 * @param dateTime the current time, so if its before 6 or after 6 
	 * @return the compiled query of the String 
	 */
    public String[] generateQuery(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime) {
ArrayList<String> queries = new ArrayList<String>();
    	
    	//first query
    	if(ticketType == TicketType.ADULT) queries.add("Adult");
    	else if(ticketType == TicketType.STUDENT) queries.add("Student");
    	else if (ticketType == TicketType.SENIORCITIZEN) queries.add("Senior Citizen");
    	
    	//second query
    	if (isWeekend(dateTime) || isHoliday(dateTime)) queries.add("Weekend");
    	else queries.add("Weekday");
    	
    	//third query
    	if(dateTime.getDayOfWeek() == DayOfWeek.FRIDAY) queries.add("Friday");
    	else if (dateTime.getDayOfWeek() == DayOfWeek.THURSDAY) queries.add("Thursday");
    	else queries.add("Monday/Tuesday/Wednesday");
    	
    	//fourth query
    	if(dateTime.toLocalTime().isBefore(LocalTime.of(18, 0))) queries.add("bef");
    	else queries.add("af");
    	
    	//fifth query
    	if(cinemaClass == ClassOfCinema.PLATINUM) queries.add("Platinum");
    	else if (movieType == TypeOfMovie.BLOCKBUSTER_2D) queries.add("BlockBusters");
    	else if (movieType == TypeOfMovie.BLOCKBUSTER_3D || movieType==TypeOfMovie.NONBLOCKBUSTER_3D) queries.add("3D");
    	else if (movieType == TypeOfMovie.NONBLOCKBUSTER_2D) queries.add("2D");
    	
    	
    	String[] strList = queries.toArray(new String[0]); //convert to String[]
    	return strList;	
    }
    
    
	
	/** 
	 * @param dateTime current time 
	 * @return boolean if it is a weekend or not 
	 */
	//checks if this dateTime is on a weekend

    public boolean isWeekend(LocalDateTime dateTime) {
		DayOfWeek day = dateTime.getDayOfWeek();
		if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
			return true;
		}
		return false;
    }
    
    
    public boolean isHoliday(LocalDateTime dateTime) {
    	HolidayFileManager holidayMgr = new HolidayFileManager();
    	return holidayMgr.isHoliday(dateTime.toLocalDate());
    }
    
    
    
    /**
	 * A trie node class 
	 */
    
    public static class TrieNode implements Serializable {
        private static final long serialVersionUID = 1L;
		Map<String, TrieNode> children = new HashMap<String, TrieNode>();
        String finalInformation = "";
    }


	/**
	 * Trie consisting of trie nodes 
	 * A trie is essentially a node consisting of hashmaps 
	 * 
	 */
    public static class Trie implements Serializable{
        private static final long serialVersionUID = 1L;
		TrieNode root = new TrieNode();

        public void insertInformation(String[] currentLine) {
            String finalPrice = currentLine[currentLine.length - 1];
            TrieNode node = root;
            for (int i = 0; i < currentLine.length - 1; i++) {
                String currentString = currentLine[i];
                if (!node.children.containsKey(currentString)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(currentString, newNode);
                }
                node = node.children.get(currentString);

            }
            node.finalInformation = finalPrice;

        }

		/**
		 *  traverses down the trie and reads the current price where the information is stored
		 * @param queries an array of queries to read from the trie 
		 * @return the price based on the query 
		 */
        public String readPrice(String[] queries) {
            String res = "";

            TrieNode node = root;
            for (String query : queries) {
                if (node.children.containsKey(query)) { //if key exists
                	node = node.children.get(query); 
                }
            }

            res = node.finalInformation; //retrieve price
            return res;
        }
        
		/**
		 * updates the new price 
		 * @param queries The new attributes of the ticket you want to update 
		 * @param newPrice The new price based on the attributes you want to update 
		 */
        public void setPrice(String[] queries, String newPrice) {
            TrieNode node = root;
            for (String query : queries) {
                if (node.children.containsKey(query)) { //if key exists
                	node = node.children.get(query); 
                }
            }

            node.finalInformation = newPrice; //update price
        }
    }

}
