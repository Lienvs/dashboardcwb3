package user;

import java.util.ArrayList;
import java.util.Iterator;

import activity.Activity;
import activity.ActivityManager;

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
		Entity User = new Entity("User",user.getUserName());
		User.setProperty("Gebruikersnaam", user.getUserName());
		User.setProperty("Paswoord", user.getPassword());
		User.setProperty("Voornaam", user.getFirstName());
		User.setProperty("Achternaam", user.getLastName());
		User.setProperty("Geslacht", user.getGender());
		User.setProperty("Rnummer", user.getRNumber());
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
		catch(IllegalArgumentException f){
			exist = false;
		}
		return exist;			
	}
	
	public ArrayList<String> getRnumbers(){
		ArrayList<String> Rnumbers = new ArrayList<String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String RNumber = (String) result.getProperty("Rnummer");
			Rnumbers.add(RNumber);			
		}
		return Rnumbers;
	}
	
	public ArrayList<String> getUserNames(){
		ArrayList<String> userNames = new ArrayList<String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String userName = (String) result.getProperty("Gebruikersnaam");
			userNames.add(userName);
		}
		return userNames;
	}
	
	public String getUsersString(){
		ArrayList<String> userNames = getUserNames();
		userNames.remove(getCurrentUserName());
		Iterator<String> it = userNames.iterator();
		String result = "[";
		while(it.hasNext()){
			String name = it.next();
			result = result +"'"+ name+"'";
		if(it.hasNext()){
			result = result +",";}
		
		}
		result = result+"]";
		return result;
	}
	
	public String getPassword(String userName){
		String password = "";
		try{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key k = KeyFactory.createKey(User.class.getSimpleName(), userName);
			Entity user = datastore.get(k);
			password = (String) user.getProperty("Paswoord");			
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Course> getCourses(){
		ArrayList<Course> courses = new ArrayList<Course>();
		Object object = new Object();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			Key k = KeyFactory.createKey("User", currentUserName);
			Entity User = datastore.get(k);
			object = User.getProperty("courses");			
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}	
		ArrayList <Key> keys = new ArrayList<Key>();
		keys = (ArrayList<Key>) object;
		try{
		for (Key l : keys){
			courses.add(CourseManager.getInstance().getCourse(l));
		}
		}
		catch(NullPointerException f){			
		}
		return courses;
	}	
	
	public void updateCourses (ArrayList<Course> courses){
		ArrayList<String> courseNames = CourseManager.getInstance().getCourseNames(courses);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			Key k = KeyFactory.createKey("User", currentUserName);
			Entity User = datastore.get(k);
			ArrayList<Key> keys = new ArrayList<Key>();
			for (String naam : courseNames){
				Key l = KeyFactory.createKey("Course", naam);
				keys.add(l);
			}
			User.setProperty("courses",keys);			
			datastore.put(User);
			txn.commit();
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Activity> getActivities(){
		ArrayList<Activity> activities = new ArrayList<Activity>();
		Object object = new Object();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			Key k = KeyFactory.createKey("User", currentUserName);
			Entity User = datastore.get(k);
			object = User.getProperty("activities");			
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}	
		ArrayList <Key> keys = new ArrayList<Key>();
		keys = (ArrayList<Key>) object;
		try{
		for (Key l : keys){
			activities.add(ActivityManager.getInstance().getActivity(l));
		}
		}
		catch(NullPointerException f){			
		}
		return activities;
	}	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Key> getActivityKeys(){
		ArrayList<Key> keys = new ArrayList<Key>();
		Object object = new Object();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			Key k = KeyFactory.createKey("User", currentUserName);
			Entity User = datastore.get(k);
			object = User.getProperty("activities");			
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}	
		keys = (ArrayList<Key>) object;		
		return keys;
	}	
	
	public void updateActivities (Activity activity){		
		try{
			ArrayList<Key> keys = new ArrayList<Key>();
			keys = getActivityKeys();
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Transaction txn = datastore.beginTransaction();
			try{
				Key k = KeyFactory.createKey("User", currentUserName);
				Entity User = datastore.get(k);
				Key l = KeyFactory.createKey("Activity",activity.getStart().toString());
				keys.add(l);
				User.setProperty("activities",keys);			
				datastore.put(User);
				txn.commit();
			}
			catch (EntityNotFoundException e){
				if (txn.isActive()) {
					txn.rollback();
				}
			}	
		}
		catch(NullPointerException f){
			ArrayList<Key> keyss = new ArrayList<Key>();
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Transaction txn = datastore.beginTransaction();
			try{
				Key k = KeyFactory.createKey("User", currentUserName);
				Entity User = datastore.get(k);
				Key l = KeyFactory.createKey("Activity",activity.getStart().toString());
				keyss.add(l);
				User.setProperty("activities",keyss);			
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getGoals(){
		ArrayList<Integer> goals = new ArrayList<Integer>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			Key k = KeyFactory.createKey("User", currentUserName);
			Entity User = datastore.get(k);
			goals = (ArrayList<Integer>) User.getProperty("goals");
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		return goals;
	}
	
	public void setGoals(ArrayList<Integer> goals){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try{
			Key k = KeyFactory.createKey("User", currentUserName);
			Entity User = datastore.get(k);
			User.setProperty("goals", goals);
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
	}
	
	
	
	
	
	

	
		
		
	
	
}
