package goals;
import java.util.*;

import activity.Activity;
import activity.CurricularActivity;

import user.UserManager;
import user.User;
import course.Course;
public class GoalController {
	private Date startDate;
	private Date currentDate;
	private Date stopDate;
	private HashMap<Course,Integer> goals;
	
	public GoalController(){
		Calendar cal = Calendar.getInstance();
		currentDate = cal.getTime();
		while(cal.DAY_OF_WEEK!=Calendar.SUNDAY){
			cal.roll(Calendar.DAY_OF_WEEK,true);
		}
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,55);
		
		stopDate=cal.getTime();
		cal.roll(Calendar.DAY_OF_WEEK,false);
		while(cal.DAY_OF_WEEK!=Calendar.SUNDAY){
			cal.roll(Calendar.DAY_OF_WEEK,false);
		}
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,55);
		startDate= cal.getTime();
		goals = new HashMap<Course,Integer>();
	}
	
	public void setGoal(Course course,double time){//tijd in uren(kan ook naar minuten aangepast worden)
	double tijd = time*60;
	int t = (int) tijd;
		goals.put(course,t);
	}
	
	public HashMap<Course,Integer> compareGoals(){
		HashMap<Course,Integer> map = new HashMap<Course,Integer>();
		User currentUser = UserManager.getInstance().getCurrentUser();
		for(Course course:currentUser.getCourses()){
			int getal = goals.get(course);
		for(Activity act:currentUser.getActivities()){
			if(act.getStop().after(startDate)&&act.getStop().before(stopDate)){
			if(act.getActivityType().equals("scolair")){
				CurricularActivity scol =(CurricularActivity) act;
				if(scol.getCourse().equals(course)){
					getal = getal - scol.getDuration();
				}
			}
			}
		}
		map.put(course,getal);
		}
		return map;
	}
}
