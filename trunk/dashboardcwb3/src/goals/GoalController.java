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
public class GoalController {
	private Date startDate;
	private Date currentDate;
	private Date stopDate;
	
	
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
	public int getGoal(Course course){
		int result = 0;
	
		int i =0; boolean ja = true;
		while(i<UserManager.getInstance().getCourses().size()&&ja){
			Course c = UserManager.getInstance().getCourses().get(i);
			if(c.toString().equals(course.toString())){
				ja = false;
			}
		i++;}
		ArrayList<Integer> list = UserManager.getInstance().getGoals();
		if(!ja){result = list.get(i-1);
		}
		return result;
	}
	
	public void setGoal(Course course,double time){//tijd in uren(kan ook naar minuten aangepast worden)
	double tijd = time*60;
	int t = (int) tijd;
	int i =0; boolean ja = true;
	while(i<UserManager.getInstance().getCourses().size()&&ja){
		Course c = UserManager.getInstance().getCourses().get(i);
		if(c.toString().equals(course.toString())){
			ja = false;
		}
	i++;}
	ArrayList<Integer> list = UserManager.getInstance().getGoals();
	
	if(!ja&&UserManager.getInstance().getCourses().size()!=0){list.set(i-1,t);
	}
	
	UserManager.getInstance().setGoals(list);
	}

	
			
		
	
}
