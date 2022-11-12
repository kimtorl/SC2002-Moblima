package boundary;

import java.io.Serializable;

import control.InputManager;

public class ConfigureSystemSetting implements Capability, Serializable {

	private static final long serialVersionUID = 39L;

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
		
	}
	
	
	public void editPrice() {
		
	}
	
	
}
