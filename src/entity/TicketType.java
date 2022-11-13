/*
 * 
 */
package entity;

/**
 * The Enum TicketType indicating the type of ticket. Such as
 * Adult ticket, Student ticket, Senior Citizen ticket, as they follow 
 * different pricing
 */
public enum TicketType {
	
	
	/**
	 * Enumerator for ticket type:Adult
	 */
	ADULT("Adult"),
	/**
	 * Enumerator for ticket Student
	 */
	STUDENT("Student"),
	/**
	 * Enumerator for ticket type:Senior Citizen
	 */
	SENIORCITIZEN("Senior Citizen");
	
	/**
	 * name for enumrator
	 */
	private final String name;
	
	//constructor
	/**
	 * @param s
	 */
	TicketType(String s){
		name = s;
	}

	/**
	 *method to print the enumerator's name
	 */
	public String toString() {
		return this.name;
	}
}
