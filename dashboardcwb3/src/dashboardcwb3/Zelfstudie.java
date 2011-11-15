package dashboardcwb3;
import java.util.*;

public class Zelfstudie extends Scolair {
	
	private StudyLocation location;
	private String what; // theorie / oefeningen
	
	public Zelfstudie(Course course, Date start) {
		super(course, start,"ZS");
	}
	
	public void setLocation(StudyLocation x) {
		location = x;
	}
	
	public void setWhat(String x) {
		what = x;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getWhat() {
		return what;
	}
}
