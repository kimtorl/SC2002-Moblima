package boundary;

import java.io.Serializable;

import control.MovieManager;

public class Top5MovieByRating implements Capability, Serializable {

	private static final long serialVersionUID =33L;
	
	private MovieManager movieMgr;
	
	public Top5MovieByRating(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}

	
	public String toString() {
		String str = "List Top 5 Movies by Rating";
		return str;
	}
}
