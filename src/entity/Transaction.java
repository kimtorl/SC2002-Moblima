package entity;

import java.io.Serializable;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 
/***
 * Transaction class is an entity class that stores the data and information that
 * a normal transaction would have. This class allows us to track what transactions have 
 * been made, allowing us to find out who bought what tickets and the information of the
 * buyer such as his/her name, number and email etc.
 */
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 10L;
	/***
	 * transactionID is a unique identification number for each transaction, this
	 * allows us to differentiate between transactions that might be similar.
	 * It has the format XXXYYYYMMDDhhmm
	 * XXX is the cinema code
	 * YYYYDDMMhhmm is datetime
	 */
	private String transactionID;
	/***
	 * transactionAmount is the total amount that was paid by the movie goer 
	 * during the transaction.
	 */
	private double transactionAmount;
	/***
	 * name is the name of the movie goer that paid for the movie tickets in the transaction.
	 */
	private String name;
	/***
	 * mobileNumber is the mobile number of the movie goer that paid for the movie tickets
	 * in the transaction
	 */
	private String mobileNumber;
	/***
	 * email is the email of the movie goer in the transaction
	 */
	private String email;
	/***
	 * dateTime is the date and time of when the transaction was made
	 */
	private LocalDateTime dateTime;
	/***
	 * movieID is the movie ID of the movie that was booked
	 */
	private int movieID;
	/***
	 * cinemaCode is the cinema code CYZ, where Y represents the cineplex ID.
	 * Z represents the cinema ID.
	 * This is a unique code to represent the 
	 * cinema code in which the movie and session is being held
	 *  
	 */
	private String cinemaCode;

	/***
	 * seatIDList is an arraylist of integers representing the seat ID
	 * of of all the seats booked in the transaction
	 */
	private ArrayList<Integer> seatIDList;
	
	/***
	 * creates a Transaction with no attributes
	 */
	public Transaction() {};
	
	/**
	 * Creates a Transaction with the given attributes
	 * @param transactionAmount total amount that was paid by the movie goer 
	 * 							during the transaction.
	 * @param name				Name of movie goer who booked the tickets
	 * @param mobileNumber		Mobile number of MovieGoer who made the Transaction
	 * @param email				Email of MovieGoer who made the Transaction
	 * @param dateTime			Date and Time of moviegoer
	 * @param movieID			movie ID of the movie that was booked
	 * @param cinemaCode		cinema code in which the movie and session is being held
	 * @param seatIDList		arraylist of integers representing the seat ID
	 * 							of of all the seats booked in the transaction
	 */
	public Transaction(
			double transactionAmount,
			String name,
			String mobileNumber,
			String email,
			LocalDateTime dateTime,
			int movieID,
			String cinemaCode,
			ArrayList<Integer> seatIDList) 
	{
		this.transactionAmount =transactionAmount;
		this.name =name;
		this.mobileNumber =mobileNumber;
		this.email =email;
		this.dateTime =dateTime;
		this.movieID =movieID;
		this.cinemaCode =cinemaCode;
		this.seatIDList = seatIDList;
		this.transactionID = generateTID();
	}
	
	/***
	 * prints out the content of the transaction
	 */
	public String toString(){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String s = "transactionID: "+transactionID+"\nAmount: "+transactionAmount+"\nName: "+name+
				"\nMobile.no : "+mobileNumber+"\nEmail: "+email+"\nDate&Time of transaction: "+dateTime.format(format)
				+"\nMovieID: "+movieID+"\nCinemaCode: "+cinemaCode + '\n';
		return s;
		
	}
	/***
	 * generates a unique transaction ID based on its cinemacode and date and time
	 * @return TID transactionID
	 */
	public String generateTID() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		return cinemaCode + dateTime.format(format);
	}
	
	/***
	 * gets the transactionID
	 * @return String transactionID of the transaction
	 */
	public String getTransactionID() {
		return transactionID;
	}
	/***
	 * sets the transaction ID
	 * @param transactionID, sets the transactionID
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	/***
	 * gets the transaction amount
	 * @return double transaction amount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
	/***
	 * sets the transaction amount
	 * @param transactionAmount amount in the transaction
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	/***
	 * gets the name of the movie goer
	 * @return String name of the movie goer
	 */
	public String getName() {
		return name;
	}
	/***
	 * sets the name of the movieGoer on the transaction
	 * @param name name of movieGoer
	 */
	public void setName(String name) {
		this.name = name;
	}
	/***
	 * gets the mobile number
	 * @return String mobile number of movie goer
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * 
	 * @param mobileNumber	sets a new mobilenumber for the transaction
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/***
	 * gets the movie goer's email
	 * @return String email of the movie goer
	 */
	public String getEmail() {
		return email;
	}
	/**
	 *sets the movie goer's email
	 * @param email new email of the movieGoer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/***
	 * gets the date and time of the transaction
	 * @return LocalDateTime 
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	/**
	 * sets the date and time of the transasction
	 * @param dateTime new date and time of the transaction
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	/***
	 * gets the movieID that was booked
	 * @return String 
	 */
	public int getMovieID() {
		return movieID;
	}
	/**
	 * sets the movieID
	 * @param movieID new movieID of the setter
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	/***
	 * gets the cinema code
	 * @return String  CinemaCode
	 */
	public String getCinemaCode() {
		return cinemaCode;
	}
	/**
	 * sets the cinemaCode of the cinema booked
	 * @param cinemaCode new cinemaCode
	 */
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	/***
	 * 
	 * @return ArrayList&lt;Integer&gt;
	 */
	public ArrayList<Integer> getSeatIDList() {
		return seatIDList;
	}
	/***
	 * sets the seatIDList, list of seatID booked
	 * @param seatIDList the new array of seat
	 */
	public void setSeatIDList(ArrayList<Integer> seatIDList) {
		this.seatIDList = seatIDList;
	}
	
	
	
	

}
