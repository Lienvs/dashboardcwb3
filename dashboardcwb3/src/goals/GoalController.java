package goals;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;

import user.UserManager;
import activity.Activity;
import activity.ActivityManager;
import activity.CurricularActivity;
import course.Course;
public class GoalController {
	private Date startDate;
	private Date currentDate;
	private Date stopDate;
	
	
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
		
		
	}
	public int getGoal(Course course){
		String currentUserName = UserManager.getInstance().getCurrentUserName();
	//	int result = UserManager.getInstance().getGoal(currentUserName,startDate).get(course);
		return 0;
	}
	public void setGoal(Course course,double time){//tijd in uren(kan ook naar minuten aangepast worden)
	double tijd = time*60;
	int t = (int) tijd;
	
	String currentUserName = UserManager.getInstance().getCurrentUserName();
	//UserManager.getInstance().setGoal(currentUserName,startDate,course,t);
	}

	public HashMap<Course,Integer> compareGoals(){
		HashMap<Course,Integer> map = new HashMap<Course,Integer>();
		String currentUserName = UserManager.getInstance().getCurrentUserName();
		for(Course course:UserManager.getInstance().getCourses()){
//			int getal = UserManager.getInstance().getGoal(currentUserName,startDate).get(course);
		for(Activity act:UserManager.getInstance().getActivities()){
			if(act.getStop().after(startDate)&&act.getStop().before(stopDate)){
			if(act.getActivityType().equals("scolair")){
				CurricularActivity scol =(CurricularActivity) act;
				if(scol.getCourse().equals(course)){
	//				getal = getal - scol.getDuration();
				}
			}
			}
		}
	//	map.put(course,getal);
		}
		return map;
	}
}
