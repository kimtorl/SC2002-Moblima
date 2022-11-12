package boundary;

import java.io.Serializable;

public class Top5MovieByRating implements Capability, Serializable {

	private static final long serialVersionUID =33L;


	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}

	
	public String toString() {
		String str = "List Top 5 Movies by Rating";
		return str;
	}
}
