package statistics;
import java.util.*;

import activity.Activity;
import activity.ExtraCurricularActivity;

import course.Course;



import user.User;
import user.UserManager;
public class StatisticController {
	
private DataController data;

public StatisticController(){
	data = new DataController();
}
public String myCoursesCheese(){
	String result = "[";
	 User currentUser = UserManager.getInstance().getCurrentUser();
	 ArrayList<Course> courseList = currentUser.getCourses();
	 Iterator<Course> it = courseList.iterator();
	 while(it.hasNext()){
		 Course course = it.next();
		 result = result + "['" +  course.toString() + "'," + data.getTotalScolair(course) + "]";
		 if(it.hasNext()){
			 result = result + ",";
		 }
	 }
	 result = result+"]";
	 return result;
}

public String myPlacesCheese(){
	String result = "[";
	result = result +"['" +  "Library" + "'," + data.getDuurBibZelfstudie() + "],";
	result = result +"['" +  "Home" + "'," + data.getDuurThuisZelfstudie() + "],";
	result = result +"['" +  "Kot" + "'," + data.getDuurKotZelfstudie() + "]";
	result = result +"['" +  "Other" + "'," + data.getDuurOtherZelfstudie() + "]";
	result = result + "]";
	return result;
}
public String myTypeCheese(Course course){
	String result = "[";
	result = result +"['" +  "Zelfstudie" + "'," + data.getDuurZelfstudie(course) + "],";
	result = result +"['" +  "Les" + "'," + data.getDuurLes(course) + "],";
	result = result +"['" +  "Oefenzitting" + "'," + data.getDuurOefenzitting(course) + "]";
	result = result + "]";
	return result;
	
}
public String myTypeBar(Course course){//geeft 3 getallen in een string voor bar-stat: Les/oef/zs (samen met volgend methode in 1 stat!!)
	String result = "[";
	result= result+ data.getDuurLes(course)+","+data.getDuurOefenzitting(course)+","+data.getDuurZelfstudie(course)+"]";
	return result;
	
}
public String overallMeanTypeBar(Course course){//zelfde als hierboven, ma dan voor het gemiddelde
	String result = "[";
	result= result+ data.getGemiddeldeLes(course)+","+data.getGemiddeldeOefenzitting(course)+","+data.getGemiddeldeStudie(course)+"]";
	return result;

}
public String myTimeInTime2(Date startDate){//dit is voor een week per dag bekeken
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	stop.setTime(startDate);
	start.setTime(startDate);
	int i = 0;
	User currentUser = UserManager.getInstance().getCurrentUser();
	while(i<8){
		int getal=0;
		stop.roll(Calendar.DAY_OF_YEAR,true);
	for(Activity act:currentUser.getActivities()){
		if(act.getStart().after(start.getTime())&&act.getStart().before(stop.getTime())&&act.getActivityType().equals("scolair")){
			getal = getal+act.getDuration();
			}
		}
	result = result + getal;
	start.roll(Calendar.DAY_OF_YEAR,true);
	i++; if(i<8){result = result + ",";
	}
}
result= result+"]";
return result;
}


public String myTimeInTime(){//dit is voor 20 weken (in januari minder) week per week bekeken.
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	if(start.WEEK_OF_YEAR>20){
		start.set(Calendar.WEEK_OF_YEAR,stop.WEEK_OF_YEAR-20);
	}
	else{int i = start.WEEK_OF_YEAR-1;
	start.set(Calendar.WEEK_OF_YEAR,stop.WEEK_OF_YEAR-i);
	}
	Calendar mid = start;
	User currentUser = UserManager.getInstance().getCurrentUser();
	while(start.before(stop)){
		int getal=0;
		mid.roll(Calendar.DAY_OF_YEAR,true);
	for(Activity act:currentUser.getActivities()){
		if(act.getStart().after(start.getTime())&&act.getStart().before(mid.getTime())&&act.getActivityType().equals("scolair")){
			getal = getal+act.getDuration();
			}
		}
	result = result + getal;
	start.roll(Calendar.DAY_OF_YEAR,true);
	if(start.before(stop)){result = result + ",";
	}
	
}result= result+"]";
return result;
}


private String myInTime(String type,String type2){
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	if(start.WEEK_OF_YEAR>20){
		start.set(Calendar.WEEK_OF_YEAR,stop.WEEK_OF_YEAR-20);
	}
	else{int i = start.WEEK_OF_YEAR-1;
	start.set(Calendar.WEEK_OF_YEAR,stop.WEEK_OF_YEAR-i);
	}
	Calendar mid = start;
	User currentUser = UserManager.getInstance().getCurrentUser();
	while(start.before(stop)){
		int getal=0;
		mid.roll(Calendar.DAY_OF_YEAR,true);
	for(Activity act:currentUser.getActivities()){
		if(act.getStart().after(start.getTime())&&act.getStart().before(mid.getTime())){
			
			if(act.getType().equals(type)||act.getType().equals(type2)){
			getal = getal+act.getDuration();
			}}
		}
	result = result + getal;
	start.roll(Calendar.DAY_OF_YEAR,true);
	if(start.before(stop)){result = result + ",";
	}
	
}result= result+"]";
return result;
}
public String mySleepInTime(){
return myInTime("Sleep","Sleep");
}
public String mySportInTime(){
return myInTime("Sport","Sport");
}
public String myNightlifeInTime(){
return myInTime("Nightlife","Nightlife");
}
public String myFunInTime(){
return myInTime("Sport","Nightlife");
}
public int myTime(){
	int result=0;

	 User currentUser = UserManager.getInstance().getCurrentUser();
	 ArrayList<Course> courseList = currentUser.getCourses();
	 Iterator<Course> it = courseList.iterator();
	 while(it.hasNext()){
		 Course course = it.next();
		result=result+ data.getTotalScolair(course);
}
	 return result;
}
public int overallTime(){
	int result=0;

	 ArrayList<User> users = UserManager.getInstance().getUsers();
	 for(User user: users){
	 ArrayList<Activity> actList = user.getActivities();
	 for(Activity act:actList){
		 if(act.getActivityType().equals("scolair")){
			 result = result + act.getDuration();
		 }
	 }
}
	 return result;
}

}

