package entity;

/**
 * @author Cross
 *
 */
public enum TypeOfMovie {
	
	/**
	 * Enumerator for 2D Blockbuster
	 */
	BLOCKBUSTER_2D("2D Blockbuster"),
	/**
	 * Enumerator for 3D Blockbuster
	 */
	BLOCKBUSTER_3D("3D Blockbuster"),
	/**
	 * Enumerator for normal 2D movies
	 */
	NONBLOCKBUSTER_2D("2D"),
	/**
	 * Enumerator for normal 3D movies
	 */
	NONBLOCKBUSTER_3D("3D");
	
	/**
	 * String attribute for enumerator's name
	 */
	private final String name;
	
	//constructor
	/** each enumerator has its own name
	 * @param s
	 */
	TypeOfMovie(String s){
		name = s;
	}
	
	/**
	 *method to print the type of movie's name
	 */
	public String toString() {
		return this.name;
	}
	
}
