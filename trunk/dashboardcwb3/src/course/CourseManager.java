package course;

import java.util.*;

import user.Professor;


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
			Professor prof1 = new Professor("Dierckx");
			Professor prof2 = new Professor("Vander Sloten");
			Professor prof3 = new Professor("Smet");
			Professor prof4 = new Professor("Van Dyck");
			Professor prof5 = new Professor("Nauwelaers");
			Professor prof6 = new Professor("Meerbergen");
			Professor prof7 = new Professor("D'haene");
			Professor prof8 = new Professor("Duval");
			Course anal = new Course(5*30,15,prof1,"Analyse, deel 3",5);
			Course mech = new Course(5*30,15,prof2,"Mechanica, deel 2",5);
			Course org = new Course(5*30,15,prof3,"Organische scheikunde",5);
			Course kan = new Course(5*30,15,prof4,"Kansrekenen en statistiek",5);
			Course iov = new Course(5*30,15,prof5,"Informatieoverdracht en -verwerking",5);
			Course num = new Course(5*30,15,prof6,"Numerieke wiskunde",5);
			Course eco = new Course(5*30,15,prof7,"Economie",5);
			Course pno = new Course(5*30,15,prof8,"Probleemoplossen en -ontwerpen, deel 3",5);
			courses.add(anal);courses.add(mech);courses.add(org);courses.add(kan);courses.add(iov);
			courses.add(num);courses.add(eco);courses.add(pno);
			
			
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
