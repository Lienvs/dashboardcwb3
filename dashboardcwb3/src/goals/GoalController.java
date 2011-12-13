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
	public int getGoal(String courseName){
		int result = 0;
		HashMap<String, Integer> map= new HashMap<String,Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>(9);
		if(UserManager.getInstance().getGoals()!=null){
			ArrayList<Integer> lijst = UserManager.getInstance().getGoals();
			int i =0;
			while(i<CourseManager.getInstance().getAllCourses().size()){
				int getal=0;
				String vak = CourseManager.getInstance().getAllCourses().get(i).toString();
				if(lijst.get(i)!=null){
					getal = lijst.get(i);
					
					
				}
				map.put(vak, getal);
				list.set(i, getal);
			i++;}
			result =map.get(courseName);
		}
		
		UserManager.getInstance().setGoals(list);
		
		return result;
	}
	
	public int getDifGoal(String courseName){
		int studieTime=0;
		int i = 0;
		for(Activity act : UserManager.getInstance().getActivities()){
			if(act.getCourse().toString().equals(courseName)){
				if(act.getStart().compareTo(startDate)<0 && act.getStart().compareTo(stopDate)>0){
				studieTime = studieTime+act.getDuration();
				}
			}
		}
		int goal = getGoal(courseName);
		return goal*60-studieTime;
	}
	

	
			
		
	
}
