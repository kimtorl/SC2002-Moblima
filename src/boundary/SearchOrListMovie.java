package boundary;

import java.io.Serializable;

public class SearchOrListMovie implements Capability, Serializable {

	private static final long serialVersionUID = 35L;



	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}


	
	public String toString() {
		String str = "Search/List Movies";
		return str;
	}
	
	
	//NEED TO CHECK MOVIE'S SHOWING STATUS WHEN LISTING!
}
