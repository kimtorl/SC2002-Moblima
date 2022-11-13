/*
 * 
 */
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import entity.Movie;
import entity.ShowingStatus;
import entity.TypeOfMovie;

/**
 * The Class MovieFileManager. It manages movie files, allowing us to find movies via its
 * ID and return. It allows us to update movies on their attributes, such as duration,
 * showing status, synopsis, and so on. It allows us to display all movies and delete
 * movies. It is the controller class for movies.
 * 
 */
public class MovieFileManager implements MovieManager, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 20L;
	
	/** The Constant FILENAME. */
	//Attribute
	private static final String FILENAME = "Database/movies.txt"; //file that this class manages
	
	
	/**
	 * Instantiates a new movie file manager.
	 */
	//Empty constructor
	public MovieFileManager() {
	}
	
	
	
	//returns all the movies from the file
	/**
	 * Gets the all movie. It reads from the file and stores it into an ArrayList
	 * of Movie.
	 *
	 * @return the ArrayList of movies called movieList
	 */
	//if file not found, an empty ArrayList is returned
	@SuppressWarnings("unchecked")
	public ArrayList<Movie> getAllMovie(){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fis);
			movieList = (ArrayList<Movie>) in.readObject();
			in.close();
			
		}catch(IOException ex) {
			System.out.println("File not found!");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		return movieList;
	}
	
	
	/**
	 * Write to file an arrayList of movies.
	 *
	 * @param movieList the movie list
	 */
	//writes an ArrayList of Movie to the file
	public void writeToFile(ArrayList<Movie> movieList) {
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(movieList);
			out.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	//reads the current list of movies if file exists
	//check for duplicates, return false if duplicates found
	//creates a movie and store to file

	
	/**
	 * Creates the movie.
	 * reads the current list of movies if file exists
	 * gets movieList if there's an existing file, else initialise an empty ArrayList
	 * check for duplicates, return false if duplicates found
	 * creates a movie and append it to the list.
	 * sorts the movieList based on movieID. Then write the list to the file.
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
	public boolean createMovie(int movieID,
			String movieTitle,
			TypeOfMovie movieType, 
			String language, 
			int duration,
			ShowingStatus showingStatus,
			String synopsis,
			String director,
			ArrayList<String> cast,
			ArrayList<String> pastReviews,
			ArrayList<Integer> pastRatings) {
		
		ArrayList<Movie> movieList;
		File f = new File(FILENAME);
		
		if(f.exists()) movieList = getAllMovie(); //gets movieList if there's an existing file
		else movieList = new ArrayList<Movie>(); //else initialise an empty ArrayList
		
		//check duplicates
		for (int i=0; i <movieList.size();i++) {
			if(movieList.get(i).getMovieID() == movieID) {
				System.out.println("movieID already exists!");
				return false;
			}
		}
		
		//add to movieList
		movieList.add(new Movie(movieID, movieTitle, movieType, language, duration, showingStatus, synopsis, director, 
				cast, pastReviews, pastRatings));
		
		//sort movieList by movieID
		movieList.sort(new Comparator<Movie>() {
			public int compare(Movie m1, Movie m2) {
				return m1.getMovieID() - m2.getMovieID();
			}
		});
		
		writeToFile(movieList);
		
		return true;
	}
	
	/**
	 * Finds movie from the set of all movies using its movieID, iterates through the list
	 * until it finds a movie with the same movieID. If found,break and return the movie.
	 * 
	 * if list is empty/null, we will return null
	 * @param movieID the movie ID
	 * @return the Movie
	 */
	//overloaded method
	public Movie findMovie(int movieID) {
		ArrayList<Movie> movieList = getAllMovie();
		if(movieList ==null) return null; 
		Movie mov=null;
		
		for(int i=0; i < movieList.size(); i++) {
			if(movieList.get(i).getMovieID() == movieID) {
				mov = movieList.get(i);
				break;
			}
		}
		return mov;
	}
	
	//finds and returns a specific movie based on movieID
	/**
	 * Finds and returns a specific movie based on movieID from an arrayList of movies.
	 * Returns null if not found.
	 * @param movieList the movie list
	 * @param movieID the movie ID
	 * @return the movie
	 */
	//return null if not found
	public Movie findMovie(ArrayList<Movie> movieList, int movieID) {
		if(movieList ==null) return null; 
		Movie mov=null;
		
		for(int i=0; i < movieList.size(); i++) {
			if(movieList.get(i).getMovieID() == movieID) {
				mov = movieList.get(i);
				break;
			}
		}
		return mov;
	}
	
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
	public ArrayList<Movie> findAllMovieOfTitle(ArrayList<Movie> movieList, String movieTitle) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		for(int i=0; i < movieList.size();i++) {
			Movie mov = movieList.get(i);
			if(mov.getMovieTitle().equals(movieTitle)) {
				movies.add(mov);
			}
		}
		return movies;
	}
	
	
	//reads movieList from file
	//update movie attributes
	/**Reads movieList from file
	 * Check if MovieID to be updated/replace exists or not
	 * If it doesn't, return false
	 * Else, check if the new movieID to place the old one exists or not
	 * If yes, then there is a duplicate, and return false
	 * else, update the movieID and return true.
	 *
	 * Update movie ID.
	 *
	 * @param oldMovieID the old movie ID
	 * @param newMovieID the new movie ID
	 * @return true, if successful
	 */
	//saved updated movieList
	public boolean updateMovieID(int oldMovieID, int newMovieID) {
		ArrayList<Movie> movieList = getAllMovie();
		
		Movie movie = findMovie(movieList,oldMovieID);
		if(movie==null) {
			System.out.println("The MovieID to be updated doesn't exist!");
			return false;
		}
		Movie movie2 = findMovie(movieList,newMovieID);
		if(movie2!=null) {
			System.out.println("The new input ID already exists!");
			return false;
		}
		movie.setMovieID(newMovieID);
		
		movieList.sort(new Comparator<Movie>() {
			public int compare(Movie m1, Movie m2) {
				return m1.getMovieID() - m2.getMovieID();
			}
		});
		
		writeToFile(movieList);
		return true;
	}
	
	/**
	 * Update movie title. Iterate through the list to look for the movie title to be updated
	 * If cannot be found, return false
	 * If found, update all the movie titles with this name to the new movie title,
	 * and return true
	 *
	 * @param oldMovieTitle the old movie title
	 * @param newMovieTitle the new movie title
	 * @return true, if successful
	 */
	//updates all movies with oldMovieTitle to newMovieTitle
	public boolean updateMovieTitle(String oldMovieTitle, String newMovieTitle) {
		ArrayList<Movie> movieList = getAllMovie();
		boolean modified = false;
		for(Movie m : movieList) {
			if(m.getMovieTitle().equals(oldMovieTitle))
			{
				m.setMovieTitle(newMovieTitle);
				modified = true;
			}
		}
		if(!modified) {
			System.out.println("Movie title doesn't exist");
		}
		else
		{
			System.out.println("Successfully updated title.");
		}
		writeToFile(movieList);
		return modified;
	}
	
	/**
	 * Update movie type. Use movieID to find the movie and update its movie type.
	 * Iterate through list and check if movieID matches.
	 * If successful, update and return true, else return false
	 * @param movieID the movie ID
	 * @param newMovieType the new movie type
	 * @return true, if successful
	 */
	//update movieType of a specific movie
	public boolean updateMovieType(int movieID, TypeOfMovie newMovieType) {
		ArrayList<Movie> movieList = getAllMovie();
		
		Movie movie = findMovie(movieList, movieID);
		if(movie==null) {
			System.out.println("Movie ID doesn't exist!");
			return false;
		}
		
		movie.setMovieType(newMovieType);
		
		writeToFile(movieList);
		System.out.println("Successfully updated movie type!");
		return true;
	}
	
	/**
	 * Update movie language. Iterate through list to find a matching movieID, then update the language
	 * and return true, else return false.
	 *
	 * @param movieID the movie ID
	 * @param newLanguage the new language
	 * @return true, if successful
	 */
	//updates a movie's dubbed language
	public boolean updateMovieLanguage(int movieID, String newLanguage) {
		ArrayList<Movie> movieList = getAllMovie();
		
		Movie movie = findMovie(movieList, movieID);
		if(movie==null) {
			System.out.println("Movie ID doesn't exist!");
			return false;
		}
		
		movie.setLanguage(newLanguage);
		
		writeToFile(movieList);
		return true;
	}
	
	/**
	 * Update movie duration. Iterate through list and find matching movetitle, update the 
	 * duration and return true.
	 *
	 * @param movieTitle the movie title
	 * @param newDuration the new duration
	 * @return true, if successful
	 */
	//updates the duration of all movies of the same title
	public boolean updateMovieDuration(String movieTitle, int newDuration) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for (Movie m : movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setDuration(newDuration);
		}
		
		writeToFile(movieList);
		return true;
	}
	
	/**
	 * Update movie showing status.  Iterate through list and find matching movie titles, update the 
	 * duration and return true. If cannot find matching title, return false, and no updates.
	 *
	 * @param movieTitle the movie title
	 * @param newShowingStatus the new showing status
	 * @return true, if successful
	 */
	//updates the showingStatus of all movies of the same title
	public boolean updateMovieShowingStatus(String movieTitle, ShowingStatus newShowingStatus) {
		ArrayList<Movie> movieList = getAllMovie();
		boolean modified = false;
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle))
			{
				m.setShowingStatus(newShowingStatus);
				modified = true;
			}
		}
		if(modified)
			System.out.println("Successfully updated showing status.");
		else
			System.out.println("Movie title doesn't exist.");
		
		writeToFile(movieList);
		return modified;
	}
	
	/**
	 * Update movie synopsis.
	 * Update movie synopsis. Use movieTitle to find the movie and update its movie synopsis.
	 * Iterate through list and check if movie title matches.Update if matches.
	 * If successful, update and return true, else return false
	 *
	 * @param movieTitle the movie title
	 * @param newSynopsis the new synopsis
	 * @return true, if successful
	 */
	//updates the Synopsis of all movies of the same title
	public boolean updateMovieSynopsis(String movieTitle, String newSynopsis) {
		ArrayList<Movie> movieList = getAllMovie();
		boolean modified = false;
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) {
				m.setSynopsis(newSynopsis);;
				modified = true;
			}
		}
		writeToFile(movieList);
		
		if(modified)
			System.out.println("Successfully updated synopsis.");
		else
			System.out.println("Movie title doesn't exist.");
		
		
		return modified;
	}

	/**
	 * Update movie director.
	 * Update movie director.  Iterate through list and find matching movie titles, update the 
	 * director and return true. If cannot find at least one
	 * matching title, return false, and no updates.
	 *
	 * @param movieTitle the movie title
	 * @param newDirector the new director
	 * @return true, if successful
	 */
	//updates the Director of all movies of the same title
	public boolean updateMovieDirector(String movieTitle, String newDirector) {
		ArrayList<Movie> movieList = getAllMovie();
		boolean modified = false;
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) {
				modified = true;
				m.setDirector(newDirector);
			}
		}
		writeToFile(movieList);
		
		if(modified)
			System.out.println("Successfully updated director.");
		else
			System.out.println("Movie title doesn't exist.");
		
		return modified;
	}
	
	/**
	 * Update movie cast.
	 *   Iterate through list and find matching movie titles, update the 
	 * cast and return true. If cannot find matching title, return false, and no updates.
	 *
	 * @param movieTitle the movie title
	 * @param newCast the new cast
	 * @return true, if successful
	 */
	//updates the cast of all movies of the same title
	public boolean updateMovieCast(String movieTitle, ArrayList<String> newCast) {
		ArrayList<Movie> movieList = getAllMovie();
		boolean modified = false;
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) {
				modified = true;
				m.setCast(newCast);
			}
		}
		writeToFile(movieList);
		
		if(modified)
			System.out.println("Successfully updated cast.");
		else
			System.out.println("Movie title doesn't exist.");
		
		return modified;
	}
	
	/**
	 * Update movie review.
	 * Iterate through list and find matching movie titles, update the 
	 * reviews and return true. If cannot find matching title, return false, and no updates.
	 *
	 * @param movieTitle the movie title
	 * @param newReview the new review
	 * @return true, if successful
	 */
	//updates the reviews of all movies of the same title
	public boolean updateMovieReview(String movieTitle, ArrayList<String> newReview) {
		ArrayList<Movie> movieList = getAllMovie();
		boolean modified = false;
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) {
				modified = true;
				m.setPastReviews(newReview);;
			}
		}
		writeToFile(movieList);
		if(modified)
			System.out.println("Successfully updated reviews.");
		else
			System.out.println("Movie title doesn't exist.");
		return modified;
	}
	
	/**
	 * Update movie rating.
	 * Iterate through list and find matching movie titles, update the 
	 * rating and return true. If cannot find matching title, return false, and no updates.
	 * @param movieTitle the movie title
	 * @param newRating the new rating
	 * @return true, if successful
	 */
	//updates the rating of all movies of the same title
	public boolean updateMovieRating(String movieTitle, ArrayList<Integer> newRating) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setPastRatings(newRating);
		}
		writeToFile(movieList);
		return true;
	}
	
	
	/**
	 * Update ticket sales.
	 * Iterate through list and find matching movie ID, update the 
	 * numOfTickets and return true. If cannot find matching ID, return false, and no updates.
	 * @param movieID the movie ID
	 * @param numOfTickets the num of tickets
	 */
	//updates all movies of same title's ticket sales when a transaction is made
	public void updateTicketSales(int movieID, int numOfTickets) {
		ArrayList<Movie> movieList = getAllMovie();
		
		String movieTitle = findMovie(movieID).getMovieTitle();
		
		for(Movie m : movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setTicketSales(m.getTicketSales()+numOfTickets);
		}
		
		writeToFile(movieList);
	}
	
	
	//reads movieList from file
	//delete a movie based on movieID 
	/**
	 * Delete movie.
	 * Iterate through list and find matching movie ID, delete the movie
	 * and return true. If cannot find matching ID, return false, and no updates.
	 * @param movieID the movie ID
	 * @return true, if successful
	 */
	//save updated movieList to file
	public boolean deleteMovie(int movieID) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(int i=0; i<movieList.size();i++) {
			if(movieList.get(i).getMovieID() == movieID) {
				movieList.remove(i);
				writeToFile(movieList);
				System.out.println("Successfully deleted movie.");
				return true;
			}
		}
		System.out.println("Movie ID doesn't exist.");
		return false;
	}
	
	
	/**
	 * Display all the movie info.
	 *
	 * @param movieID the movie ID
	 */
	//displays Info
	public void displayMovieInfo(int movieID) {
		Movie movie = findMovie(getAllMovie(), movieID); //may return null
		
		if(movie==null) return;
		
		movie.dislayInfo();
	}
	
	//list out all movies
	/**
	 * List all movies.
	 * checks showing status before printing
	 * @param regardlessOfShowingStatus if true, print all movie of Coming Soon, Preview and NowShowing
	 */
	//checks showing status before printing
	public void listAllMovies(boolean regardlessOfShowingStatus) {
		ArrayList<Movie> movieList = getAllMovie();
		
		if(!regardlessOfShowingStatus) {
			for(Movie movie: movieList) {
				if(movie.getShowingStatus() == ShowingStatus.PREVIEW || movie.getShowingStatus() == ShowingStatus.NOW_SHOWING)
				{
					System.out.printf("%-80s | %s",movie.toString(),movie.getMovieType().toString());
					System.out.println();
					
				}
			}
		}
		else {
			for(Movie movie: movieList) {
				if(movie.getShowingStatus() != ShowingStatus.END_OF_SHOWING)
				{
					System.out.printf("%-80s | %s",movie.toString(),movie.getMovieType().toString());
					System.out.println();
					
				}
			}
		}
	}
	
	
	
}
