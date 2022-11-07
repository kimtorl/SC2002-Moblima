package entity;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class Transaction {
	
	private String transactionID;
	private double transactionAmount;
	private String name;
	private String mobileNumber;
	private String email;
	private LocalDateTime dateTime;
	private String movieTitle;
	private String cinemaCode;
	
	public Transaction() {};
	public Transaction(	String transactionID,
			double transactionAmount,
			String name,
			String mobileNumber,
			String email,
			LocalDateTime dateTime,
			String movieTitle,
			String cinemaCode) 
	{
		this.transactionID = transactionID;
		this.transactionAmount =transactionAmount;
		this.name =name;
		this.mobileNumber =mobileNumber;
		this.email =email;
		this.dateTime =dateTime;
		this.movieTitle =movieTitle;
		this.cinemaCode =cinemaCode;
	}
	
	public String toString(){
		String s = "transactionID: "+transactionID+"\nAmount: "+transactionAmount+"\nName: "+name+
				"Mobile.no : "+mobileNumber+"\nEmail: "+email+"\nDate&Time of transaction: "+dateTime
				+"Move: "+movieTitle+"CinemaCode: "+cinemaCode;
		return s;
		
	}
	

}
