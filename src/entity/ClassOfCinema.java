/*
 * 
 */
package entity;

/**
 * The Enum ClassOfCinema.
 */
public enum ClassOfCinema {
	
	/** The standard. */
	STANDARD("Standard"),
	
	/** The platinum. */
	PLATINUM("Platinum Movie Suites");
	
	/** The name. */
	private final String name;
	
	/**
	 * Instantiates a new class of cinema.
	 *
	 * @param s the s
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
