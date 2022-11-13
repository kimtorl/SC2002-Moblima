/*
 * 
 */
package entity;

/**
 * The Enum ShowingStatus of a movie.
 */
public enum ShowingStatus {
	
	/** Enumerator for coming soon status. */
	COMING_SOON("Coming Soon"),
	
	/** Enumerator for preview status. */
	PREVIEW("Preview"),
	
	/** Enumerator for now showing status. */
	NOW_SHOWING("Now Showing"),
	
	/** Enumerator for end of showing status. */
	END_OF_SHOWING("End Of Showing");
	 
	/** The name. */
	private final String name;
	
	//constructor
	/**
	 * Instantiates a new showing status.
	 *
	 * @param s the s
	 */
	ShowingStatus(String s){
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
