/*
 * 
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Cross
 *
 */
public class Movie implements Serializable{	
	
	/** The Constant serialVersionUID. */
	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	
	/** The movie ID. */
	private int movieID;
	
	/** The movie title. */
	private String movieTitle;
	
	/** The movie type. */
	private	TypeOfMovie movieType;
	
	/** The language. */
	private String language;
	
	/** The duration. */
	private int duration; //in minutes
	
	/** The showing status. */
	private ShowingStatus showingStatus;
	
	/** The synopsis. */
	private String synopsis;
	
	/** The director. */
	private String director;
	
	/** The cast. */
	private ArrayList<String> cast;
	
	/** The past reviews. */
	private ArrayList<String> pastReviews;
	
	/** The past ratings. */
	private ArrayList<Integer> pastRatings;
	
	/** The ticket sales. */
	private int ticketSales = 0;
	


	/**
	 * Instantiates a new movie.
	 */
	public Movie() {
		
	}
	
	/**
	 * Instantiates a new movie.
	 *
	 * @param movieID the movie ID
	 * @param movieTitle the movie title
	 * @param movieType the movie type
	 * @param language the language
	 * @param duration the duration
	 * @param showingStatus the showing status
	 * @param synopsis the synopsis
	 * @param director the director
	 * @param cast the cast
	 * @param pastReviews the past reviews
	 * @param pastRatings the past ratings
	 */
	public Movie(int movieID,
			String movieTitle,
			TypeOfMovie movieType, 
			String language,
			int duration,
			ShowingStatus showingStatus,
			String synopsis,
			String director,
			ArrayList<String> cast,
			ArrayList<String> pastReviews,
			ArrayList<Integer> pastRatings) 
	{
		this.movieID = movieID; //unique for each variation of the same movie e.g. 2D and 3D movie has diff movieID
		this.movieTitle = movieTitle;
		this.movieType = movieType;
		this.language = language;
		this.duration = duration;
		this.showingStatus = showingStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.pastReviews = pastReviews;
		this.pastRatings = pastRatings;
	}
	
	/**
	 * Dislay info.
	 */
	public void dislayInfo()
	{
		System.out.println("Movie ID: "+movieID);
		System.out.println("Movie Title: "+movieTitle);
		System.out.println(movieType);
		System.out.println("Language: "+language);
		System.out.println("Duration: "+duration+" minutes");
		System.out.println("Showing status: "+showingStatus);
		System.out.println("Synopsis: "+synopsis);
		System.out.println("Directed by "+director);
		System.out.print("Casts: ");
		for(int i=0;i<cast.size();i++) {
			if(i == cast.size()-1)
				System.out.println(cast.get(i));	//if last member of cast, don't print comma
			else
				System.out.print(cast.get(i)+", ");
		}
		double overallRating = getOverallReviewerRating();
		if(overallRating == -1) System.out.println("Overall Ratings: NA"); //displays NA if <= 1 rating
		else System.out.printf("Overall Ratings: %.1f\n",overallRating);
		
		for(int i=0; i<pastReviews.size();i++) {
			System.out.println("Reviewer "+(i+1)+": "+pastReviews.get(i));
		}
	}
	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "MovieID: " + movieID + ". Title: " + movieTitle;
	}
	
	//getters and setters
	/**
	 * Gets the movie ID.
	 *
	 * @return the movie ID
	 */
	public int getMovieID() {
		return movieID;
	}
	
	/**
	 * Sets the movie ID.
	 *
	 * @param movieID the new movie ID
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	/**
	 * Gets the movie title.
	 *
	 * @return the movie title
	 */
	public String getMovieTitle() {
		return movieTitle;
	}
	
	/**
	 * Sets the movie title.
	 *
	 * @param movieTitle the new movie title
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	/**
	 * Gets the movie type.
	 *
	 * @return the movie type
	 */
	public TypeOfMovie getMovieType() {
		return movieType;
	}
	
	/**
	 * Sets the movie type.
	 *
	 * @param movieType the new movie type
	 */
	public void setMovieType(TypeOfMovie movieType) {
		this.movieType = movieType;
	}
	
	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Gets the showing status.
	 *
	 * @return the showing status
	 */
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
	
	/**
	 * Sets the showing status.
	 *
	 * @param showingStatus the new showing status
	 */
	public void setShowingStatus(ShowingStatus showingStatus) {
		this.showingStatus = showingStatus;
	}
	
	/**
	 * Gets the synopsis.
	 *
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	/**
	 * Sets the synopsis.
	 *
	 * @param synopsis the new synopsis
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	/**
	 * Gets the director.
	 *
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * Sets the director.
	 *
	 * @param director the new director
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * Gets the cast.
	 *
	 * @return the cast
	 */
	public ArrayList<String> getCast() {
		return cast;
	}
	
	/**
	 * Sets the cast.
	 *
	 * @param cast the new cast
	 */
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}
	
	/**
	 * Gets the overall reviewer rating.
	 *
	 * @return the overall reviewer rating
	 */
	public double getOverallReviewerRating() {
		double value=0;
		
		if(pastRatings.size() <=1) return -1; //Overall reviewer rating will only be displayed if there are more than one individual rating
		
		for(int i=0; i< pastRatings.size();i++) {
			value += pastRatings.get(i);
		}
		value = value/ (double) pastRatings.size();
		
		return value;
	}

	/**
	 * Gets the past reviews.
	 *
	 * @return the past reviews
	 */
	public ArrayList<String> getPastReviews() {
		return pastReviews;
	}
	
	/**
	 * Sets the past reviews.
	 *
	 * @param pastReviews the new past reviews
	 */
	public void setPastReviews(ArrayList<String> pastReviews) {
		this.pastReviews = pastReviews;
	}
	
	/**
	 * Gets the past ratings.
	 *
	 * @return the past ratings
	 */
	public ArrayList<Integer> getPastRatings() {
		return pastRatings;
	}
	
	/**
	 * Sets the past ratings.
	 *
	 * @param pastRatings the new past ratings
	 */
	public void setPastRatings(ArrayList<Integer> pastRatings) {
		this.pastRatings = pastRatings;
	}
	
	/**
	 * Gets the ticket sales.
	 *
	 * @return the ticket sales
	 */
	public int getTicketSales() {
		return ticketSales;
	}

	/**
	 * Sets the ticket sales.
	 *
	 * @param ticketSales the new ticket sales
	 */
	public void setTicketSales(int ticketSales) {
		this.ticketSales = ticketSales;
	}
	
	/**
	 * Plus one sales.
	 */
	public void plusOneSales() {
		this.ticketSales++;
	}
	

	

	
	
	

}
