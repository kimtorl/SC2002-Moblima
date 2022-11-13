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
import entity.Showtime;
import entity.TicketType;
import entity.TypeOfMovie;

public class BookTicket implements Capability, Serializable {

	private static final long serialVersionUID = 37L;
	
	private CinemaManager cinemaMgr;
	private ShowtimeManager showtimeMgr;
	private MovieManager movieMgr;
	private TransactionManager transactionMgr;
	private PriceManager priceMgr;
	
	public BookTicket(CinemaManager cinemaMgr, ShowtimeManager showtimeMgr, MovieManager movieMgr, TransactionManager transactionMgr, PriceManager priceMgr){
		this.cinemaMgr = cinemaMgr;
		this.showtimeMgr = showtimeMgr;
		this.movieMgr = movieMgr;
		this.transactionMgr = transactionMgr;
		this.priceMgr = priceMgr;
	}


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


	
	public String toString() {
		String str = "Book Tickets";
		return str;
	}
	
	
	public int selectMovie() {
		int movieID;
		
		System.out.println("Select a movie from below:");
		movieMgr.listAllMovies();
		System.out.println("Enter movieID: (-1 to exit)");
		movieID = InputManager.getInt();

		return movieID;
	}
	

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
