package entity;

import java.io.Serializable;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 

public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 10L;
	private String transactionID;
	private double transactionAmount;
	private String name;
	private String mobileNumber;
	private String email;
	private LocalDateTime dateTime;
	private int movieID;
	private String cinemaCode;
	private ArrayList<Integer> seatIDList;
	
	public Transaction() {};
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
	
	
	public String toString(){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String s = "transactionID: "+transactionID+"\nAmount: "+transactionAmount+"\nName: "+name+
				"\nMobile.no : "+mobileNumber+"\nEmail: "+email+"\nDate&Time of transaction: "+dateTime.format(format)
				+"\nMovieID: "+movieID+"\nCinemaCode: "+cinemaCode + '\n';
		return s;
		
	}
	
	public String generateTID() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		return cinemaCode + dateTime.format(format);
	}
	
	//getters and setters
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	public ArrayList<Integer> getSeatIDList() {
		return seatIDList;
	}
	public void setSeatIDList(ArrayList<Integer> seatIDList) {
		this.seatIDList = seatIDList;
	}
	
	
	
	

}
