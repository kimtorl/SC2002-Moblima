/*
 * Implemented by all classes that are for account functionalities.
 */
package boundary;

/**
 * @author Cross
 * Implemented by all classes that are for account functionalities.
 */
public interface Capability{
	
	
	
	/**
	 * Perform capability.
	 * Each class has a single responsibility.
	 */
	public void performCapability();
	
	/**
	 * toString method for printing the capability.
	 *
	 * @return the string
	 */
	public String toString();
}
