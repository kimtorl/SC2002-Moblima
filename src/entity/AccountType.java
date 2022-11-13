/*
 * 
 */
package entity;

/**
 * The Enum AccountType.
 */
public enum AccountType {
	
	/** The admin. */
	ADMIN("Cinema Staff"),
	
	/** The moviegoer. */
	MOVIEGOER("Movie-goer");
	
	/** The name. More user friendly display*/
	private final String name;
	
	/**
	 * Instantiates a new account type.
	 * @param s the s
	 */
	AccountType(String s){
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
