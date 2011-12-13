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
		String leeg = "";
		Date empty = null;
		//Activity.setProperty("buddy", leeg);
		//Activity.setProperty("comment", leeg);
		//Activity.setProperty("rating", leeg);
		//Activity.setProperty("stopdatum", empty);
		//Activity.setProperty("studielocatie", leeg);
		//Activity.setProperty("studietype", leeg);
		if (activity.getActivityType().equals("scolair")){
			Activity.setProperty("course", CourseManager.getInstance().getKey(activity.getCourse().toString()));
		}
		datastore.put(Activity);
	}
	
	public void updateActivity(int rating, String comment, String place, String stype, String buddy){
		Calendar cal = Calendar.getInstance();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();		
		ArrayList<Key> keys = new ArrayList<Key>();
		keys = UserManager.getInstance().getActivityKeys();		
		for(Key l : keys){
		Transaction txn = datastore.beginTransaction();
		try {			
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
						Activity.setProperty("buddy", buddy);
					}
					datastore.put(Activity);
				}
					    
		    txn.commit();
		
		} 
		catch (EntityNotFoundException e){
			if (txn.isActive()) {
		        txn.rollback();
		    }
		    
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
				les.setStart((Date)result.getProperty("startdatum"));
				if (result.hasProperty("stopdatum")){
					les.setStop((Date)result.getProperty("stopdatum"));
				}
				if (result.hasProperty("rating")){
					les.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				if (result.hasProperty("comment")){
					les.postComment((String)result.getProperty("comment"));
				}
				activities.add(les);
			}
			else if(type.equals("Oefenzitting")){
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				oefenzitting.setStart((Date)result.getProperty("startdatum"));
				if (result.hasProperty("stopdatum")){
					oefenzitting.setStop((Date)result.getProperty("stopdatum"));
				}
				if (result.hasProperty("stopdatum")){
					oefenzitting.setStop((Date)result.getProperty("stopdatum"));
				}
				if (result.hasProperty("rating")){
					oefenzitting.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				if (result.hasProperty("comment")){
					oefenzitting.postComment((String)result.getProperty("comment"));
				}
				activities.add(oefenzitting);
			}
			else if(type.equals("Zelfstudie")){
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				zelfstudie.setStart((Date)result.getProperty("startdatum"));
				if (result.hasProperty("stopdatum")){
					zelfstudie.setStop((Date)result.getProperty("stopdatum"));
				}
				if (result.hasProperty("stopdatum")){
					zelfstudie.setStop((Date)result.getProperty("stopdatum"));
				}
				if (result.hasProperty("rating")){
					zelfstudie.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				if (result.hasProperty("comment")){
					zelfstudie.postComment((String)result.getProperty("comment"));
				}
				if (result.hasProperty("buddy")){
					zelfstudie.setStudyBuddy((String)result.getProperty("buddy"));
				}	
				if (result.hasProperty("studielocatie")){
					zelfstudie.setLocation(StudyLocation.getStudyLocation((String)result.getProperty("studielocatie")));
				}
				if (result.hasProperty("studietype")){
					zelfstudie.setType(StudyType.getStudyType((String)result.getProperty("studietype")));
				}
				activities.add(zelfstudie);
			}
			else if(activityType.equals("fun")){
				ExtraFun ex = ExtraFun.getExtraFun((String) result.getProperty("type"));
				ExtraCurricularActivity act = new ExtraCurricularActivity(ex);
				act.setStart((Date)result.getProperty("startdatum"));
				if (result.hasProperty("stopdatum")){
					act.setStop((Date)result.getProperty("stopdatum"));
				}
				if (result.hasProperty("rating")){
					act.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				if (result.hasProperty("comment")){
					act.postComment((String)result.getProperty("comment"));
				}
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
				les.setStart((Date)Activity.getProperty("startdatum"));
				if (Activity.hasProperty("stopdatum")){
					les.setStop((Date)Activity.getProperty("stopdatum"));
				}
				if (Activity.hasProperty("rating")){
					les.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				if (Activity.hasProperty("comment")){
					les.postComment((String)Activity.getProperty("comment"));
				}
				act=les;
			}
			else if(type.equals("Oefenzitting")){
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				oefenzitting.setStart((Date)Activity.getProperty("startdatum"));
				if (Activity.hasProperty("stopdatum")){
					oefenzitting.setStop((Date)Activity.getProperty("stopdatum"));
				}
				if (Activity.hasProperty("stopdatum")){
					oefenzitting.setStop((Date)Activity.getProperty("stopdatum"));
				}
				if (Activity.hasProperty("rating")){
					oefenzitting.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				if (Activity.hasProperty("comment")){
					oefenzitting.postComment((String)Activity.getProperty("comment"));
				}
				act=oefenzitting;
			}
			else if(type.equals("Zelfstudie")){
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				zelfstudie.setStart((Date)Activity.getProperty("startdatum"));
				if (Activity.hasProperty("stopdatum")){
					zelfstudie.setStop((Date)Activity.getProperty("stopdatum"));
				}
				if (Activity.hasProperty("stopdatum")){
					zelfstudie.setStop((Date)Activity.getProperty("stopdatum"));
				}
				if (Activity.hasProperty("rating")){
					zelfstudie.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				if (Activity.hasProperty("comment")){
					zelfstudie.postComment((String)Activity.getProperty("comment"));
				}
				if (Activity.hasProperty("buddy")){
					zelfstudie.setStudyBuddy((String)Activity.getProperty("buddy"));
				}	
				if (Activity.hasProperty("studielocatie")){
					zelfstudie.setLocation(StudyLocation.getStudyLocation((String)Activity.getProperty("studielocatie")));
				}
				if (Activity.hasProperty("studietype")){
					zelfstudie.setType(StudyType.getStudyType((String)Activity.getProperty("studietype")));
				}
				act=zelfstudie;
			}
			else if(activityType.equals("fun")){
				ExtraFun ex = ExtraFun.getExtraFun((String) Activity.getProperty("type"));
				ExtraCurricularActivity actt = new ExtraCurricularActivity(ex);
				actt.setStart((Date)Activity.getProperty("startdatum"));
				if (Activity.hasProperty("stopdatum")){
					actt.setStop((Date)Activity.getProperty("stopdatum"));
				}
				if (Activity.hasProperty("rating")){
					actt.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				if (Activity.hasProperty("comment")){
					actt.postComment((String)Activity.getProperty("comment"));
				}
				act=actt;
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
