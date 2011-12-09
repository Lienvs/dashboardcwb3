package activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import user.UserManager;

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
import course.CourseManager;
import activity.Activity;
import servlet.StartServlet;

public class ActivityManager {

	private static ActivityManager instance = null;	
	
	public static ActivityManager getInstance() {
		if( instance == null ) {
		
			instance = new ActivityManager();
		}
		return instance;
	}
	/**
	 * Constructor
	 */
	public ActivityManager() {		
		
	}
	
	
	public void addActivity(Activity activity){
		Calendar cal = Calendar.getInstance();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity Activity = new Entity("Activity",activity.getStart().getTime());
		Date startDatum = new Date();
		startDatum = activity.getStart();
		Activity.setProperty("startdatum",startDatum);
		String activitytype = activity.getActivityType();
		Activity.setProperty("activitytype", activitytype);
		String type = activity.getType();
		Activity.setProperty("type", type);
		datastore.put(Activity);	
		
		Transaction txn = datastore.beginTransaction();
		try {
		    Key k = KeyFactory.createKey("Activity", activity.getStart().getTime());
		    Key l = KeyFactory.createKey("User", UserManager.getInstance().getCurrentUserName());
		    Entity User = datastore.get(l);
		    User.setProperty(activity.getStart().toString(),k);		    
		    datastore.put(User);
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
		}
	
	
	public void setStop(Activity activity){
		Calendar cal = Calendar.getInstance();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try {
		    Key k = KeyFactory.createKey("Activity", activity.getStart().getTime());
		    Entity Activity = datastore.get(k);
		    Date stopDatum = new Date();
			stopDatum = cal.getTime(); 
			Activity.setProperty("stopdatum",stopDatum);		    
		    datastore.put(Activity);
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
		}
	
	public ArrayList<Activity> getActivities(){
		ArrayList<Activity> activities = new ArrayList<Activity>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Activity");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String type = (String) result.getProperty("type");
			String activityType = (String) result.getProperty("activitytype");
			if(type.equals("les")){
				Lecture les = new Lecture(CourseManager.getInstance().getCurrentCourse());
				activities.add(les);
			}
			else if(type.equals("Oefenzitting")){
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCurrentCourse());
				activities.add(oefenzitting);
			}
			else if(type.equals("Zelfstudie")){
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCurrentCourse());
				activities.add(zelfstudie);
			}
			else if(activityType.equals("fun")){
				ExtraFun ex = ExtraFun.getExtraFun((String) result.getProperty("type"));
				ExtraCurricularActivity act = new ExtraCurricularActivity(ex);
				activities.add(act);
			}			
		}
		return activities;
	}
	
	public ArrayList<Activity> getActivities(String userName){
		ArrayList<String> alleActiviteiten = new ArrayList<String>();
		ArrayList<Activity> activiteiten = new ArrayList<Activity>();
		for (Activity activity : getActivities()){
			alleActiviteiten.add(activity.getStart().toString());
		}
		try{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("User", UserManager.getInstance().getCurrentUserName());
		Entity User = datastore.get(k);
		for(String activiteit: alleActiviteiten){
			try{ 
				Key l = (Key) User.getProperty(activiteit);
				if((l.equals(null))){
				}
				else{
					Entity Activiteit = datastore.get(l);
					for(Activity activiteitje : getActivities()){
						if(activiteitje.getStart().equals((Date)Activiteit.getProperty("startdatum"))){
							activiteiten.add(activiteitje);
						}
					}
				}
			}
			catch(NullPointerException e){
		}
		}
		}
		catch(EntityNotFoundException e){
			
			
		}
		
		return activiteiten;
	}
	
	
	
}
