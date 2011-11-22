package dashboardcwb3;

import java.util.Date;

public class Scolair extends Activity{
	private Course course;
	private String type;  //les, OZ of ZS
	
	public Scolair(Course course, String type){
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

}
