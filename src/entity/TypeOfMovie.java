package entity;

public enum TypeOfMovie {
	BLOCKBUSTER_2D("2D Blockbuster"),
	BLOCKBUSTER_3D("3D Blockbuster"),
	NONBLOCKBUSTER_2D("2D"),
	NONBLOCKBUSTER_3D("3D");
	
	
	private final String name;
	
	//constructor
	TypeOfMovie(String s){
		name = s;
	}
	
	public String toString() {
		return this.name;
	}
	
}
