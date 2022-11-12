package entity;

public enum TicketType {
	
	
	ADULT("Adult"),
	STUDENT("Student"),
	SENIORCITIZEN("Senior Citizen");
	
	private final String name;
	
	//constructor
	TicketType(String s){
		name = s;
	}
	
	public String toString() {
		return this.name;
	}
}
