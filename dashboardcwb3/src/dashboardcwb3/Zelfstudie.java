package dashboardcwb3;
import java.util.*;

public class Zelfstudie extends Scolair {
	
	private String location;
	private String what; // theorie / oefeningen
	
	public Zelfstudie(Course course, Date start) {
		super(course, start,"ZS");
	}
	
	public void setLocation(String x) {
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
