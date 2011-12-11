package course;

import java.util.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import course.Course;


/**
 * Singleton klasse die de vakken beheert. Het is een singletonklasse omdat men maar ��n object wil dat de vakken beheert
 * @author
 * @version
 *
 */
public class CourseManager {

	private static CourseManager instance = null;
	
		
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
			
			Course anal = new Course(26,6,prof1.getName(),"Analyse, deel 3",5);
			Course mech = new Course(26,8,prof2.getName(),"Mechanica, deel 2",5);
			Course org = new Course(26,8,prof3.getName(),"Organische scheikunde",5);
			Course kan = new Course(26,6,prof4.getName(),"Kansrekenen en statistiek",5);
			Course iov = new Course(26,6,prof5.getName(),"Informatieoverdracht en -verwerking",5);
			Course num = new Course(26,10,prof6.getName(),"Numerieke wiskunde",5);
			Course eco = new Course(24,12,prof7.getName(),"Economie",5);
			Course pno = new Course(8,2000,prof8.getName(),"Probleemoplossen en -ontwerpen, deel 3",5);
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Entity analy = new Entity("Course","Analyse, deel 3");
			analy.setProperty("totalLecture", anal.getTotalLecture());
			analy.setProperty("totalPractice", anal.getTotalPractice());
			analy.setProperty("prof", anal.getProf());
			analy.setProperty("name", anal.toString());
			analy.setProperty("studypoints", anal.getStudyPoints());
			Entity mecha = new Entity("Course","Mechanica, deel 2");
			mecha.setProperty("totalLecture", mech.getTotalLecture());
			mecha.setProperty("totalPractice", mech.getTotalPractice());
			mecha.setProperty("prof", mech.getProf());
			mecha.setProperty("name", mech.toString());
			mecha.setProperty("studypoints", mech.getStudyPoints());
			Entity orga = new Entity("Course","Organische scheikunde");
			orga.setProperty("totalLecture", org.getTotalLecture());
			orga.setProperty("totalPractice", org.getTotalPractice());
			orga.setProperty("prof", org.getProf());
			orga.setProperty("name", org.toString());
			orga.setProperty("studypoints", org.getStudyPoints());
			Entity kans = new Entity("Course","Kansrekenen en statistiek");
			kans.setProperty("totalLecture", kan.getTotalLecture());
			kans.setProperty("totalPractice", kan.getTotalPractice());
			kans.setProperty("prof", kan.getProf());
			kans.setProperty("name", kan.toString());
			kans.setProperty("studypoints", kan.getStudyPoints());
			Entity ioev = new Entity("Course","Informatieoverdracht en -verwerking");
			ioev.setProperty("totalLecture", iov.getTotalLecture());
			ioev.setProperty("totalPractice", iov.getTotalPractice());
			ioev.setProperty("prof", iov.getProf());
			ioev.setProperty("name", iov.toString());
			ioev.setProperty("studypoints", iov.getStudyPoints());
			Entity nume = new Entity("Course","Numerieke wiskunde");
			nume.setProperty("totalLecture", num.getTotalLecture());
			nume.setProperty("totalPractice", num.getTotalPractice());
			nume.setProperty("prof", num.getProf());
			nume.setProperty("name", num.toString());
			nume.setProperty("studypoints", num.getStudyPoints());
			Entity econ = new Entity("Course","Economie");
			econ.setProperty("totalLecture", eco.getTotalLecture());
			econ.setProperty("totalPractice", eco.getTotalPractice());
			econ.setProperty("prof", eco.getProf());
			econ.setProperty("name", eco.toString());
			econ.setProperty("studypoints", eco.getStudyPoints());
			Entity peno = new Entity("Course","Probleemoplossen en -ontwerpen, deel 3");
			peno.setProperty("totalLecture", pno.getTotalLecture());
			peno.setProperty("totalPractice", pno.getTotalPractice());
			peno.setProperty("prof", pno.getProf());
			peno.setProperty("name", pno.toString());
			peno.setProperty("studypoints", pno.getStudyPoints());
			List<Entity> vakken = Arrays.asList(analy, mecha, orga, kans, ioev, nume, econ, peno);
			datastore.put(vakken);	
			
			Entity Prof1 = new Entity("Prof","Dierckx",analy.getKey());
			Entity Prof2 = new Entity("Prof","Vander Sloten",mecha.getKey());			
			Entity Prof3 = new Entity("Prof","Smet",orga.getKey());			
			Entity Prof4 = new Entity("Prof","Van Dyck",kans.getKey());			
			Entity Prof5 = new Entity("Prof","Nauwelaers",ioev.getKey());			
			Entity Prof6 = new Entity("Prof","Meerbergen",nume.getKey());		
			Entity Prof7 = new Entity("Prof","D'haene",econ.getKey());			
			Entity Prof8 = new Entity("Prof","Duval",peno.getKey());
			List<Entity> profs = Arrays.asList(Prof1, Prof2, Prof3, Prof4, Prof5, Prof6, Prof7, Prof8);
			datastore.put(profs);
		}		
		
		/**
		 * geeft een lijst met alle vakken weer
		 * @return courses (type: ArrayList)
		 */
		public ArrayList<Course> getAllCourses(){
			ArrayList<Course> courses = new ArrayList<Course>();
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("Course");
			PreparedQuery pq = datastore.prepare(q);
			for (Entity result : pq.asIterable()) {
				Course course = new Course(Integer.parseInt(result.getProperty("totalLecture").toString()), Integer.parseInt(result.getProperty("totalPractice").toString()), (String) result.getProperty("prof"), (String) result.getProperty("name"), Integer.parseInt(result.getProperty("studypoints").toString())); 
				courses.add(course);
			}
			return courses;
		}
		
			
		
		public ArrayList<String> getCourseNames(ArrayList<Course> courses){
			ArrayList<String> courseNames = new ArrayList<String>();
			for (Course course: courses){
				courseNames.add(course.toString());
			}
			return courseNames;
		}
		
		public Course getCourse(Key k){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Transaction txn = datastore.beginTransaction();
			Course vak = null;
			try{
				Entity Course = datastore.get(k);
				vak = new Course(Integer.parseInt(Course.getProperty("totalLecture").toString()), Integer.parseInt(Course.getProperty("totalPractice").toString()), (String) Course.getProperty("prof"), (String) Course.getProperty("name"), Integer.parseInt(Course.getProperty("studypoints").toString()));			
			}
			catch (EntityNotFoundException e){
				if (txn.isActive()) {
			        txn.rollback();
			    }
			}
			return vak;
		}
		
		public Key getKey(String name){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key l = KeyFactory.createKey("Course",name);
			return l;			
		}
	
}
