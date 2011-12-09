package user;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import java.lang.NullPointerException;
import course.Course;
import course.CourseManager;

/**
 * Singleton klasse die de gebruikers beheert.
 * @author 
 * @version
 *
 */
public class UserManager {

	private static UserManager instance = null;
	
	
	private String currentUserName;
	
	
	public static UserManager getInstance() {
		if( instance == null ) {
		
			instance = new UserManager();
		}
		return instance;
	}
	/**
	 * Constructor
	 */
	public UserManager() {
		
		
		
	}
	
	
	public void addUser(User user){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String userName = user.getUserName();
		ArrayList<String> courses = new ArrayList<String>();
		Key k = KeyFactory.createKey(Course.class.getSimpleName(), "Analyse, deel3");
		Entity User = new Entity("User",userName);
		User.setProperty("gebruikersNaam", user.getUserName());
		User.setProperty("paswoord", user.getPassword());
		User.setProperty("voornaam", user.getFirstName());
		User.setProperty("achternaam", user.getLastName());
		User.setProperty("geslacht", user.getGender());
		User.setProperty("nummer", user.getRNumber());
		User.setProperty("Analyse, deel 3",k);
		User.setProperty("Mechanica, deel 2",k);
		User.setProperty("Organische scheikunde",k);
		User.setProperty("Kansrekenen en statistiek",k);
		User.setProperty("Informatieoverdracht en -verwerking",k);
		User.setProperty("Numerieke wiskunde",k);
		User.setProperty("Economie",k);
		User.setProperty("Probleemoplossen en -ontwerpen, deel 3",k);
		datastore.put(User);
				
	}
	
	public void deleteUser(String userName){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey(User.class.getSimpleName(), userName);
		datastore.delete(k);
	}	
	
	public boolean exist(String userName){
		boolean exist = false;
		try{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key k = KeyFactory.createKey(User.class.getSimpleName(), userName);
			Entity user = datastore.get(k);
			exist = true;
		}
		catch(EntityNotFoundException e){
			exist = false;
		}
		return exist;			
		}
	
	public ArrayList<String> getRNumbers(){
		ArrayList<String> RNumbers = new ArrayList<String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String RNumber = (String) result.getProperty("nummer");
			RNumbers.add(RNumber);			
		}
		return RNumbers;
	}
	
	public ArrayList<String> getUserNames(){
		ArrayList<String> userNames = new ArrayList<String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String userName = (String) result.getProperty("gebruikersNaam");
			userNames.add(userName);
		}
		return userNames;
	}	
	
	public String getPassword(String userName){
		String password = "";
		try{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key k = KeyFactory.createKey(User.class.getSimpleName(), userName);
			Entity user = datastore.get(k);
			password = user.getProperty("paswoord").toString();			
		}
		catch(EntityNotFoundException e){
			password = "";
		}
		
		return password;
		
	}	
	
	public void setCurrentUserName(String userName){
		currentUserName = userName;
	}
	
	public String getCurrentUserName(){
		return currentUserName;
	}
	
	public ArrayList<Course> getAllCourses(String userName){
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<String> alleVakken = new ArrayList<String>();
		ArrayList<Course> alleCourses = new ArrayList<Course>();
		ArrayList<String> vakken = new ArrayList<String>();
		alleCourses = CourseManager.getInstance().getAllCourses();
		for (Course course : alleCourses){
			alleVakken.add(course.toString());
		}
		try{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("User", userName);
		Entity User = datastore.get(k);
		for(String vak: alleVakken){
			Key l = (Key) User.getProperty(vak);
			try{ 
				if(!(l.equals(null))){
				Entity Vak = datastore.get(l);
				Course course = new Course(Integer.parseInt(Vak.getProperty("totalLecture").toString()),Integer.parseInt(Vak.getProperty("totalPractice").toString()),(String)Vak.getProperty("prof"),(String)Vak.getProperty("name"),Integer.parseInt(Vak.getProperty("studypoints").toString()));
				courses.add(course);
				}
			}
			catch(NullPointerException e){
		}
		}
		}
		catch(EntityNotFoundException e){
			
			
		}
		
		return courses;
	}
	
	public void addCourse(String userName, Course course){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try {
		    Key k = KeyFactory.createKey("User", userName);
		    Key l = KeyFactory.createKey("Course", course.toString());
		    Entity User = datastore.get(k);
		    Entity Course = datastore.get(l);
		    User.setProperty(course.toString(),Course.getKey());		    
		    datastore.put(User);
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
		}
	
	public void removeCourse(String userName, Course course){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try {
		    Key k = KeyFactory.createKey("User", userName);
		    Entity User = datastore.get(k);
		    User.setProperty(course.toString(),null);		    
		    datastore.put(User);
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
	}
	
	public void updateCourses (String userName, ArrayList<Course> courses){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		ArrayList<String> alleVakken = new ArrayList<String>();
		ArrayList<Course> alleCourses = new ArrayList<Course>();
		alleCourses = CourseManager.getInstance().getAllCourses();
		ArrayList<String> vakken = new ArrayList<String>();
		for (Course course : alleCourses){
			alleVakken.add(course.toString());
		}
		for (Course course : courses){
			vakken.add(course.toString());
		}
		try {
		    Key k = KeyFactory.createKey("User", userName);
		    Entity User = datastore.get(k);
		    for (String vak : alleVakken){
		    	User.setProperty(vak, null);
		    }
		    for (String vak : vakken){
		    	Key l = KeyFactory.createKey("Course", vak);
		    	//Entity Course = datastore.get(l);
		    	User.setProperty(vak, l);
		    }
		    
		    datastore.put(User);
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
	}
	
	public void updateUser(String userName){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try {
		    Key k = KeyFactory.createKey("User", userName);
		    Entity User = datastore.get(k);
		    User.setProperty("blablabla", "bla");
		    datastore.put(User);
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
	}
	
	
	

	
		
		
	
	
}


