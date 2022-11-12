package boundary;

import java.io.Serializable;

import control.InputManager;

public class EditMovieListing implements Capability, Serializable {

	private static final long serialVersionUID = 30L;

	@Override
	public void performCapability() {
		int choice;
		
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. Add a Movie");
			System.out.println("2. Update a Movie");
			System.out.println("3. Remove a Movie");
			System.out.println("4. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 4);
			
			switch(choice) {
			case 1:
				createMovie();
				break;
			case 2:
				updateMovie();
				break;
			case 3:
				removeMovie();
				break;
			default:
					
			}
			
		}while(choice != 4);
		
		

	}
	
	public String toString() {
		String str = "Edit Movie Listing";
		return str;
	}
	
	public void createMovie() {
		
	}
	
	public void updateMovie() {
		
	}
	
	public void removeMovie() {
		
	}
	
	
}
