package entity;
import java.util.ArrayList;

public class Movie {	
	
	enum TypeOfMovie {BLOCKBUSTER_2D, BLOCKBUSTER_3D, NONBLOCKBUSTER_2D, NONBLOCKBUSTER_3D};
	private int movieID;
	private String movieTitle;
	private	TypeOfMovie movieType;
	private String language;
	private int duration;
	private ShowingStatus showingStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> cast;
	private float overallReviewerRating;
	private ArrayList<String> pastReviews;
	private ArrayList<Integer> pastRatings;
	
	public Movie() {}
	public Movie(int movieID,String movieTitle,TypeOfMovie movieType, String language,int duration,
			ShowingStatus showingStatus,String synopsis,String director,ArrayList<String> cast,float overallReviewerRating,
			ArrayList<String> pastReviews,ArrayList<Integer> pastRatings) {
		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.movieType = movieType;
		this.language = language;
		this.duration = duration;
		this.showingStatus = showingStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.overallReviewerRating = overallReviewerRating;
		this.pastReviews = pastReviews;
		this.pastRatings = pastRatings;
	}
	
	public void dislayInfo()
	{
		System.out.println("Movie ID: "+movieID);
		System.out.println("Movie Title: "+movieTitle);
		System.out.println(movieType.name());
		System.out.println("Language: "+language);
		System.out.println("Duration: "+duration+" minutes");
		System.out.println("Showing status: "+showingStatus.name());
		System.out.println("Synopsis: "+synopsis);
		System.out.println("Directed by "+director);
		System.out.print("Casts: ");
		for(int i=0;i<cast.size();i++) {
			if(i == cast.size()-1)
				System.out.println(cast.get(i));	//if last member of cast, don't print comma
			else
				System.out.print(cast.get(i)+", ");
		}
		System.out.println("Overall Ratings: "+overallReviewerRating);
	}
	
	public String getMovieTitle() 
	{
		return movieTitle;
	}
	
	public void setMovieTitle(String movieTitle)
	{
		this.movieTitle = movieTitle;
	}
	
	public ShowingStatus getShowingStatus()
	{
		return showingStatus;
	}
	
	public void setShowingStatus(ShowingStatus a)
	{
		this.showingStatus = a;
	}
	
	public ArrayList<Integer> getPastRatings()
	{
		return pastRatings;
	}
	
	public void setPastRatings(ArrayList<Integer> a)
	{
		pastRatings = a;
	}
	
	public ArrayList<String> getPastReviews()
	{
		return pastReviews;
	}
	
	public void setPastReviews(ArrayList<String> a)
	{
		pastReviews = a;
	}
	
	

}
