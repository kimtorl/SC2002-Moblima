package entity;

public enum AccountType {
	ADMIN("Cinema Staff"),
	MOVIEGOER("Movie-goer");
	
	private final String name;
	
	//constructor
	AccountType(String s){
		name = s;
	}
	
	public String toString() {
		return this.name;
	}
	
}
