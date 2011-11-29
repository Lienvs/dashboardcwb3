package activity;
import java.util.*;

import course.Course;


public class Zelfstudie extends Scolair {
	
	
	private StudyLocation location;
	private String what;
	
	public Zelfstudie(Course course) {
		super(course,"ZS");
	}
	
	public void setLocation(StudyLocation x) {
		location = x;
	}
	
	public void setWhat(String x) {
		what = x;
	}
	
	public StudyLocation getLocation() {		
		return location;
	}
	
	public String getWhat() {
		return what;
	}
}
