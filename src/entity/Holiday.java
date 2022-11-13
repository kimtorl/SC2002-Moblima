package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Holiday implements Serializable {

	private static final long serialVersionUID =7L;
	private LocalDate date;
	
	public Holiday(LocalDate d) {
		date = d;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
