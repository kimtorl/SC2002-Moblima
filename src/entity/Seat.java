/*
 * 
 */
package entity;

import java.io.Serializable;

/**
 * @author Cross
 * The seat class tells us information about the location of the seat and whether
 * it is booked or not. We can also retrieve information on the seat or
 * modify them.
 */
public class Seat implements Serializable{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5L;
	//Attributes
	/** The seat ID, row size * row number + column number. */
	private int seatID;
	
	/** The row num. */
	private int rowNum;
	
	/** The col num. */
	private int colNum;
	
	/** The is booked. */
	private boolean isBooked;
	
	//Constructor
	/**
	 * Instantiates a new seat.
	 *
	 * @param seatID the seat ID
	 * @param rowNum the row num
	 * @param colNum the col num
	 */
	public Seat(int seatID, int rowNum, int colNum) {
		this.seatID = seatID;
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.isBooked = false;
	}
	
	//getters and setters
	/**
	 * Gets the seat ID.
	 *
	 * @return the seat ID
	 */
	public int getSeatID() {
		return seatID;
	}
	
	/**
	 * Sets the seat ID.
	 *
	 * @param seatID the new seat ID
	 */
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	
	/**
	 * Gets the row num.
	 *
	 * @return the row num
	 */
	public int getRowNum() {
		return rowNum;
	}
	
	/**
	 * Sets the row num.
	 *
	 * @param rowNum the new row num
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	/**
	 * Gets the col num.
	 *
	 * @return the col num
	 */
	public int getColNum() {
		return colNum;
	}
	
	/**
	 * Sets the col num.
	 *
	 * @param colNum the new col num
	 */
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	
	/**
	 * Checks if is booked.
	 *
	 * @return true, if is booked
	 */
	public boolean isBooked() {
		return isBooked;
	}
	
	/**
	 * Sets the booked.
	 *
	 * @param isBooked the new booked
	 */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	
}
