
package entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Class Holiday.
 */
public class Holiday implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID =7L;
	
	/** The date. YYYY MM DD */
	private LocalDate date;
	
	/**
	 * Instantiates a new holiday.
	 *
	 * @param d the d
	 */
	public Holiday(LocalDate d) {
		date = d;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
