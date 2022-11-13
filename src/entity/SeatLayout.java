/*
 * 
 */
package entity;

import java.io.Serializable;

/**
 * The Class SeatLayout.
 * Seat layour represents the seatlayout in a cinema, with the size 
 * represented as no.of rows and columns.
 * @author Cross
 */
public class SeatLayout implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4L;
	//Attributes
	/** The seat layout. */
	private Seat[][] seatLayout;
	
	/** The num of row. Default value is 8. */
	private int numOfRow = 8; 
	
	/** The num of col.  Default value is 8. */
	private int numOfCol = 8; 
	
	//Constructor 
	/**
	 * Instantiates a new seat layout.
	 */
	public SeatLayout() {
		seatLayout = new Seat[numOfRow][numOfCol];
		
		for (int row =0; row < numOfRow; row++) {
			for(int col=0; col< numOfCol; col++) {
				seatLayout[row][col] = new Seat(row*numOfRow + col, row, col);
			}
		}
		
	}
	
	
	//Constructor with params
	/**
	 * Instantiates a new seat layout.
	 *
	 * @param rowSize the row size
	 * @param colSize the col size
	 */
	public SeatLayout(int rowSize, int colSize) {
		this.numOfRow = rowSize;
		this.numOfCol = colSize;
		seatLayout = new Seat[rowSize][colSize];
		
		for (int row =0; row < rowSize; row++) {
			for(int col=0; col< colSize; col++) {
				seatLayout[row][col] = new Seat(row*rowSize + col, row, col);
			}
		}
		
	}
	
	
	/**
	 * Display seat layout. prints out the cinema Seating layout in a matrix 
	 */
	public void displaySeatLayout() {
		//print out the column labels
		System.out.print("row\\col");
		for (int col=0; col < getNumOfCol();col++) {
			System.out.printf("%4d",col);
		}
		System.out.println();
		
		//print out the matrix with row labels
		for (int row=0; row < getNumOfRow(); row++) {
			System.out.printf("%4c   ",row+65); //row label in Alphabets
			for(int col=0; col< getNumOfCol(); col++) {
				if(seatLayout[row][col].isBooked())
					System.out.printf("%4s", "X"); //seat not available
				else
					System.out.printf("%4s", "O"); //seat is available
			}
			System.out.println();
		}
		
	}
	

	/**
	 * Book seat.
	 * 	returns negative 1 if seat is already booked or invalid seatCode
	 * returns seatID if seat is booked successfully
	 *
	 * @param seatCode the seat code
	 * @return the int
	 */
	public int bookSeat(String seatCode) {
		int row = seatCode.charAt(0)-65; //convert the alphabetical row into int
		seatCode = seatCode.substring(1);
		int col;
		try {
			col = Integer.parseInt(seatCode); 
		}catch(Exception e) {
			return -1;
		}
		
		if(row<0 || row>=numOfRow || col<0 || col >=numOfCol) return -1;//invalid seatCode
		
		if(seatLayout[row][col].isBooked()) return -1;
		
		seatLayout[row][col].setBooked(true);
		return seatLayout[row][col].getSeatID();
		
	}
	

	/**
	 * Gets the num of row.
	 *
	 * @return the num of row
	 */
	public int getNumOfRow() {
		return numOfRow;
	}

	/**
	 * Sets the num of row.
	 *
	 * @param numOfRow the new num of row
	 */
	public void setNumOfRow(int numOfRow) {
		this.numOfRow = numOfRow;
	}

	/**
	 * Gets the num of col.
	 *
	 * @return the num of col
	 */
	public int getNumOfCol() {
		return numOfCol;
	}

	/**
	 * Sets the num of col.
	 *
	 * @param numOfCol the new num of col
	 */
	public void setNumOfCol(int numOfCol) {
		this.numOfCol = numOfCol;
	}
	
	/**
	 * Gets the seat layout.
	 *
	 * @return the seat layout
	 */
	public Seat[][] getSeatLayout(){
		return this.seatLayout;
	}
	
}
