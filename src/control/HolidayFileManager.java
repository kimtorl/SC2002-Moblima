/*
 * 
 */
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;

import entity.Holiday;

/**
 * @author Cross
 * Manages holiday files and allows us to access and modify it
 */
public class HolidayFileManager implements HolidayManager, Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 24L;
	
	/** The Constant FILENAME. */
	private static final String FILENAME = "Database/holidays.txt"; 
	
	
	
	/**
	 * Creates the holiday.
	 * It takes in a date, and check if the holiday already exists in the
	 * database. If it doesn't, we will add in the new holiday date by appending it.
	 * Else, we will return false.
	 * Once appended, we will sort the arraylist of date representing holiday.
	 * once done we will write the new list into the file.
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean createHoliday(LocalDate date) {
		ArrayList<Holiday> holidayList = getAllHolidays();
		
		//check for duplicated Holiday
		for(Holiday hol : holidayList) {
			if(hol.getDate().equals(date)){
				System.out.println("This holiday has already been added!");
				return false;
			}
		}
		
		//append the holiday
		holidayList.add(new Holiday(date));
		
		//sort the holidayList by date
		holidayList.sort(new Comparator<Holiday>() {
			public int compare(Holiday h1, Holiday h2) {
				LocalDate h1date = h1.getDate();
				LocalDate h2date = h2.getDate();
				if(h1date.isBefore(h2date)) return -1;
				else if(h1date.isAfter(h2date)) return 1;
				else return 0;
			}
		});
		
		//save to file
		writeToFile(holidayList);
		return true;
	}
	
	
	/**
	 * Delete holiday.
	 * It takes in a date and iterates through the arrayList of LocalDate holidays and deletes 	
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean deleteHoliday(LocalDate date) {
		File f = new File(FILENAME);
		if(f.exists())
		{
			ArrayList<Holiday> holidaylist = getAllHolidays();
			for(int i = 0; i < holidaylist.size();i++)
			{
				if(holidaylist.get(i).getDate().equals(date))
				{
					holidaylist.remove(i);
					System.out.println("Successfully removed holiday!");
					writeToFile(holidaylist);
					return true;
				}
			}
		}
		else
		{
			System.out.println("File does not exist");
			return false;
		}

		System.out.println("Holiday could not be found");
		return false;
		
	}
	
	/**
	 * Gets all the holidays.
	 * 
	 * @return all the holidays
	 */
	//returns empty List if file not found
	@SuppressWarnings("unchecked")
	public ArrayList<Holiday> getAllHolidays(){
		
		ArrayList<Holiday> holidaylist = new ArrayList<Holiday>();
		
		try
		{
			FileInputStream fileIn = new FileInputStream(FILENAME);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			
			holidaylist = (ArrayList<Holiday>)objectIn.readObject();
            objectIn.close();
		}
		catch(Exception ex)
		{
			System.out.println("File not found!");
		}
		
		return holidaylist;
	}
	
	
	/**
	 * Writes to file an ArrayList of Holiday
	 * 
	 * @param holidayList the holiday list to be written
	 */
	public void writeToFile(ArrayList<Holiday> holidayList) {
		try
		{
			FileOutputStream fileOut = new FileOutputStream(FILENAME);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(holidayList);
            objectOut.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Checks if is holiday.
	 *
	 * @param date 		the date
	 * @return true, if is holiday
	 */
	public boolean isHoliday(LocalDate date) {
		
		ArrayList<Holiday> holidaylist = new ArrayList<Holiday>();
		holidaylist = getAllHolidays();
		for(int i = 0; i < holidaylist.size();i++)
		{
			if(date.equals(holidaylist.get(i).getDate()))
			{
				return true;
			}
		}
		
		return false;
		
	}
	

}

