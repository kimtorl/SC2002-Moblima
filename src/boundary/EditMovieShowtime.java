package boundary;

import java.io.Serializable;

import control.InputManager;
import control.ShowtimeManager;

public class EditMovieShowtime implements Capability, Serializable{

	private static final long serialVersionUID = 31L;

	private ShowtimeManager showtimeMgr;
	
	public EditMovieShowtime(ShowtimeManager showtimeMgr) {
		this.showtimeMgr = showtimeMgr;
	}
	
	@Override
	public void performCapability() {
		int choice;
		
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. Add a movie Showtime");
			System.out.println("2. Update a movie Showtime");
			System.out.println("3. Remove a movie Showtime");
			System.out.println("4. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 4);
			
			switch(choice) {
			case 1:
				createShowtime();
				break;
			case 2:
				updateShowtime();
				break;
			case 3:
				removeShowtime();
				break;
			default:
					
			}
			
		}while(choice != 4);
		
		

	}
	
	
	public String toString() {
		String str = "Edit Movie Showtime";
		return str;
	}
	
	public void createShowtime() {
		
	}
	
	public void updateShowtime() {
		
	}
	
	public void removeShowtime() {
		
	}
	
}
