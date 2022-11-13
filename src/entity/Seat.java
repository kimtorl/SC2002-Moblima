package entity;

import java.io.Serializable;

/**
 * @author Cross
 *
 */
public class Seat implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	//Attributes
	/**
	 * 
	 */
	private int seatID;
	/**
	 * 
	 */
	private int rowNum;
	/**
	 * 
	 */
	private int colNum;
	/**
	 * 
	 */
	private boolean isBooked;
	
	//Constructor
	/**
	 * @param seatID
	 * @param rowNum
	 * @param colNum
	 */
	public Seat(int seatID, int rowNum, int colNum) {
		this.seatID = seatID;
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.isBooked = false;
	}
	
	//getters and setters
	/**
	 * @return
	 */
	public int getSeatID() {
		return seatID;
	}
	/**
	 * @param seatID
	 */
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	/**
	 * @return
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * @return
	 */
	public int getColNum() {
		return colNum;
	}
	/**
	 * @param colNum
	 */
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	/**
	 * @return
	 */
	public boolean isBooked() {
		return isBooked;
	}
	/**
	 * @param isBooked
	 */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	
}
