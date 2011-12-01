package activity;

import java.util.Date;

import course.Course;


public class Curricular extends Activity{
	private Course course;
	private String type;  //les, OZ of ZS
	
	public Curricular(Course course, String type){
		super("scolair");
		this.course=course;
		this.type=type;
	}
	
	public Course getCourse(){
		return course;
	}
	
	public String getType(){
		return type;
	}
	public String toString(){
		String result = "scolair" + " - " + getType() + " - " + course.toString();
		return result;
	}
}
