package boundary;

import java.io.Serializable;

import control.MovieManager;

public class ViewMovieDetail implements Capability, Serializable {

	private static final long serialVersionUID = 36L;
	
	
	private MovieManager movieMgr;
	
	public ViewMovieDetail(MovieManager movieMgr) {
		this.movieMgr = movieMgr;
	}

	@Override
	public void performCapability() {
		// TODO Auto-generated method stub

	}
	

	
	public String toString() {
		String str = "View Movie Details";
		return str;
	}

}
