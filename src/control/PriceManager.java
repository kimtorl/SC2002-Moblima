/*
 * 
 */
package control;

import java.time.LocalDateTime;

import entity.ClassOfCinema;
import entity.TicketType;
import entity.TypeOfMovie;

public interface PriceManager {

	/**
	 * Initialise price.
	 */
	public void initialisePrice();
	
	/**
	 * Gets the price.
	 *
	 * @param ticketType the ticket type
	 * @param movieType the movie type
	 * @param cinemaClass the cinema class
	 * @param dateTime the date time
	 * @return the price
	 */
	public double getPrice(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime);
	
	/**
	 * Update price.
	 *
	 * @param ticketType the ticket type
	 * @param movieType the movie type
	 * @param cinemaClass the cinema class
	 * @param dateTime the date time
	 * @param newPrice the new price
	 * @return true, if successful
	 */
	public boolean updatePrice(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime, double newPrice);
	
}
	
