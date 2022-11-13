/*
 * 
 */
package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Holiday;

/**
 * The Interface HolidayManager. Represents the holiday manager to retrieve and
 * modify information on holidays.
 */
public interface HolidayManager {

	/**
	 * Creates the holiday.
	 *
	 * @param date 		the date
	 * @return true, if successful
	 */
	public boolean createHoliday(LocalDate date);
	
	/**
	 * Deletes the holiday.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean deleteHoliday(LocalDate date);
	
	/**
	 * Gets all the holidays through the arrayList of holidays
	 * Returns empty list if file not found
	 *
	 * @return arrayList of holidays
	 */
	//returns empty List if file not found
	public ArrayList<Holiday> getAllHolidays();
	
	/**
	 * Writes to file the ArrayList of holidays
	 *
	 * @param holidayList the holiday list
	 */
	public void writeToFile(ArrayList<Holiday> holidayList) ;
	
	
	/**
	 * Checks if date is a holiday.
	 *
	 * @param date the date
	 * @return true, if is holiday
	 */
	public boolean isHoliday(LocalDate date);
	
}
