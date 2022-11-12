package boundary;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.text.SimpleDateFormat;

import control.HolidayManager;
import control.InputManager;

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
		System.out.println("1. Add holiday");
		System.out.println("2. Delete holiday");
		System.out.println("3. List all holidays");
		System.out.println("4. Check if a date is a holiday");
		System.out.println("5. Exit");
		int c;
		do {
			c = InputManager.getInt(1,3);
			switch(c)
			{
				case 1:
					
					System.out.println("Enter the date of new holiday"
							+ "in yyyy-MM-dd format: ");
					String newholiday = InputManager.getString(); //newholiday stores string of new holiday
					LocalDate newdate = LocalDate.parse(newholiday);
					manager.createHoliday(newdate);
					break;
				case 2:
					System.out.println("Enter date of holiday to be deleted"
							+ " in yyyy-MM-dd format: ");
					String deletestring = InputManager.getString();
					LocalDate deletedate = LocalDate.parse(deletestring);
					manager.deleteHoliday(deletedate);
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid input, try again.");
			}
			
			
		}while(c!=3);
		
	}
	
	
	public void editPrice() {
		
	}
	
	
}
