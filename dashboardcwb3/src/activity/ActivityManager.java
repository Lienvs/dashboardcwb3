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
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity Activity = new Entity("Activity",activity.getStart().toString());		
		Activity.setProperty("startdatum",activity.getStart());
		Activity.setProperty("activitytype", activity.getActivityType());
		Activity.setProperty("type", activity.getType());
		if (activity.getActivityType().equals("scolair")){
			Activity.setProperty("course", CourseManager.getInstance().getKey(activity.getCourse().toString()));
		}
		datastore.put(Activity);
	}
	
	public void updateActivity(int rating, String comment, String place, String stype){
		Calendar cal = Calendar.getInstance();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();		
		ArrayList<Key> keys = new ArrayList<Key>();
		keys = UserManager.getInstance().getActivityKeys();		
		Transaction txn = datastore.beginTransaction();
		try {			
			for(Key l : keys){
				Entity Activity = datastore.get(l);
				if (Activity.hasProperty("stopdatum")){					
				}
				else {
					Activity.setProperty("stopdatum", cal.getTime());
					Activity.setProperty("rating", rating);
					Activity.setProperty("comment", comment);
					if (((String)Activity.getProperty("type")).equals("Zelfstudie")){
						Activity.setProperty("studielocatie", place);
						Activity.setProperty("studietype", stype);
					}
					datastore.put(Activity);
				}
			}		    
		    txn.commit();
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
		    }
		}
	
	public ArrayList<Activity> getAllActivities(){
		ArrayList<Activity> activities = new ArrayList<Activity>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Activity");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String type = (String) result.getProperty("type");
			String activityType = (String) result.getProperty("activitytype");
			if(type.equals("les")){
				Lecture les = new Lecture(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				activities.add(les);
			}
			else if(type.equals("Oefenzitting")){
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				activities.add(oefenzitting);
			}
			else if(type.equals("Zelfstudie")){
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
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
	
	public Activity getActivity(Key k){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Activity act = null;
		try{
			Entity Activity = datastore.get(k);
			String type = (String) Activity.getProperty("type");
			String activityType = (String) Activity.getProperty("activitytype");
			if(type.equals("les")){
				Lecture les = new Lecture(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				act = les;
			}
			else if(type.equals("Oefenzitting")){
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				act = oefenzitting;
			}
			else if(type.equals("Zelfstudie")){
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				act = zelfstudie;
			}
			else if(activityType.equals("fun")){
				ExtraFun ex = ExtraFun.getExtraFun((String) Activity.getProperty("type"));
				ExtraCurricularActivity activ = new ExtraCurricularActivity(ex);
				act = activ;				
			}						
		}
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		}
		return act;
	}
	
	
	
}
