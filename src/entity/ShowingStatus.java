package entity;

/**
 * @author Cross
 *
 */
public enum ShowingStatus {
	/**
	 * 
	 */
	COMING_SOON("Coming Soon"),
	/**
	 * 
	 */
	PREVIEW("Preview"),
	/**
	 * 
	 */
	NOW_SHOWING("Now Showing"),
	/**
	 * 
	 */
	END_OF_SHOWING("End Of Showing");
	 
	/**
	 * 
	 */
	private final String name;
	
	//constructor
	/**
	 * @param s
	 */
	ShowingStatus(String s){
		name = s;
	}
	
	/**
	 *
	 */
	public String toString() {
		return this.name;
	}
}
