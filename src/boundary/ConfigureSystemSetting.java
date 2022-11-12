package boundary;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.ArrayList;

import java.time.format.DateTimeParseException;

import control.HolidayManager;
import control.InputManager;
import entity.Holiday;

public class ConfigureSystemSetting implements Capability, Serializable {

	private static final long serialVersionUID = 39L;
	private HolidayManager manager;
	
	public ConfigureSystemSetting(HolidayManager m) {
		manager = m;
	}
	
	
	
	@Override
	public void performCapability() {
		int choice;
		
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. Edit Holidays");
			System.out.println("2. Edit Prices");
			System.out.println("3. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 3);
			
			switch(choice) {
			case 1:
				editHoliday();
				break;
			case 2:
				editPrice();
				break;
			default:
					
			}
			
		}while(choice != 3);
		
		

	}


	
	public String toString() {
		String str = "Configure System Settings";
		return str;
	}
	
	public void editHoliday() {
		System.out.println("\n1. Add holiday");
		System.out.println("2. Delete holiday");
		System.out.println("3. List all holidays");
		System.out.println("4. Check if a date is a holiday");
		System.out.println("5. Exit\n");
		int c;
		do {
			c = InputManager.getInt(1,5);
			switch(c)
			{
				case 1:
					
					System.out.println("Enter the date of new holiday"
							+ "in yyyy-MM-dd format: ");
					String newholiday = InputManager.getString(); //newholiday stores string of new holiday
					try{
						LocalDate newdate = LocalDate.parse(newholiday);	//if invalid date is input
						manager.createHoliday(newdate);
					}catch (DateTimeParseException e) {
						System.out.println("Invalid date");
					}
					

					break;
				case 2:
					System.out.println("Enter date of holiday to be deleted"
							+ " in yyyy-MM-dd format: ");
					String deletestring = InputManager.getString();
					
					try {
						LocalDate deletedate = LocalDate.parse(deletestring);
						manager.deleteHoliday(deletedate);
						
					}catch (DateTimeParseException e) {
						System.out.println("Invalid date");
					}

					break;
				case 3:
					ArrayList<Holiday> holidaylist = new ArrayList<Holiday>();
					//ArrayList<Holiday> holidaylist;
					holidaylist = manager.getAllHolidays();
					for(int i = 0; i < holidaylist.size(); i++) {
						System.out.println(holidaylist.get(i).getDate());
					}
					break;
				case 4:
					System.out.println("Enter the date(yyyy-mm-dd) to be checked: ");
					String stringisholiday = InputManager.getString(); //newholiday stores string of new holiday
					
					try {
						LocalDate objectisholiday = LocalDate.parse(stringisholiday);
						if(manager.isHoliday(objectisholiday))
							System.out.println("It is a holiday.");
						else
							System.out.println("It is NOT a holiday.");
						
					}catch (DateTimeParseException e) {
						System.out.println("Invalid date");
					}
					break;
				case 5:
					break;
				default:
					break;
			}
			
			if(c!=5) {
				System.out.println("1. Add holiday");
				System.out.println("2. Delete holiday");
				System.out.println("3. List all holidays");
				System.out.println("4. Check if a date is a holiday");
				System.out.println("5. Exit");
			}
			
			
		}while(c!=5);
		
	}
	
	
	public void editPrice() {
		
	}
	
	
}
