package dashboardcwb3;

import java.util.Calendar;
import java.util.Date;
// import java.util.*;

public abstract class Activity {
	
	private Date start;
	private Date stop;
	private String comment;
	private int rating;
	private boolean stopped;
	
	public Activity(Date start) {
		start = this.start;
		stop = this.start;
		stopped = false;
		comment = null;
		rating  = -1;		
	}
	
	public void postComment(String x) { 
		comment = x; // als er al een comment is, wordt die gewoon overschreven (geen spam)
	}
	
	public void setRating(int x) {
		rating = x; // overschrijven
	}
	
	public void setStop() {
		stopped = true;
		Calendar cal = Calendar.getInstance();
		stop = cal.getTime();
	}
	
	public boolean isStopped() {
		return stopped;
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getStop() {
		return stop;
	}
	
	public int getDuration() {
		long i = (stop.getTime() - start.getTime())/60000;
		int duur = (int) i;
		return duur;
	}
	
	
	
	public String getComment() {
		return comment;
	}
	
	public int getRating() {
		return rating;
	}

	
	
	
}
