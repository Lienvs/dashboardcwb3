package dashboardcwb3;

import java.util.*;

public class CourseManager {
	
	
	private static CourseManager instance = null;
	private Map<String,Course> courses;
	public static CourseManager getInstance() {
		if( instance == null ) {
		
			instance = new CourseManager();
		}
		return instance;
	}
	
	public CourseManager(){
		courses = new HashMap<String,Course>();
		makeCourses();
	}
		public void makeCourses(){
			Prof prof1 = new Prof("Dierckx","","","","","");
			Prof prof2 = new Prof("VDSlooten","","","","","");
			Course anal = new Course(5*30,15,prof1,"Analyse 3",5);
			Course mech = new Course(5*30,15,prof2,"mechanica 2",5);
			courses.put(anal.toString(), anal); courses.put(mech.toString(),mech);
		}
		
		public List<Course> getCourses(){
			
			List<Course> lijst = (List) courses.values();
			return lijst;
		}
		
		public Course getCourse (String name){
			
			return courses.get(name);
		}
		
	
}
