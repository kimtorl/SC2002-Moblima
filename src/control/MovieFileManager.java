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

public class MovieFileManager implements MovieManager, Serializable{

	private static final long serialVersionUID = 20L;
	//Attribute
	private static final String FILENAME = "Database/movies.txt"; //file that this class manages
	
	
	//Empty constructor
	public MovieFileManager() {
	}
	
	
	
	//returns all the movies from the file
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
	
	//finds and returns a specific movie based on movieID
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
	//saved updated movieList
	public boolean updateMovieID(int oldMovieID, int newMovieID) {
		ArrayList<Movie> movieList = getAllMovie();
		
		Movie movie = findMovie(movieList,oldMovieID);
		if(movie==null) return false;
		
		movie.setMovieID(newMovieID);
		
		writeToFile(movieList);
		return true;
	}
	
	//updates all movies with oldMovieTitle to newMovieTitle
	public boolean updateMovieTitle(String oldMovieTitle, String newMovieTitle) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m : movieList) {
			if(m.getMovieTitle().equals(oldMovieTitle)) m.setMovieTitle(newMovieTitle);
		}
		
		writeToFile(movieList);
		return true;
	}
	
	//update movieType of a specific movie
	public boolean updateMovieType(int movieID, TypeOfMovie newMovieType) {
		ArrayList<Movie> movieList = getAllMovie();
		
		Movie movie = findMovie(movieList, movieID);
		if(movie==null) return false;
		
		movie.setMovieType(newMovieType);
		
		writeToFile(movieList);
		return true;
	}
	
	//updates a movie's dubbed language
	public boolean updateMovieLanguage(int movieID, String newLanguage) {
		ArrayList<Movie> movieList = getAllMovie();
		
		Movie movie = findMovie(movieList, movieID);
		if(movie==null) return false;
		
		movie.setLanguage(newLanguage);
		
		writeToFile(movieList);
		return true;
	}
	
	//updates the duration of all movies of the same title
	public boolean updateMovieDuration(String movieTitle, int newDuration) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for (Movie m : movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setDuration(newDuration);
		}
		
		writeToFile(movieList);
		return true;
	}
	
	//updates the showingStatus of all movies of the same title
	public boolean updateMovieShowingStatus(String movieTitle, ShowingStatus newShowingStatus) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setShowingStatus(newShowingStatus);
		}
		
		writeToFile(movieList);
		return true;
	}
	
	//updates the Synopsis of all movies of the same title
	public boolean updateMovieSynopsis(String movieTitle, String newSynopsis) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setSynopsis(newSynopsis);;
		}
		writeToFile(movieList);
		return true;
	}

	//updates the Director of all movies of the same title
	public boolean updateMovieDirector(String movieTitle, String newDirector) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setDirector(newDirector);
		}
		writeToFile(movieList);
		return true;
	}
	
	//updates the cast of all movies of the same title
	public boolean updateMovieCast(String movieTitle, ArrayList<String> newCast) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setCast(newCast);
		}
		writeToFile(movieList);
		return true;
	}
	
	//updates the reviews of all movies of the same title
	public boolean updateMovieReview(String movieTitle, ArrayList<String> newReview) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setPastReviews(newReview);;
		}
		writeToFile(movieList);
		return true;
	}
	
	//updates the rating of all movies of the same title
	public boolean updateMovieRating(String movieTitle, ArrayList<Integer> newRating) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(Movie m:movieList) {
			if(m.getMovieTitle().equals(movieTitle)) m.setPastRatings(newRating);
		}
		writeToFile(movieList);
		return true;
	}
	
	
	
	//reads movieList from file
	//delete a movie based on movieID 
	//save updated movieList to file
	public boolean deleteMovie(int movieID) {
		ArrayList<Movie> movieList = getAllMovie();
		
		for(int i=0; i<movieList.size();i++) {
			if(movieList.get(i).getMovieID() == movieID) {
				movieList.remove(i);
				writeToFile(movieList);
				return true;
			}
		}
		return false;
	}
	
	
	//displays Info
	public void displayMovieInfo(int movieID) {
		Movie movie = findMovie(getAllMovie(), movieID); //may return null
		
		if(movie==null) return;
		
		movie.dislayInfo();
	}
	
	//list out all movies
	//checks showing status before printing
	public void listAllMovies() {
		ArrayList<Movie> movieList = getAllMovie();
		for(Movie movie: movieList) {
			if(movie.getShowingStatus() == ShowingStatus.PREVIEW || movie.getShowingStatus() == ShowingStatus.NOW_SHOWING)
				System.out.println("MovieID: " + movie.getMovieID() + ". Title: " + movie);
		}
	}
	
	
}
