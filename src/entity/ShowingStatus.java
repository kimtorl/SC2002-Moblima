/*
 * 
 */
package entity;

/**
 * The Enum ShowingStatus.
 */
public enum ShowingStatus {
	
	/** The coming soon. */
	COMING_SOON("Coming Soon"),
	
	/** The preview. */
	PREVIEW("Preview"),
	
	/** The now showing. */
	NOW_SHOWING("Now Showing"),
	
	/** The end of showing. */
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
