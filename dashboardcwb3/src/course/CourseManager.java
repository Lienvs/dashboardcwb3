package course;

import java.util.*;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.JDOFatalUserException;
import javax.jdo.PersistenceManager;

import user.User;

import database.PMF;

/**
 * Singleton klasse die de vakken beheert. Het is een singletonklasse omdat men maar ��n object wil dat de vakken beheert
 * @author
 * @version
 *
 */
public class CourseManager {

	private static CourseManager instance = null;
	
	public PersistenceManager pm;
	private ArrayList<Course> courses;
	
	public static CourseManager getInstance() {
		if( instance == null ) {
		
			instance = new CourseManager();
		}
		return instance;
	}
	
	/**
	 * Controller
	 */
	public CourseManager(){
		pm = PMF.get().getPersistenceManager();
		courses = new ArrayList<Course>();
		makeCourses();
	}
	
	/**
	 * Slaat de vakken op in een lijst met vakken
	 */
		public void makeCourses(){
			Professor prof1 = new Professor("Dierckx");
			Professor prof2 = new Professor("Vander Sloten");
			Professor prof3 = new Professor("Smet");
			Professor prof4 = new Professor("Van Dyck");
			Professor prof5 = new Professor("Nauwelaers");
			Professor prof6 = new Professor("Meerbergen");
			Professor prof7 = new Professor("D'haene");
			Professor prof8 = new Professor("Duval");
			Course anal = new Course(26,6,prof1,"Analyse, deel 3",5);
			Course mech = new Course(26,8,prof2,"Mechanica, deel 2",5);
			Course org = new Course(26,8,prof3,"Organische scheikunde",5);
			Course kan = new Course(26,6,prof4,"Kansrekenen en statistiek",5);
			Course iov = new Course(26,6,prof5,"Informatieoverdracht en -verwerking",5);
			Course num = new Course(26,10,prof6,"Numerieke wiskunde",5);
			Course eco = new Course(24,12,prof7,"Economie",5);
			Course pno = new Course(8,2000,prof8,"Probleemoplossen en -ontwerpen, deel 3",5);
			courses.add(anal);courses.add(mech);courses.add(org);courses.add(kan);courses.add(iov);
			courses.add(num);courses.add(eco);courses.add(pno);
			
			
		}
		
		/**
		 * geeft een lijst met alle vakken weer
		 * @return courses (type: ArrayList)
		 */
		public ArrayList<Course> getAllCourses(){
			return courses;
		}
		
		/**
		 * geeft het opgevraagde vak weer, indien de naam van het ingegeven vak door de gebruiker overeenkomt met de naam van een vak in de lijst
		 * @param name : de naam van het vak dat de gebruiker wil opvragen (type: String)
		 * @return coursje : het vak dat de gebruiker heeft opgevraagd (type: Course)
		 */
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
