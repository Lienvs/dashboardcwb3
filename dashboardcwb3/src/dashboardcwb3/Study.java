package dashboardcwb3;
import java.util.*;

public class Study {

	private Date start;
	private Date stop;
	private String comment;
	private int rating;
	private String type;
	private boolean stopped;
	
	public Study(Date start, String type) {
		start = this.start;
		stop = this.start;
		stopped = false;
		type = this.type;
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
	
	public String getType() {
		return type;
	}
	
	public String getComment() {
		return comment;
	}
	
	public int getRating() {
		return rating;
	}
}
