/*
 * 
 */
package boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import control.CinemaManager;
import control.InputManager;
import control.MovieManager;
import control.PriceManager;
import control.ShowtimeManager;
import control.TransactionManager;
import entity.ClassOfCinema;
import entity.MovieGoer;
import entity.ShowingStatus;
import entity.Showtime;
import entity.TicketType;
import entity.TypeOfMovie;

/**
 * @author Cross
 * Class allows us to book ticket.
 */
public class BookTicket implements Capability, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 37L;
	
	/** The Control class for Cinema. */
	private CinemaManager cinemaMgr;
	
	/** The Control class for Showtime. */
	private ShowtimeManager showtimeMgr;
	
	/** The Control class for Movie. */
	private MovieManager movieMgr;
	
	/** The Control class for Transaction. */
	private TransactionManager transactionMgr;
	
	/** The Control class for Price. */
	private PriceManager priceMgr;
	
	/**
	 * Instantiates a new bookTicket.
	 * 
	 *
	 * @param cinemaMgr 		The CinemaManager
	 * @param showtimeMgr 		The ShowtimeManager
	 * @param movieMgr the  	The MovieManager
	 * @param transactionMgr 	TransactionManager
	 * @param priceMgr 			The PriceManager
	 */
	public BookTicket(CinemaManager cinemaMgr, ShowtimeManager showtimeMgr, MovieManager movieMgr, TransactionManager transactionMgr, PriceManager priceMgr){
		this.cinemaMgr = cinemaMgr;
		this.showtimeMgr = showtimeMgr;
		this.movieMgr = movieMgr;
		this.transactionMgr = transactionMgr;
		this.priceMgr = priceMgr;
	}


	/**
	 * Displays available movies and get user to select a movie using movieID.
	 * Then, displays the available Showtimes and get user to select a Showtime.
	 * Then, displays the seat layout and get user to select the seats. More than 1 seat can be selected.
	 * Then, ticket price is retrieved and displayed to user for confirmation.
	 * Upon confirmation, transaction will be made and saved using TransactionManager.
	 */
	@Override
	public void performCapability() {
		
		int movieID;
		boolean tryAgain = true;
		
		//movie selection
		do {
			movieID = selectMovie();
			if(movieID == -1) return; //user exits
			
			//error checking
			else if(movieMgr.findMovie(movieID) == null) {
				System.out.println("Invalid movieID!");
				tryAgain = InputManager.tryAgain();
				if (!tryAgain) return; //user does not wish to try again
			}
			else break; //movie selected
		}while(tryAgain);
		
		if(movieMgr.findMovie(movieID).getShowingStatus() == ShowingStatus.COMING_SOON) {
			System.out.println("Booking only allowed for 'Preview' and 'Now Showing' status!");
			return;
		}
		
		//showtime selection
		Showtime st = selectShowtimes(movieID);
		if (st == null) return; //user exits

		
		//seat selection
		ArrayList<Integer> selectedSeats = selectSeats(st);
		if (selectedSeats == null) return; //user exits
		
		//calculates the price and prints it
		double transactionAmount = calculatePrice(st,selectedSeats.size());
		
		System.out.println("Confirm Transaction? Enter Y/N: ");
		if(!InputManager.getY_or_N()) return; //user exits
		
		makeTransaction(transactionAmount, LocalDateTime.now(), movieID, st.getCinemaCode(), selectedSeats);
	}


	
	/**
	 * Overrides toString method for printing the capability.
	 *
	 * @return the string
	 */
	public String toString() {
		String str = "Book Tickets";
		return str;
	}
	
	
	/**
	 * Select Movie. Movies are listed. Get user to input integer.
	 *
	 * @return an integer which is the movieID 
	 */
	public int selectMovie() {
		int movieID;
		
		System.out.println("Select a movie from below:");
		movieMgr.listAllMovies(true);
		System.out.println("Enter movieID: (-1 to exit)");
		movieID = InputManager.getInt();

		return movieID;
	}
	

	/**
	 * Select showtimes.
	 * Display available showtimes for the chosen movie and user will select one.
	 *
	 * @param movieID the movieID attribute of a Movie
	 * @return the selected Showtime
	 */
	//movieID is valid
	public Showtime selectShowtimes(int movieID) {
		System.out.println("Here are the available showtimes for the selected movie: ");
		//retrieve all showtimes of this movieID
		ArrayList<Showtime> showtimeList = showtimeMgr.findAllShowtimeByMovie(movieID);
		
		//displays available showtimes
		int numOfOptions = showtimeList.size()+1;
		for(int i =0; i<showtimeList.size();i++) {
			Showtime st = showtimeList.get(i);
			System.out.println((i+1) + ". " + st.dateTimeToString() + " at Cinema " + st.getCinemaCode());
		}
		System.out.println(numOfOptions + ". Go Back");
		System.out.println("------------------------------");
		System.out.println("Enter a number to select a showtime:");
		int choice = InputManager.getInt(1, numOfOptions);
		
		if(choice == numOfOptions) return null; //Go back
		return showtimeList.get(choice-1);
	}
	
	
	/**
	 * Select seats.
	 * Display the seat layout of the chosen Showtime and get user to choose the seats.
	 * @param st the chosen Showtime
	 * @return the array list of integers which represents the chosen seatIDs
	 */
	public ArrayList<Integer> selectSeats(Showtime st) {
		ArrayList<Integer> selectedSeats = new ArrayList<Integer>();
		boolean invalid = true;
		int numSeats;
		//get number of seats
		do {
			System.out.println("How many seats do you want to book? Enter a number:");
			numSeats = InputManager.getInt();
			if (numSeats<=0) {
				System.out.println("Invalid input! Try again!");
			}
			else invalid = false;
		}while(invalid);
		
		//selects num seats
		for(int i=0; i<numSeats; i++) {
			invalid = true;
			int seatID;
			do {
				st.getSeatLayout().displaySeatLayout(); //display seats
				System.out.println("Please select seat " +(i+1) + ": (-1 to exit)");
				String choice = InputManager.getString();
				if(choice.equals("-1")) return null; //user chooses to exit
				
				seatID = st.getSeatLayout().bookSeat(choice);
				if(seatID != -1) invalid = false; //valid seat chosen
				else System.out.println("Invalid seat chosen!");
			}while(invalid);
			selectedSeats.add(seatID);
		}
		
		//update Showtime with seatLayout
		showtimeMgr.updateShowtimeSeatLayout(st.getCinemaCode(), st.getDateTime(), st.getSeatLayout());
		
		return selectedSeats;
	}
	
	/**
	 * Calculate price.
	 * User can choose ticketType - Adult, Senior Citizen or Student. Assume that verification will be done at the cinema.
	 * PriceManager uses TicketType, TypeOfMovie, ClassOfCinema, dateTime to retrieve price
	 *	
	 * @param st the chosen Showtime
	 * @param numOfTickets the num of tickets ordered
	 * @return the total price of the transaction
	 */
	public double calculatePrice(Showtime st, int numOfTickets) {
		//Ticket type
		System.out.println("Choose your ticket type:");
		for(int i=0; i < TicketType.values().length;i++) {
			System.out.println((i+1) + ". " + TicketType.values()[i]);
		}
		int choice = InputManager.getInt(1, TicketType.values().length);
		TicketType ticketType = TicketType.values()[choice-1];
		
		//Movie type
		TypeOfMovie movieType = st.getMovie().getMovieType();
		
		//cinema Class
		ClassOfCinema cinemaClass = cinemaMgr.findCinema(st.getCinemaCode()).getCinemaClass();
		
		//dateTime
		LocalDateTime dateTime = st.getDateTime();
		
		double price = priceMgr.getPrice(ticketType, movieType, cinemaClass, dateTime);
		
		System.out.println("------------------------------");
		System.out.println("Ticket Type: " + ticketType);
		System.out.println("Movie Type: " + movieType);
		System.out.println("Cinema Class: " + cinemaClass);
		System.out.printf("Price: %.2f /ticket\n", price);
		
		return price*numOfTickets;
		
	}
	
	/**
	 * Make transaction.
	 * Retrieves the Account of this user currently logged in and get the name, mobile number and email.
	 * TransactionManager then creates an store the Transaction.
	 *
	 * @param transactionAmount the transaction amount
	 * @param dateTime the date time of transaction
	 * @param movieID the movie ID of the chosen movie
	 * @param cinemaCode the cinema code
	 * @param seats the array list of seats chosen
	 */
	public void makeTransaction(double transactionAmount, LocalDateTime dateTime, int movieID, String cinemaCode, ArrayList<Integer> seats) {
		MovieGoer acc = (MovieGoer) MainApplication.currentAcc;
		String name = acc.getName();
		String mobileNum = acc.getMobileNumber();
		String email = acc.getEmail();
		
		if(transactionMgr.createTransaction(transactionAmount, name, mobileNum, email, dateTime, movieID, cinemaCode, seats)) {
			System.out.println("Transaction successful!");
		}	
		else {
			makeTransaction(transactionAmount,dateTime.plusMinutes(1), movieID, cinemaCode, seats); //advance the transaction to the next minute
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
