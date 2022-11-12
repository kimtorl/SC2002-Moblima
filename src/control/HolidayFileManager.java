package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import entity.Holiday;

public class HolidayFileManager {
	
	private static final String FILENAME = "Database/holidays.txt"; 
	
	public static void createHoliday(Holiday h) {
		try
		{
			FileOutputStream fileOut = new FileOutputStream(FILENAME);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(h);
            objectOut.close();
            System.out.println("Holiday added.");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static boolean deleteHoliday(Holiday h) {
		File f = new File(FILENAME);
		if(f.exists())
		{
			ArrayList<Holiday> holidaylist = new ArrayList<Holiday>();
			holidaylist = getAllHolidays();
			for(int i = 0; i < holidaylist.size();i++)
			{
				if(h.getDate() == holidaylist.get(i).getDate())
				{
					holidaylist.remove(i);
					System.out.println("Successfully removed holiday!");
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
	
	public static ArrayList<Holiday> getAllHolidays(){
		
		ArrayList<Holiday> holidaylist = new ArrayList<Holiday>();
		
		try
		{
			FileInputStream fileIn = new FileInputStream(FILENAME);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			
			holidaylist = (ArrayList<Holiday>)objectIn.readObject();
			System.out.println("The Object has been read from the file");
            objectIn.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
		return holidaylist;
		
	}
	
	
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

