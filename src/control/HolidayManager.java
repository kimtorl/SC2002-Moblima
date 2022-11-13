/*
 * 
 */
package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Holiday;

public interface HolidayManager {

	/**
	 * Creates the holiday.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean createHoliday(LocalDate date);
	
	/**
	 * Delete holiday.
	 *
	 * @param date the date
	 * @return true, if successful
	 */
	public boolean deleteHoliday(LocalDate date);
	
	/**
	 * Gets the all holidays.
	 *
	 * @return the all holidays
	 */
	//returns empty List if file not found
	public ArrayList<Holiday> getAllHolidays();
	
	/**
	 * Write to file.
	 *
	 * @param holidayList the holiday list
	 */
	public void writeToFile(ArrayList<Holiday> holidayList) ;
	
	
	/**
	 * Checks if is holiday.
	 *
	 * @param date the date
	 * @return true, if is holiday
	 */
	public boolean isHoliday(LocalDate date);
	
}
