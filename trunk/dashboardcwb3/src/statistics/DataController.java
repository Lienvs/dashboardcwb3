package statistics;
import java.util.*;

import user.User;
import user.UserManager;

import course.Course;

import activity.Activity;
import activity.Curricular;
import activity.IndividualStudy;

public class DataController {
	private User currentUser;
		public  DataController(){
			currentUser = UserManager.getInstance().getCurrentUser();
}
		
private int getGemiddeldeStudie(){
	int result = 0;
	for(User usertje:UserManager.getInstance().getUsers()){
		ArrayList<Activity> actList = usertje.getActivities();
	
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Zelfstudie")){
				result = result + scol.getDuration(); 
			}
		}
	}
	
}
	result = result/UserManager.getInstance().getUsers().size();
	return result;
}

private int getGemiddeldeLes(){
	int result = 0;
	for(User usertje:UserManager.getInstance().getUsers()){
		ArrayList<Activity> actList = usertje.getActivities();
	
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Les")){
				result = result + scol.getDuration(); 
			}
		}
	}
	
}
	result = result/UserManager.getInstance().getUsers().size();
	return result;
}

private int getGemiddeldeOefenzitting(){
	int result = 0;
	for(User usertje:UserManager.getInstance().getUsers()){
		ArrayList<Activity> actList = usertje.getActivities();
	
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Oefenzitting")){
				result = result + scol.getDuration(); 
			}
		}
	}
	
}
	result = result/UserManager.getInstance().getUsers().size();
	return result;
}

private int getDuurZelfstudie(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Zelfstudie")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;
}

private int getDuurLes(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Les")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;

}
private int getDuurOefenzitting(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Oefenzitting")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;

}
private int getDuurExtraScolair(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("extra-scolair")){
			
				result = result + act.getDuration(); 
			}
		
	}
	return result;

}
private int getDuurThuisZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = currentUser.getActivities();
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Thuis")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
private int getDuurBibZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = currentUser.getActivities();
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Bibliotheek")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
private int getDuurKotZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = currentUser.getActivities();
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Kot")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}

private int getDuurZelfstudie(Course course){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Zelfstudie")){
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			}
		}
	}
	return result;

}
private int getDuurLes(Course course){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Les")){
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			}
		}
	}
	return result;

}
private int getDuurOefenzitting(Course course){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Curricular scol = (Curricular)act;
			if(scol.getType().equals("Oefenzitting")){
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			}
		}
	}
	return result;

}
private String ToHourMinute(int minuten){
	int minutes = minuten%60;
	int hours = minuten /60;
	return hours +" hours " + minutes + " minutes";
}
private String getMaximumStudie(){
	String result = "You didn't study at all";
	int maxDuur =0;
	ArrayList<Course> courseList= currentUser.getCourses();
	if(courseList.size()>0){
	Course maxCourse= courseList.get(0);
	for(Course course:courseList){
		if(getDuurZelfstudie(course) > maxDuur){
			maxDuur = getDuurZelfstudie(course);
			maxCourse = course;
		}
	}
	result = "You studied " + maxDuur + " for " + maxCourse.toString();
	}
	return result;
}}

