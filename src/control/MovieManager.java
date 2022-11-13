/*
 * 
 */
package control;

import java.util.ArrayList;

import entity.Movie;
import entity.ShowingStatus;
import entity.TypeOfMovie;

public interface MovieManager {

	//returns all the movies from the file
	/**
	 * Gets the all movie.
	 *
	 * @return the all movie
	 */
	//if file not found, an empty ArrayList is returned
	public ArrayList<Movie> getAllMovie();
		
		
	/**
	 * Write to file.
	 *
	 * @param movieList the movie list
	 */
	//writes an ArrayList of Movie to the file
	public void writeToFile(ArrayList<Movie> movieList);
	
	
	//reads the current list of movies if file exists
	//check for duplicates, return false if duplicates found
	/**
	 * Creates the movie.
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
	 * @return true, if successful
	 */
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
	/**
	 * Find movie.
	 *
	 * @param movieID the movie ID
	 * @return the movie
	 */
	//return null if not found
	public Movie findMovie(int movieID);
	
	/**
	 * Find movie.
	 *
	 * @param movieList the movie list
	 * @param movieID the movie ID
	 * @return the movie
	 */
	public Movie findMovie(ArrayList<Movie> movieList, int movieID);
	
	//returns all the movies that have the movieTitle
	// as the same movie titles are stored in various versions such as 2D,3D, Blockbuster
	/**
	 * Find all movie of title.
	 *
	 * @param movieList the movie list
	 * @param movieTitle the movie title
	 * @return the array list
	 */
	// empty ArrayList is returned if nothing found
	public ArrayList<Movie> findAllMovieOfTitle(ArrayList<Movie> movieList, String movieTitle);
	
	
	//reads movieList from file
	//update movie attributes
	/**
	 * Update movie ID.
	 *
	 * @param oldMovieID the old movie ID
	 * @param newMovieID the new movie ID
	 * @return true, if successful
	 */
	//saved updated movieList
	public boolean updateMovieID(int oldMovieID, int newMovieID);
	
	
	
	/**
	 * Update movie title.
	 *
	 * @param oldMovieTitle the old movie title
	 * @param newMovieTitle the new movie title
	 * @return true, if successful
	 */
	//updates all movies with oldMovieTitle to newMovieTitle
	public boolean updateMovieTitle(String oldMovieTitle, String newMovieTitle);
	
	/**
	 * Update movie type.
	 *
	 * @param movieID the movie ID
	 * @param newMovieType the new movie type
	 * @return true, if successful
	 */
	//update movieType of a specific movie
	public boolean updateMovieType(int movieID, TypeOfMovie newMovieType);
	
	
	/**
	 * Update movie language.
	 *
	 * @param movieID the movie ID
	 * @param newLanguage the new language
	 * @return true, if successful
	 */
	//updates a movie's dubbed language
	public boolean updateMovieLanguage(int movieID, String newLanguage);
	
	/**
	 * Update movie duration.
	 *
	 * @param movieTitle the movie title
	 * @param newDuration the new duration
	 * @return true, if successful
	 */
	//updates the duration of all movies of the same title
	public boolean updateMovieDuration(String movieTitle, int newDuration);
	
	/**
	 * Update movie showing status.
	 *
	 * @param movieTitle the movie title
	 * @param newShowingStatus the new showing status
	 * @return true, if successful
	 */
	//updates the showingStatus of all movies of the same title
	public boolean updateMovieShowingStatus(String movieTitle, ShowingStatus newShowingStatus);
	
	/**
	 * Update movie synopsis.
	 *
	 * @param movieTitle the movie title
	 * @param newSynopsis the new synopsis
	 * @return true, if successful
	 */
	//updates the Synopsis of all movies of the same title
	public boolean updateMovieSynopsis(String movieTitle, String newSynopsis);
	
	/**
	 * Update movie director.
	 *
	 * @param movieTitle the movie title
	 * @param newDirector the new director
	 * @return true, if successful
	 */
	//updates the Director of all movies of the same title
	public boolean updateMovieDirector(String movieTitle, String newDirector);
	
	/**
	 * Update movie cast.
	 *
	 * @param movieTitle the movie title
	 * @param newCast the new cast
	 * @return true, if successful
	 */
	//updates the cast of all movies of the same title
	public boolean updateMovieCast(String movieTitle, ArrayList<String> newCast);
	
	/**
	 * Update movie review.
	 *
	 * @param movieTitle the movie title
	 * @param newReview the new review
	 * @return true, if successful
	 */
	//updates the reviews of all movies of the same title
	public boolean updateMovieReview(String movieTitle, ArrayList<String> newReview);
	
	/**
	 * Update movie rating.
	 *
	 * @param movieTitle the movie title
	 * @param newRating the new rating
	 * @return true, if successful
	 */
	//updates the rating of all movies of the same title
	public boolean updateMovieRating(String movieTitle, ArrayList<Integer> newRating);
	
	/**
	 * Update ticket sales.
	 *
	 * @param movieID the movie ID
	 * @param numOfTickets the num of tickets
	 */
	//updates all movies of same title's ticket sales when a transaction is made
	public void updateTicketSales(int movieID, int numOfTickets);
		
	//reads movieList from file
	//delete a movie based on movieID 
	/**
	 * Delete movie.
	 *
	 * @param movieID the movie ID
	 * @return true, if successful
	 */
	//save updated movieList to file
	public boolean deleteMovie(int movieID);
	
	/**
	 * Display movie info.
	 *
	 * @param movieID the movie ID
	 */
	//displays Info
	public void displayMovieInfo(int movieID);
	
	/**
	 * List all movies.
	 */
	//prints out all movies
	public void listAllMovies(boolean regardlessOfShowingStatus);
			
}
