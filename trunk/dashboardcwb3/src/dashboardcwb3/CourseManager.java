package dashboardcwb3;

import java.util.*;

public class CourseManager {
	
	
	private static CourseManager instance = null;
	
	private ArrayList<Course> courses;
	public static CourseManager getInstance() {
		if( instance == null ) {
		
			instance = new CourseManager();
		}
		return instance;
	}
	
	public CourseManager(){
		
		courses = new ArrayList<Course>();
		makeCourses();
	}
		public void makeCourses(){
			Prof prof1 = new Prof("Dierckx");
			Prof prof2 = new Prof("VDSlooten");
			Course anal = new Course(5*30,15,prof1,"Analyse 3",5);
			Course mech = new Course(5*30,15,prof2,"mechanica 2",5);
			
			courses.add(anal);courses.add(mech);
		}
		
	
		
		public ArrayList<Course> getAllCourses(){
			return courses;
		}
		
		public Course getCourse (String name){
			Course coursje = null;
			for(Course course: courses){
				if(course.toString().equals(name)){
					coursje= course;
				}
			}
			return coursje;
		}
		
		
	
}
