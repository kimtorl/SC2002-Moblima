package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	
	private int movieID;
	private String movieTitle;
	private	TypeOfMovie movieType;
	private String language;
	private int duration; //in minutes
	private ShowingStatus showingStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> cast;
	private ArrayList<String> pastReviews;
	private ArrayList<Integer> pastRatings;
	
	public Movie() {
		
	}
	
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
		System.out.println("Overall Ratings: "+ getOverallReviewerRating());
	}
	
	
	public String toString() {
		return "MovieID: " + movieID + ". Title: " + movieTitle;
	}
	
	//getters and setters
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public TypeOfMovie getMovieType() {
		return movieType;
	}
	public void setMovieType(TypeOfMovie movieType) {
		this.movieType = movieType;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public ShowingStatus getShowingStatus() {
		return showingStatus;
	}
	public void setShowingStatus(ShowingStatus showingStatus) {
		this.showingStatus = showingStatus;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public ArrayList<String> getCast() {
		return cast;
	}
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}
	public double getOverallReviewerRating() {
		double value=0;
		
		for(int i=0; i< pastRatings.size();i++) {
			value += pastRatings.get(i);
		}
		value = value/ (double) pastRatings.size();
		
		return value;
	}

	public ArrayList<String> getPastReviews() {
		return pastReviews;
	}
	public void setPastReviews(ArrayList<String> pastReviews) {
		this.pastReviews = pastReviews;
	}
	public ArrayList<Integer> getPastRatings() {
		return pastRatings;
	}
	public void setPastRatings(ArrayList<Integer> pastRatings) {
		this.pastRatings = pastRatings;
	}
	

	
	
	

}
