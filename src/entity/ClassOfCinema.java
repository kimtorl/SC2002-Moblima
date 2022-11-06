package entity;

public enum ClassOfCinema {
	STANDARD("Standard"),
	PLATINUM("Platinum Movie Suites");
	
	private final String name;
	
	//constructor
	ClassOfCinema(String s){
		name = s;
	}
	
	public String toString() {
		return this.name;
	}
}
