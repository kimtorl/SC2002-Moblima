package boundary;

import java.io.Serializable;

public class BookTicket implements Capability, Serializable {

	private static final long serialVersionUID = 37L;



	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}


	
	public String toString() {
		String str = "Book Tickets";
		return str;
	}
}
