package entity;

import java.io.Serializable;

public class SeatLayout implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	//Attributes
	private Seat[][] seatLayout;
	private int numOfRow = 8; //default values
	private int numOfCol = 8; //default values
	
	//Constructor 
	public SeatLayout() {
		seatLayout = new Seat[numOfRow][numOfCol];
		
		for (int row =0; row < numOfRow; row++) {
			for(int col=0; col< numOfCol; col++) {
				seatLayout[row][col] = new Seat(row*numOfRow + col, row, col);
			}
		}
		
	}
	
	
	//Constructor with params
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
	
	
	//prints out the cinema Seating layout in a matrix
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
	
	//returns -1 if seat is already booked or invalid seatCode
	//returns seatID if seat is booked successfully
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
	
	//getters and setters
	public int getNumOfRow() {
		return numOfRow;
	}

	public void setNumOfRow(int numOfRow) {
		this.numOfRow = numOfRow;
	}

	public int getNumOfCol() {
		return numOfCol;
	}

	public void setNumOfCol(int numOfCol) {
		this.numOfCol = numOfCol;
	}
	
	public Seat[][] getSeatLayout(){
		return this.seatLayout;
	}
	
}
