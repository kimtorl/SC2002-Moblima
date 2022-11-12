package boundary;

import java.io.Serializable;

import control.TransactionManager;

public class ViewBookingHistory implements Capability, Serializable {

	private static final long serialVersionUID = 38L;
	
	TransactionManager transactionMgr;
	
	public ViewBookingHistory(TransactionManager transactionMgr) {
		this.transactionMgr = transactionMgr;
	}

	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}

	

	
	public String toString() {
		String str = "View Booking History";
		return str;
	}
}
