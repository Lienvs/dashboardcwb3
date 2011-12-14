package goals;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import user.UserManager;
import activity.Activity;
import activity.ActivityManager;
import activity.CurricularActivity;
import course.Course;
import course.CourseManager;
public class GoalController {
	private Date startDate;
	private Date currentDate;
	private Date stopDate;
	private HashMap<String, Integer> map;
	
	
	public GoalController(){
		Calendar cal = Calendar.getInstance();
		currentDate = cal.getTime();
		while(cal.DAY_OF_WEEK!=7){
			cal.roll(Calendar.DAY_OF_WEEK,1);
		}
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,55);
		
		stopDate=cal.getTime();
		cal.roll(Calendar.DAY_OF_WEEK,false);
		while(cal.DAY_OF_WEEK!=7){
			cal.roll(Calendar.DAY_OF_WEEK,false);
		}
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,55);
		startDate= cal.getTime();
		
		
	}
	public long getGoal(String courseName){
		
		if(UserManager.getInstance().getGoals().size()==0){
			return 0;
		}
		else{
			ArrayList<Course> courses = UserManager.getInstance().getCourses();
			ArrayList<String> str = CourseManager.getInstance().getCourseNames(courses);
			int i =str.indexOf(courseName);
			return UserManager.getInstance().getGoals().get(i);
			
		}
		
	}
	
	public int getDifGoal(String courseName){
		int studieTime=0;
		int i = 0;
		for(Activity act : UserManager.getInstance().getActivities()){
			if(act.getActivityType().equals("scolair")){
			if(act.getCourse().toString().equals(courseName)){
				if(act.getStart().compareTo(startDate)<0 && act.getStart().compareTo(stopDate)>0){
				studieTime = studieTime+act.getDuration();
				}
			}
		}}
		int goal = (int)getGoal(courseName);
		return goal*60-studieTime;
	}
	public int getTotalWeek(String courseName){
		int studieTime = 0;
		for(Activity act : UserManager.getInstance().getActivities()){
			if(act.getActivityType().equals("scolair")){
			if(act.getCourse().toString().equals(courseName)){
				if(act.getStart().compareTo(startDate)<0 && act.getStart().compareTo(stopDate)>0){
				studieTime = studieTime+act.getDuration();
				}
			}
		}}
		return studieTime;
	}
	

	
			
		
	
}
