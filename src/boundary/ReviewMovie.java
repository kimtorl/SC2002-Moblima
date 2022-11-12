package boundary;

import java.io.Serializable;

import control.MovieManager;

public class ReviewMovie implements Capability, Serializable {

	private static final long serialVersionUID =341L;

	private MovieManager movieMgr;
	
	public ReviewMovie(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}


	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}

	

	
	public String toString() {
		String str = "Review Movie";
		return str;
	}
}
