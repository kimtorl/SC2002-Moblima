package control;

import java.util.ArrayList;

import entity.Movie;
import entity.ShowingStatus;
import entity.TypeOfMovie;

public interface MovieManager {

	//returns all the movies from the file
	//if file not found, an empty ArrayList is returned
	public ArrayList<Movie> getAllMovie();
		
		
	//writes an ArrayList of Movie to the file
	public void writeToFile(ArrayList<Movie> movieList);
	
	
	//reads the current list of movies if file exists
	//check for duplicates, return false if duplicates found
	//creates a movie and store to file
	public  boolean createMovie(int movieID,
			String movieTitle,
			TypeOfMovie movieType, 
			String language, 
			int duration,
			ShowingStatus showingStatus,
			String synopsis,
			String director,
			ArrayList<String> cast,
			ArrayList<String> pastReviews,
			ArrayList<Integer> pastRatings);
	
	//finds and returns a specific movie based on movieID
	//return null if not found
	public Movie findMovie(ArrayList<Movie> movieList, int movieID);
	
	//returns all the movies that have the movieTitle
	// as the same movie titles are stored in various versions such as 2D,3D, Blockbuster
	// empty ArrayList is returned if nothing found
	public ArrayList<Movie> findAllMovieOfTitle(ArrayList<Movie> movieList, String movieTitle);
	
	
	//reads movieList from file
	//update movie attributes
	//saved updated movieList
	public boolean updateMovieID(int oldMovieID, int newMovieID);
	
	
	
	//updates all movies with oldMovieTitle to newMovieTitle
	public boolean updateMovieTitle(String oldMovieTitle, String newMovieTitle);
	
	//update movieType of a specific movie
	public boolean updateMovieType(int movieID, TypeOfMovie newMovieType);
	
	
	//updates a movie's dubbed language
	public boolean updateMovieLanguage(int movieID, String newLanguage);
	
	//updates the duration of all movies of the same title
	public boolean updateMovieDuration(String movieTitle, int newDuration);
	
	//updates the showingStatus of all movies of the same title
	public boolean updateMovieShowingStatus(String movieTitle, ShowingStatus newShowingStatus);
	
	//updates the Synopsis of all movies of the same title
	public boolean updateMovieSynopsis(String movieTitle, String newSynopsis);
	
	//updates the Director of all movies of the same title
	public boolean updateMovieDirector(String movieTitle, String newDirector);
	
	//updates the cast of all movies of the same title
	public boolean updateMovieCast(String movieTitle, ArrayList<String> newCast);
	
	//updates the reviews of all movies of the same title
	public boolean updateMovieReview(String movieTitle, ArrayList<String> newReview);
	
	//updates the rating of all movies of the same title
	public boolean updateMovieRating(String movieTitle, ArrayList<Integer> newRating);
	
	
	//reads movieList from file
	//delete a movie based on movieID 
	//save updated movieList to file
	public boolean deleteMovie(int movieID);
	
	//displays Info
	public void displayMovieInfo(int movieID);
			
}
