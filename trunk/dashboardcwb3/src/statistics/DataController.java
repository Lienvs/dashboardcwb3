package statistics;
import java.util.*;

import user.User;
import user.UserManager;

import course.Course;
import activity.ActivityManager;
import activity.Activity;
import activity.CurricularActivity;
import activity.IndividualStudy;

public class DataController {
	private String currentUserName;
		public  DataController(){
			String currentUserName = UserManager.getInstance().getCurrentUserName();
}
	
public int getGemiddeldeStudie(){
	int result = 0;
	
		ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				result = result + scol.getDuration(); 
			}
		}
	}
	

	result = result/UserManager.getInstance().getUserNames().size();
	return result;
}
public int getGemiddeldeStudie(Course course){
	int result = 0;
	
	ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				if(scol.getCourse().toString().equals(course.toString())){
				result = result + scol.getDuration(); 
			}}
		}
	
	
	}	
	int users =0;
	for(String userName:UserManager.getInstance().getUserNames()){
		boolean vak = false;
		Iterator<Activity> it = UserManager.getInstance().getActivities(userName).iterator();
			while(it.hasNext()&& !vak){
				Activity act = it.next();
					if (act.getActivityType().equals("scolair")){
						CurricularActivity scol = (CurricularActivity) act;
							if(scol.getType().equals("Zelfstudie")){
								if(scol.getCourse().toString().equals(course.toString())){
									users++;
									vak=true;
								
							}
					}
				}
			}
			String a= "";
	}
	if(users==0){
		result = 0;
	}
	else{
		result = result/users;
	}
	return result;
}
public int getGemiddeldeLes(){
	int result = 0;
	ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Les")){
				result = result + scol.getDuration(); 
			}
		}
	}
	

	
	result= result/UserManager.getInstance().getUserNames().size();
	return result;
}
public int getGemiddeldeLes(Course course){
	int result = 0;
	
		ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Les")){
				if(scol.getCourse().toString().equals(course.toString())){
				result = result + scol.getDuration(); 
			}}
		}
	}
	

	int users =0;
	for(String userName:UserManager.getInstance().getUserNames()){
		boolean vak = false;
		Iterator<Activity> it = UserManager.getInstance().getActivities(userName).iterator();
		while(it.hasNext()&& !vak){
			Activity act = it.next();
			if (act.getActivityType().equals("scolair")){
				CurricularActivity scol = (CurricularActivity) act;
				if(scol.getType().equals("Les")){
					if(scol.getCourse().toString().equals(course.toString())){
						users++;
						vak=true;
					}
				}
			}
		}
		}
	
	if(users==0){
		result = 0;
	}
	else{result = result/users;}
	return result;
}

public int getGemiddeldeOefenzitting(){
	int result = 0;
	ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Oefenzitting")){
				result = result + scol.getDuration(); 
			}
		}
	
	
}
	result = result/UserManager.getInstance().getUserNames().size();
	return result;
}
public int getGemiddeldeOefenzitting(Course course){
	int result = 0;
	
		ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Oefenzitting")){
				if(scol.getCourse().toString().equals(course.toString())){
				result = result + scol.getDuration(); 
			}}
		
	}
	
}
	int users =0;
	for(String userName:UserManager.getInstance().getUserNames()){
		boolean vak = false;
		Iterator<Activity> it = UserManager.getInstance().getActivities(userName).iterator();
		while(it.hasNext()&& !vak){
			Activity act = it.next();
			if (act.getActivityType().equals("scolair")){
				CurricularActivity scol = (CurricularActivity) act;
				if(scol.getType().equals("Les")){
					if(scol.getCourse().toString().equals(course.toString())){
						users++;
						vak=true;
					}
				}
			}
		}
		}
	
	if(users==0){
		result = 0;
	}
	else{result = result/users;}
	return result;
}
public int getDuurZelfstudie(){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;
}

public int getDuurLes(){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Les")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;

}
public int getDuurOefenzitting(){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Oefenzitting")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;

}
public int getDuurExtraScolair(){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("extra-scolair")){
			
				result = result + act.getDuration(); 
			}
		
	}
	return result;

}
public int getDuurThuisZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Home")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
public int getDuurBibZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Library")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
public int getDuurKotZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Kot")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
public int getDuurOtherZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				IndividualStudy zelf= (IndividualStudy) scol;
				if(zelf.getLocation().toString().equals("Other")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
public int getTotalScolair(Course course){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			
		}
	}
	return result;

}
public int getTotalScolair(){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			result =result+act.getDuration();
		}
	}
	return result;
}
public int getTotalFun(){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("fun")){
			result =result+act.getDuration();
		}
	}
	return result;
}
public int getGemiddeldeScolair(Course course){
	int result = 0;
	result = getGemiddeldeStudie(course)
	+ getGemiddeldeLes(course) + 
	getGemiddeldeOefenzitting(course);
	return result;
}
public int getDuurZelfstudie(Course course){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Zelfstudie")){
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			}
		}
	}
	return result;

}
public int getDuurLes(Course course){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("les")){
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			}
		}
	}
	return result;

}
public int getDuurOefenzitting(Course course){
	ArrayList<Activity> actList = UserManager.getInstance().getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getActivityType().equals("scolair")){
			CurricularActivity scol = (CurricularActivity)act;
			if(scol.getType().equals("Oefenzitting")){
				if(scol.getCourse().toString().equals(course.toString())){
					result = result + scol.getDuration(); 
				}
			}
		}
	}
	return result;

}
public String ToHourMinute(int minuten){
	int minutes = minuten%60;
	int hours = minuten /60;
	return hours +" hours " + minutes + " minutes";
}
public String getMaximumStudie(){
	String result = "You didn't study at all";
	int maxDuur =0;
	ArrayList<Course> courseList= UserManager.getInstance().getCourses();
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
}

}
