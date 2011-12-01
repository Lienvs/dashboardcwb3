package activity;
import java.util.*;

import course.Course;


public class IndividualStudy extends Curricular {
	
	
	private StudyLocation location;
	private String what;
	
	public IndividualStudy(Course course) {
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
