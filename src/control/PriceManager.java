package control;

import java.time.LocalDateTime;

import entity.ClassOfCinema;
import entity.TicketType;
import entity.TypeOfMovie;

public interface PriceManager {

	public void initialisePrice();
	
	public double getPrice(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime);
	
	public boolean updatePrice(TicketType ticketType, TypeOfMovie movieType, ClassOfCinema cinemaClass, LocalDateTime dateTime, double newPrice);
	
}
	
