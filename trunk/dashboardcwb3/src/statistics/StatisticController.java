package statistics;
import java.util.*;

import activity.Activity;

import course.Course;



import user.User;
import user.UserManager;
public class StatisticController {
	
private DataController data;

public StatisticController(){
	data = new DataController();
}
public String getComparisonCourses(){
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

public String getComparisonPlace(){
	String result = "[";
	result = result +"['" +  "Library" + "'," + data.getDuurBibZelfstudie() + "],";
	result = result +"['" +  "Home" + "'," + data.getDuurThuisZelfstudie() + "],";
	result = result +"['" +  "Kot" + "'," + data.getDuurKotZelfstudie() + "]";
	result = result +"['" +  "Other" + "'," + data.getDuurOtherZelfstudie() + "]";
	result = result + "]";
	return result;
}
public String getComparisonActivityType(Course course){
	String result = "[";
	result = result +"['" +  "Zelfstudie" + "'," + data.getDuurZelfstudie(course) + "],";
	result = result +"['" +  "Les" + "'," + data.getDuurLes(course) + "],";
	result = result +"['" +  "Oefenzitting" + "'," + data.getDuurOefenzitting(course) + "]";
	result = result + "]";
	return result;
	
}
public String getBarActivityType(Course course){//geeft 3 getallen in een string voor bar-stat: Les/oef/zs
	String result = "[";
	result= result+ data.getDuurLes(course)+","+data.getDuurOefenzitting(course)+","+data.getDuurZelfstudie(course)+"]";
	return result;
	
}
public String getBarAvarageActivityType(Course course){//zelfde als hierboven, ma dan voor het gemiddelde
	String result = "[";
	result= result+ data.getGemiddeldeLes(course)+","+data.getGemiddeldeOefenzitting(course)+","+data.getGemiddeldeStudie(course)+"]";
	return result;

}
public String getStudyDay(Date startDate){
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
		if(act.getStart().after(start.getTime())&&act.getStart().before(stop.getTime())){
			getal = getal+act.getDuration();
			}
		}
	result = result + getal;
	start.roll(Calendar.DAY_OF_YEAR,true);
	i++; if(i<8){result = result + ",";
	}
}
result= result+"]";
return result;}

public String getStudyWeek(){
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
			getal = getal+act.getDuration();
			}
		}
	result = result + getal;
	start.roll(Calendar.DAY_OF_YEAR,true);
	if(start.before(stop)){result = result + ",";
	}
	
}result= result+"]";
return result;}
}

