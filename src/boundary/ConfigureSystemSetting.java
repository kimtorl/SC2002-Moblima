/*
 * 
 */
package boundary;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.time.format.DateTimeParseException;

import control.AccountManager;
import control.HolidayManager;
import control.InputManager;
import control.MovieManager;
import control.PriceManager;
import control.TransactionManager;
import entity.Holiday;
import entity.TicketType;
import entity.TypeOfMovie;
import entity.AccountType;
import entity.ClassOfCinema;

/**
 * @author Cross
 * Class to allow us to configure system settings
 */
public class ConfigureSystemSetting implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 39L;
	
	/** The Control class for Holiday. */
	private HolidayManager holidayMgr;
	
	/** The Control class for Price. */
	private PriceManager priceMgr;
	
	/** The Control class for Account */
	private AccountManager accMgr;
	
	/** The Control class for Movie. */
	private MovieManager movieMgr;
	
	/** The Control class for Transaction. */
	private TransactionManager transactionMgr;
	
	/**
	 * Instantiates a new configure system setting.
	 *
	 * @param holidayMgr		The HolidayManager
	 * @param priceMgr 			The PriceManager
	 * @param accMgr 			The AccountManager
	 * @param movieMgr 			The MovieManager
	 * @param transactionMgr 	The TransactionManager
	 */
	public ConfigureSystemSetting(HolidayManager holidayMgr, PriceManager priceMgr, AccountManager accMgr, MovieManager movieMgr,TransactionManager  transactionMgr) {
		this.holidayMgr = holidayMgr;
		this.priceMgr = priceMgr;
		this.accMgr = accMgr;
		this.movieMgr = movieMgr;
		this.transactionMgr = transactionMgr;
	}
	
	
	
	/**
	 * Displays a menu for user to edit holidays, prices or account permissions of MovieGoer.
	 * Input 4 to exit this menu.
	 */
	@Override
	public void performCapability() {
		int choice;
		
		//display menu
		do {
			System.out.println("------------------------------");
			System.out.println("Choose an option: ");
			System.out.println("1. Edit Holidays");
			System.out.println("2. Edit Prices");
			System.out.println("3. Edit MovieGoer Account's Permissions ");
			System.out.println("4. Go back");
			System.out.println("------------------------------");
			
			choice = InputManager.getInt(1, 4);
			
			switch(choice) {
			case 1:
				editHoliday();
				break;
			case 2:
				editPrice();
				break;
			case 3:
				editMovieGoerAccount();
			default:
					
			}
			
		}while(choice != 4);
		
		

	}


	
	/**
	 * Overrides toString method for printing the capability
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "Configure System Settings";
		return str;
	}
	
	/**
	 * Displays a menu for the admin to edits the holiday. 
	 * Allow admin to add, delete, list or check if a date is a holiday using HolidayManager class.
	 */
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
						holidayMgr.createHoliday(newdate);
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
						holidayMgr.deleteHoliday(deletedate);
						
					}catch (DateTimeParseException e) {
						System.out.println("Invalid date");
					}

					break;
				case 3:
					ArrayList<Holiday> holidaylist = new ArrayList<Holiday>();
					holidaylist = holidayMgr.getAllHolidays();
					for(int i = 0; i < holidaylist.size(); i++) {
						System.out.println(holidaylist.get(i).getDate());
					}
					break;
				case 4:
					System.out.println("Enter the date(yyyy-mm-dd) to be checked: ");
					String stringisholiday = InputManager.getString(); //newholiday stores string of new holiday
					
					try {
						LocalDate objectisholiday = LocalDate.parse(stringisholiday);
						if(holidayMgr.isHoliday(objectisholiday))
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
	
	
	/**
	 * Displays a menu for the admin to edit the price.
	 * Allow admin to retrieve the price of tickets or update the price.
	 */
	public void editPrice() {
		System.out.println("1. Get Price"
				+ "\n2. Update price"
				+ "\n3. Exit");
		int choice;
		do {
			choice = InputManager.getInt(1, 2);
			switch(choice) {
				case 1:
					System.out.print("Price of ticket is: ");
					System.out.println(getThePrice());
					break;
				case 2:
					updateThePrice();
					break;
				default:		
			}
		}while(choice!=3);

	}
	
	/**
	 * Gets the the price.
	 * Admin inputs TicketType, MovieType, ClassOfCinema, DateTime.
	 * DateTime is used to determine whether its weekend or weekday. Certain TicketType such as Student and Senior Citizen discounts
	 * depends on date and time.
	 * @return the price based on these 4 factors
	 */
	public double getThePrice() {
		System.out.println("Enter ticket type: 1.Adult	2.Student	3.Senior Citizen");
		int ticketTypeInt = InputManager.getInt(1,3);
		TicketType ticketType = TicketType.values()[ticketTypeInt-1];
		//
		System.out.println("Enter number for the type of Movie: "
				+ "\n1.BLOCKBUSTER_2D\n2.BLOCKBUSTER_3D\n3.NONBLOCKBUSTER_2D\n4.NONBLOCKBUSTER_3D");
		int typeInt = InputManager.getInt(1,4);
		TypeOfMovie type = TypeOfMovie.values()[typeInt-1];
		//
		System.out.println("Enter number for the class of cinema: "
				+ "\n1.Standard\n2.Platinum Movie Suites");
		int cinemaClassInt = InputManager.getInt(1,2);
		ClassOfCinema cinemaClass = ClassOfCinema.values()[cinemaClassInt-1];
		//
		System.out.println("Enter the date of movie: yyyy-mm-dd ");
		LocalDate date = InputManager.getLocalDate();
		System.out.println("After or before 6pm?");
		boolean night = InputManager.getY_or_N();
		LocalDateTime dateTime;
		
		if(night)
			 dateTime = LocalDateTime.of(date, LocalTime.of(18, 01));
		else 
			dateTime = LocalDateTime.of(date, LocalTime.of(10, 00));
		
		return priceMgr.getPrice(ticketType, type, cinemaClass, dateTime);
	}
	
	/**
	 * Update the price.
	 * Old price is found using TicketType, MovieType, ClassOfCinema, DateTime, and then updated with new price
	 * DateTime is used to determine whether its weekend or weekday. Certain TicketType such as Student and Senior Citizen discounts
	 * depends on date and time.
	 */
	public void updateThePrice() {
		System.out.println("Enter ticket type: 1.Adult	2.Student	3.Senior Citizen");
		int ticketTypeInt = InputManager.getInt(1,3);
		TicketType ticketType = TicketType.values()[ticketTypeInt-1];
		//
		System.out.println("Enter number for the type of Movie: "
				+ "\n1.BLOCKBUSTER_2D\n2.BLOCKBUSTER_3D\n3.NONBLOCKBUSTER_2D\n4.NONBLOCKBUSTER_3D");
		int typeInt = InputManager.getInt(1,4);
		TypeOfMovie type = TypeOfMovie.values()[typeInt-1];
		//
		System.out.println("Enter number for the class of cinema: "
				+ "\n1.Standard\n2.Platinum Movie Suites");
		int cinemaClassInt = InputManager.getInt(1,2);
		ClassOfCinema cinemaClass = ClassOfCinema.values()[cinemaClassInt-1];
		//
		System.out.println("Enter the date of movie:");
		LocalDate date = InputManager.getLocalDate();
		System.out.println("Before or After 6pm? Enter Y/N:");
		boolean night = InputManager.getY_or_N();
		LocalDateTime dateTime;
		
		if(night)
			 dateTime = LocalDateTime.of(date, LocalTime.of(18, 01));
		else 
			dateTime = LocalDateTime.of(date, LocalTime.of(10, 00));
		
		System.out.println("Enter new price: ");
		double newPrice = InputManager.getDouble(0,99999);
		
		priceMgr.updatePrice(ticketType, type, cinemaClass, dateTime,newPrice);
		System.out.println("Updated price successfully");
	}
	
	
	/**
	 * Edits MovieGoer's list of Capabilities.
	 * Allow admin to add or remove capabilities. Only Top5MovieByTicketSale and Top5MovieByRating capabilities can be modified.
	 */
	public void editMovieGoerAccount() {
		int choice;
	
		System.out.println("Choose an option:");
		System.out.println("1. Add permissions");
		System.out.println("2. Remove permissions");
		System.out.println("3. Go back");
		
		choice = InputManager.getInt(1,3);
		if(choice == 3) return; //exit
		
		String operation=null;
		if(choice==1) operation = "add";
		else if(choice==2) operation = "del";
		

		System.out.println("Choose a permission to edit:");
		System.out.println("1. List Top 5 Movies by Ticket Sale");
		System.out.println("2. List Top 5 Movies by Overall Reviewer Rating");
		System.out.println("3. Go back");
		choice = InputManager.getInt(1,3);
		if (choice == 3) return; //exit
		
		Capability capability=null;
		if(choice == 1) capability = new Top5MovieByTicketSale(movieMgr, transactionMgr); 
		if (choice ==2) capability = new Top5MovieByRating(movieMgr);
		
		
		if(accMgr.updateAccountCapability(AccountType.MOVIEGOER, operation, capability))
			System.out.println(operation + " permissions success!");;
	
	}
	
	
}
