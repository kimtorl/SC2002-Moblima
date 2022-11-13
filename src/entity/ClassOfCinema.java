/*
 * 
 */
package entity;

/**
 * The Enum ClassOfCinema, affects prices.
 */
public enum ClassOfCinema {
	
	/** The standard. */
	STANDARD("Standard"),
	
	/** The platinum class cinema. More expensive */
	PLATINUM("Platinum Movie Suites");
	
	/** The name. More user friendly display */
	private final String name;
	
	/**
	 * Instantiates a new class of cinema.
	 *
	 * @param s the name
	 */
	//constructor
	ClassOfCinema(String s){
		name = s;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return this.name;
	}
}
