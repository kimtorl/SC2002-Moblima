package control;

import java.time.LocalDate;
import java.util.ArrayList;

import entity.Holiday;

public interface HolidayManager {

	public boolean createHoliday(LocalDate date);
	
	public boolean deleteHoliday(LocalDate date);
	
	//returns empty List if file not found
	public ArrayList<Holiday> getAllHolidays();
	
	public void writeToFile(ArrayList<Holiday> holidayList) ;
	
	
	public boolean isHoliday(LocalDate date);
	
}
