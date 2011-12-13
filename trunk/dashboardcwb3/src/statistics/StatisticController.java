package statistics;
import goals.GoalController;

import java.util.*;

import activity.Activity;
import activity.ExtraCurricularActivity;

import course.Course;

import activity.ActivityManager;

import user.User;
import user.UserManager;
public class StatisticController {
	
private DataController data;

public StatisticController(){
	data = new DataController();
}

public String myCoursesCheese(){
	String result = "[";
	 String currentUserName = UserManager.getInstance().getCurrentUserName();
	 ArrayList<Course> courseList = UserManager.getInstance().getCourses();
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
public ArrayList<String> myCourseBar(){//geeft 2 strings: het eerste zijn de P-waarden (zoals nodig voor grafiek) de 2de de O-waarden en  de 3de zijn de bijhorende vaknamen(zoals nodig voor assen van grafiek)
	String result1 = "[";
	String result2 = "[";
	String result3= "['";
	
	Iterator<Course> it = UserManager.getInstance().getCourses().iterator();
	while(it.hasNext()){
		Course course = it.next();
		result1 = result1 + data.getTotalScolair(course);
		result2 = result2 + data.getGemiddeldeScolair(course);
		result3 = result3 + course.toString()+"'";
		
		if(it.hasNext()){
		result1 = result1 + ",";
		result2 = result2 + ",";
		result3 = result3 + ",'";
	}
	}
	result1 = result1 + "]";
	result2 = result2 + "]";
	result3 = result3 + "]";
	ArrayList<String> list = new ArrayList<String>();
	list.add(0,result1);
	list.add(1,result2);
	list.add(2,result3);
	return list;
}

public String myTimeInTime2(Date startDate){//dit is voor een week per dag bekeken
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	stop.setTime(startDate);
	start.setTime(startDate);
	int i = 0;
	String currentUserName = UserManager.getInstance().getCurrentUserName();
	while(i<8){
		int getal=0;
		stop.roll(Calendar.DAY_OF_YEAR,true);
	for(Activity act:UserManager.getInstance().getActivities()){
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

public String overallMeanTimeInTime(){
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	Calendar mid = Calendar.getInstance();
	int i = 7;
	while(i>0){
		start.roll(Calendar.DAY_OF_YEAR,false);
		mid.roll(Calendar.DAY_OF_YEAR,false);
		i--;
	}

	while(start.before(stop)){
		int getal=0;
		int total=0;
		int users = 0;
		boolean ja = false;
		mid.roll(Calendar.DAY_OF_YEAR,true);
		for(String userName:UserManager.getInstance().getUserNames()){
			 ja = false;
	for(Activity act:UserManager.getInstance().getActivities(userName)){
		if(act.getStart().after(start.getTime())&&act.getStart().before(mid.getTime())&&act.getActivityType().equals("scolair")){
			getal = getal+act.getDuration();
			ja = true;
			}
		}
		if(ja){
			users++;
		}
	}
		if(users ==0){
			total = 0;
		}
		else{
		total = getal/users;
		}
	result = result + total;
	start.roll(Calendar.DAY_OF_YEAR,true);
	if(start.before(stop)){result = result + ",";
	}
	
}result= result+"]";
return result;
}

public String myTimeInTime(){//dit is voor 20 weken (in januari minder) week per week bekeken.
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	Calendar mid= Calendar.getInstance();
	int i = 7;
	while(i>0){
		start.roll(Calendar.DAY_OF_YEAR,false);
		mid.roll(Calendar.DAY_OF_YEAR,false);
		i--;
	}
	
	String currentUserName = UserManager.getInstance().getCurrentUserName();
	while(start.before(stop)){
		int getal=0;
		mid.roll(Calendar.DAY_OF_YEAR,true);
	for(Activity act:UserManager.getInstance().getActivities()){
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
	Calendar mid = Calendar.getInstance();
	int i = 7;
	while(i>0){
		start.roll(Calendar.DAY_OF_YEAR,false);
		mid.roll(Calendar.DAY_OF_YEAR,false);
		i--;
	}
	
	
	String currentUserName = UserManager.getInstance().getCurrentUserName();
	while(start.compareTo(stop)<0){
		int getal=0;
		mid.roll(Calendar.DAY_OF_YEAR,1);
		for(Activity act:UserManager.getInstance().getActivities()){
			if(act.getStart().after(start.getTime())&&act.getStart().before(mid.getTime())){
			
				if(act.getType().equals(type)||act.getType().equals(type2)){
						getal = getal+act.getDuration();
				}
			}
		}
		result = result + getal;
		start.roll(Calendar.DAY_OF_YEAR,1);
		if(start.before(stop)){
			result = result + ",";
		}
	
	}
	result= result+"]";
	return result;
}
private String overallMeanFunInTime(){
	String result ="[";
	Calendar stop = Calendar.getInstance();
	Calendar start = Calendar.getInstance();
	Calendar mid = Calendar.getInstance();
	int i = 7;
	while(i>0){
		start.roll(Calendar.DAY_OF_YEAR,false);
		mid.roll(Calendar.DAY_OF_YEAR,false);
		i--;
	}
	for(String userName:UserManager.getInstance().getUserNames()){
	
	while(start.before(stop)){
		int getal=0;
		mid.roll(Calendar.DAY_OF_YEAR,true);
	for(Activity act:UserManager.getInstance().getActivities()){
		if(act.getStart().after(start.getTime())&&act.getStart().before(mid.getTime())){
			
			if(act.getType().equals("Sport")||act.getType().equals("Nightlife")){
			getal = getal+act.getDuration();
			}}
		}
	getal = getal/UserManager.getInstance().getUserNames().size();
	result = result + getal;
	start.roll(Calendar.DAY_OF_YEAR,true);
	if(start.before(stop)){result = result + ",";
	}
	
}}
	result= result+"]";
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


public int meVSGoal2(Course course){
	GoalController go = new GoalController();
	int goal = go.getGoal(course.toString())*60;
	int verschil = go.getDifGoal(course.toString());
	int studieTime = goal-verschil;
	int result = studieTime*100;
	result = result/goal;
	return result;
}
public ArrayList<String> meVSModel(){//1wat al gedaan,2 goals, 3 modeltraject,4 vakken
	String r1="[";
	String r2 = "[";
	String r3 = "[";
	String r4 = "['";
	GoalController go = new GoalController();
	Iterator<Course> it = UserManager.getInstance().getCourses().iterator();
	
	while(it.hasNext()){
		Course course= it.next();
		String c = course.toString();
		r2=r2+go.getGoal(c);
		r3= r3+course.getAvarageWork();
		r4 = r4 + c +"'";
		
		r1 = r1 + (go.getGoal(c)-go.getDifGoal(c));
		
		if(it.hasNext()){
			r1 = r1 +",";
			r2 = r2 +",";
			r3 = r3 +",";
			r4 = r4 + ",'";
		}
	}
	r1 = r1 +"]";
	r2 = r2 +"]";
	r3 = r3 +"]";
	r4 = r4 +"]";
	ArrayList<String> list = new ArrayList<String>();
	list.add(0,r1);
	list.add(1,r2);
	list.add(2,r3);
	list.add(3,r4);
	return list;
}
public int meVSModel2(Course course){
	int result = 0;
	int a = course.getAvarageWork()*13;
	int b = data.getTotalScolair(course);
	if(a==0||b==0){
		result = 0;
	}
	else{
		a =a*100;
		b= b*100;
		result = b/a;
	}
	if(result>100){
		result = 100;
	}
	return result;
}
public int myTime(){
	int result=0;

	 String currentUserName = UserManager.getInstance().getCurrentUserName();
	 ArrayList<Course> courseList = UserManager.getInstance().getCourses();
	 Iterator<Course> it = courseList.iterator();
	 while(it.hasNext()){
		 Course course = it.next();
		result=result+ data.getTotalScolair(course);
}
	 return result;
}
public int overallTime(){
	int result=0;

	 
	 ArrayList<Activity> actList = ActivityManager.getInstance().getAllActivities();
	 for(Activity act:actList){
		 if(act.getActivityType().equals("scolair")){
			 result = result + act.getDuration();
		 }
	 }

	 return result;
}

public String getMaximumStudie(){
	String currentUserName = UserManager.getInstance().getCurrentUserName();
	String result = "You didn't study at all";
	int maxDuur =0;
	ArrayList<Course> courseList= UserManager.getInstance().getCourses();
	if(courseList.size()>0){
	Course maxCourse= courseList.get(0);
	for(Course course:courseList){
		if(data.getDuurZelfstudie(course) > maxDuur){
			maxDuur = data.getDuurZelfstudie(course);
			maxCourse = course;
		}
	}
	result = "You studied " + maxDuur + " for " + maxCourse.toString();
	}
	return result;
}

}
