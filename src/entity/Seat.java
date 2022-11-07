package entity;

import java.io.Serializable;

public class Seat implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	//Attributes
	private int seatID;
	private int rowNum;
	private int colNum;
	private boolean isBooked;
	
	//Constructor
	public Seat(int seatID, int rowNum, int colNum) {
		this.seatID = seatID;
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.isBooked = false;
	}
	
	//getters and setters
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	
}
