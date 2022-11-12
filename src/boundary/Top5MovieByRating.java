package boundary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import control.MovieManager;
import entity.Movie;

public class Top5MovieByRating implements Capability, Serializable {

	private static final long serialVersionUID =33L;
	
	private MovieManager movieMgr;
	
	public Top5MovieByRating(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

	@Override
	public void performCapability() {
		ArrayList<Movie> movieList = movieMgr.getAllMovie();
		movieList.sort(new Comparator<Movie>() {
			public int compare(Movie m1, Movie m2) {
				double diff = m1.getOverallReviewerRating() - m2.getOverallReviewerRating();
				if(diff<0) return 1;
				else if(diff> 0) return -1;
				else return 0;
			}
		});
		
		ArrayList<Movie> topMoviesNoDups = new ArrayList<Movie>();
		for(int i = 0; i < movieList.size(); i++)// create a shallow copy with no dups
		{
			if(i==0) {
				topMoviesNoDups.add(movieList.get(i));
			}
			else {
				if(movieList.get(i).getMovieTitle().equals( movieList.get(i-1).getMovieTitle() ))
					continue;
				else {
					topMoviesNoDups.add(movieList.get(i));
				}
					
			}
		}
		
		for(int i = 0; i < topMoviesNoDups.size()||i<5; i++)//print top 5
		{
			System.out.print(topMoviesNoDups.get(i).getMovieTitle()+
					" | Rating: ");
			System.out.printf("%.2f", topMoviesNoDups.get(i).getOverallReviewerRating());
			System.out.println("\n");

		}
		
	};

	
	public String toString() {
		String str = "List Top 5 Movies by Rating";
		return str;
	}
}
