package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.SeatLayout;
import entity.ShowingStatus;
import entity.Showtime;

public class ShowtimeFileManager implements ShowtimeManager, Serializable{

	
	private static final long serialVersionUID = 21L;
	//Attributes
	private CinemaManager cinemaMgr;
	
	//Constructor
	public ShowtimeFileManager(CinemaManager cinemaMgr) {
		this.cinemaMgr = cinemaMgr;
	}

	//reads and return all Showtime available from all Cineplexes in an ArrayList
	public ArrayList<Showtime> getAllShowtime(){
		ArrayList<Cinema> cinemaList = cinemaMgr.getAllCinema();
		
		ArrayList<Showtime> showtimeList = new ArrayList<Showtime>();
		
		for(int i=0; i < cinemaList.size(); i++) {
			if(cinemaList.get(i).getShowtimeList()!= null) //check not null
				showtimeList.addAll(cinemaList.get(i).getShowtimeList()); //append each cinemaList to a single ArrayList
		}
		
		return showtimeList;
	}
		
	
	//returns true if successfully created
	//returns false if failed
	public boolean createShowtime(int cineplexID, String cinemaCode, Movie movie, LocalDateTime dateTime) {
		
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex(); //retrieve cineplexList
		
		Cinema cinema = cinemaMgr.findCinema(cineplexList, cineplexID, cinemaCode);
		if(cinema == null) return false; //cinema not found
		
		
		ArrayList<Showtime> showtimeList = cinema.getShowtimeList(); //retrieve showtimeList
		if(showtimeList == null) {
			showtimeList = new ArrayList<Showtime>();
			cinema.setShowtimeList(showtimeList);
		}
		
		//check for movie's showing status
		if(movie.getShowingStatus() == ShowingStatus.COMING_SOON || movie.getShowingStatus() == ShowingStatus.END_OF_SHOWING) {
			System.out.println("Movie has invalid Showing Status");
			return false;
		}
		
		//check for duplicate showtimes 
		if(isDuplicate(cineplexList.get(cineplexID).getCinemaList(), movie, dateTime)) {
			System.out.println("Duplicated showtime in the Cineplex!");
			return false;
		}
		//check for clashes within the cinema i.e. dateTime cannot clash
		if (hasClashes(showtimeList, movie, dateTime)) {
			System.out.println("Clashes with other showtimes in the Cinema!");
			return false;
		}
		
		showtimeList.add(new Showtime(cinemaCode, movie, dateTime, new SeatLayout()));
		
		//sort showtimeList 
		showtimeList.sort(new Comparator<Showtime>() {
			public int compare(Showtime s1, Showtime s2) {
				LocalDateTime dt1 = s1.getDateTime(), dt2 = s2.getDateTime();
				if(dt1.isBefore(dt2)) return -1;
				else if(dt1.isAfter(dt2)) return 1;
				return 0;
			}
		});
		
		//save to file
		cinemaMgr.writeToFile(cineplexList);
		
		return true;
	}
		
	//checks if showtime clashes with showtime to be added
	//Showtimes within a cinema cannot clash as one cinema can only show one movie at a time
	public boolean hasClashes(ArrayList<Showtime> showtimeList, Movie movie, LocalDateTime dateTime) {
		if(showtimeList == null) return false; //showtimeList not found
		
		LocalDateTime startTimeA = dateTime, endTimeA = dateTime.plusMinutes(movie.getDuration());
		LocalDateTime startTimeB, endTimeB;
		
		for (Showtime st : showtimeList) {
			startTimeB = st.getDateTime();
			endTimeB = startTimeB.plusMinutes(st.getMovie().getDuration());
			
			//startTimeA cannot be between startTimeB and endTimeB OR
			//endTimeA cannot be between StartTimeB and endTimeB
			if((startTimeB.isBefore(startTimeA) && endTimeB.isAfter(startTimeA)) || (startTimeB.isBefore(endTimeA) && endTimeB.isAfter(endTimeA))){
				return true; //showtime clashes
			}
		}
		return false;
	}
	
	
	//Showtimes within a cineplex should not have duplicates
	//i.e. A cineplex shouldn't have two showtimes with the same Movie and dateTime across cinemas
	//cinemaList contains cinemas from the same Cineplex
	public boolean isDuplicate(ArrayList<Cinema> cinemaList, Movie movie, LocalDateTime dateTime) {
		if (cinemaList ==null) return false; //cinemaList not found

		ArrayList<Showtime> showtimeList;
		for(int i=0; i < cinemaList.size(); i++) {
			if(cinemaList.get(i).getShowtimeList()== null) continue; //check for null
			
			showtimeList = cinemaList.get(i).getShowtimeList();
			for(Showtime st : showtimeList) {
				if(st.getMovie().getMovieID()==movie.getMovieID() && st.getDateTime().equals(dateTime))
					return true;
			}		
		}
		return false;
	}
	
	
	//return all the showtime Available for a movie across cineplexes
	public ArrayList<Showtime> findAllShowtimeByMovie(int movieID){
		ArrayList<Showtime> movieShowtimeList = new ArrayList<Showtime>();
			
		ArrayList<Showtime> showtimeList = getAllShowtime();
		
		//append all showtimes that shows this movie
		for(Showtime st : showtimeList) {
			if(st.getMovie().getMovieID() == movieID) {
				movieShowtimeList.add(st);
			}
		}
		
		return movieShowtimeList;
	}
	
	
	//return all the showtime available by given date across cineplexes
	public ArrayList<Showtime> findAllShowtimeByDate(LocalDate date){
		ArrayList<Showtime> movieShowtimeList = new ArrayList<Showtime>();
		
		ArrayList<Showtime> showtimeList = getAllShowtime();
		
		//append all showtimes that are on this date
		for(Showtime st : showtimeList) {
			if(st.getDateTime().toLocalDate().equals(date)) {
				movieShowtimeList.add(st);
			}
		}
		return movieShowtimeList;
	}
	
	
	//returns the corresponding showtime
	//returns null if not found
	public Showtime findShowtime(ArrayList<Cineplex> cineplexList, int cineplexID, String cinemaCode, LocalDateTime dateTime) {
		
		Cinema cinema = cinemaMgr.findCinema(cineplexList, cineplexID, cinemaCode);
		if(cinema == null) return null; //error checking
		
		ArrayList<Showtime> showtimeList = cinema.getShowtimeList();
		if(showtimeList == null) return null;
		
		for(int i=0; i<showtimeList.size();i++) {
			if(showtimeList.get(i).getDateTime().equals(dateTime))
				return showtimeList.get(i);
		}
		return null;
	}
	
	//updates a showtime with a new movie and save to file
	//the correct showtime to be updated is found using dateTime as its unique in a cinema
	public boolean updateShowtimeMovie(int cineplexID, String cinemaCode, LocalDateTime dateTime, Movie newMovie) {
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex();
		
		Showtime st = findShowtime(cineplexList, cineplexID, cinemaCode, dateTime);
		if(st == null) {
			System.out.println("Showtime not found!");
			return false;
		}
		
		st.setMovie(newMovie);
		System.out.println("Showtime has been updated with new movie!");
		
		cinemaMgr.writeToFile(cineplexList);
		return true;
	}
	
	
	//updates a showtime with a new dateTime and save to file
	public boolean updateShowtimeDateTime(int cineplexID, String cinemaCode, LocalDateTime oldDateTime, LocalDateTime newDateTime) {
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex();
		
		Showtime st = findShowtime(cineplexList, cineplexID, cinemaCode, oldDateTime);
		if(st == null) {
			System.out.println("Showtime not found!");
			return false;
		}
		
		st.setDateTime(newDateTime);
		System.out.println("Showtime has been updated with new dateTime!");
		
		cinemaMgr.writeToFile(cineplexList);
		return true;
	}
	
	//update a showtime with a new seatLayout and save to file
	public boolean updateShowtimeSeatLayout(String cinemaCode, LocalDateTime dateTime, SeatLayout seatLayout) {
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex();
		
		Showtime st=null;
		for(Cineplex cineplex : cineplexList) {
			st = findShowtime(cineplexList, cineplex.getCineplexID(), cinemaCode, dateTime);
			if (st!=null) break; //showtime found
		}
		if (st == null) return false; //showtime not found
		
		//update seat layout
		st.setSeatLayout(seatLayout);
		
		cinemaMgr.writeToFile(cineplexList);
		return true;
	}
	
	
	//deletes all showtimes in a cinema that has this movieID
	public boolean deleteShowTimeByMovieID(int cineplexID, String cinemaCode, int movieID) {
		boolean deleted = false;
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex();
		
		//get the correct cinema
		Cinema cinema = cinemaMgr.findCinema(cineplexList, cineplexID, cinemaCode);
		if (cinema == null) return deleted; //cinema not found
		
		ArrayList<Showtime> showtimeList = cinema.getShowtimeList();
		if(showtimeList == null) return deleted; //showtimeList not found
		
		
		//remove the showtime from the end of the list
		for(int i=showtimeList.size()-1; i>=0 ;i--) {
			if(showtimeList.get(i).getMovie().getMovieID() == movieID) {
				showtimeList.remove(i);
				deleted = true;
			}
		}
		
		cinemaMgr.writeToFile(cineplexList);
		return deleted;
	}
	
	
	//deletes a specific showtime in a cinema specified by dateTime
	public boolean deleteSpecificShowtime(int cineplexID, String cinemaCode, LocalDateTime dateTime) {
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex();
		
		//get the correct cinema
		Cinema cinema = cinemaMgr.findCinema(cineplexList, cineplexID, cinemaCode);
		if (cinema == null) return false; //cinema not found
		
		ArrayList<Showtime> showtimeList = cinema.getShowtimeList();
		if(showtimeList == null) return false; //showtimeList not found
		
		
		for(int i=0; i<showtimeList.size();i++) {
			if(showtimeList.get(i).getDateTime().equals(dateTime)) {
				showtimeList.remove(i);
				cinemaMgr.writeToFile(cineplexList);
				return true;
			}
		}
		return false;
	}
	
	
	//randomly creates showtime to populate the ShowtimeList of a cinema based off on current System dateTime
	public void populateShowtime(int cineplexID, String cinemaCode, LocalDateTime dateTime) {
		MovieManager movieMgr = new MovieFileManager();
		ArrayList<Movie> movieList = movieMgr.getAllMovie(); //get list of movies
		
		LocalDateTime now = dateTime; //take dateTime as currentTime
		LocalDateTime startTime;
		//Assume that the the earlist possible showtime is 10:00 and last possible show time is at 23:30
		if(now.toLocalTime().isAfter(LocalTime.of(21, 59))) { //if its currently after 21:59
			startTime = now.plusDays(1).truncatedTo(ChronoUnit.HOURS).withHour(10); //set startTime to next day at 10:00
		}
		else if(now.toLocalTime().isBefore(LocalTime.of(10, 0))) { //if its currently before 10:00
			startTime = now.truncatedTo(ChronoUnit.HOURS).withHour(10); //set startTime to current day at 10:00
		}
		else {
			startTime = now.truncatedTo(ChronoUnit.HOURS).plusHours(1); //set startTime to the next hour
		}
		
		//Randomise the startTime with 0-11 intervals of 5mins
		Random rand = new Random();
		startTime = startTime.plusMinutes(rand.nextInt(12) * 5);
		
		
		Movie movie;
		int attempts=0, maxAttempts =3;
		
		//Add 5 showtimes for each cinema
		for(int i=0; i < 5; i++) {
			movie = movieList.get(rand.nextInt(movieList.size())); //retrives a random Movie from the movieList
			if(createShowtime(cineplexID, cinemaCode, movie, startTime)) { //check showing status, showtime added successfully
				//Calculate the startTime of the nextMovie;
				startTime = startTime.plusMinutes(movie.getDuration()+30); //update startTime to 30mins after movie ended
				startTime = startTime.truncatedTo(ChronoUnit.HOURS).plusMinutes(15*((startTime.getMinute()+14)/15)); //round up to nearest 15min
				
			}
			else {
				i--; //Showtime not created.
				attempts++;
			}
			
			//last possible show time is at 23:30
			if(startTime.toLocalTime().isAfter(LocalTime.of(23, 30))) return;;
			
			if(attempts>maxAttempts) {
				attempts =0;
				startTime = startTime.truncatedTo(ChronoUnit.HOURS).plusMinutes(15*((startTime.getMinute()+14)/15)); //round up to nearest 15min
				startTime.plusHours(3);
				return;
			}
		}
	}
	
	
	//creates showtime for all cinemas
	public void populateAllCinemaShowtime(LocalDateTime dateTime) {
		ArrayList<Cineplex> cineplexList = cinemaMgr.getAllCineplex(); //retrieve cineplexList
		
		for(int i=0; i<cineplexList.size(); i++) {
			Cineplex cineplex = cineplexList.get(i);
			ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
			for(int j=0; j<cinemaList.size(); j++) {
				Cinema cinema = cinemaList.get(j);
				//if(cinema.getShowtimeList().size()==0)
				populateShowtime(cineplex.getCineplexID(),cinema.getCinemaCode(), dateTime); //populate if the showtimeList is empty
			}
		}
		
	}
	
	
}
