package statistics;
import goals.GoalController;

import java.util.*;

import activity.Activity;
import activity.CurricularActivity;
import activity.ExtraCurricularActivity;
import activity.IndividualStudy;
import activity.StudyLocation;

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
	result = result +"['" +  "Kot" + "'," + data.getDuurKotZelfstudie() + "],";
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
public String myActiTypeCheese(){
	String result = "[";
	result = result +"['" +  "Curricular activity" + "'," + data.getTotalScolair() + "],";
	result = result +"['" +  "Extra-curricular activity" + "'," + data.getTotalFun() + "]";
	
	result = result + "]";
	return result;
}
public String myTypeCheese(){
	String result = "[";
	result = result +"['" +  "Zelfstudie" + "'," + data.getDuurZelfstudie() + "],";
	result = result +"['" +  "Les" + "'," + data.getDuurLes() + "],";
	result = result +"['" +  "Oefenzitting" + "'," + data.getDuurOefenzitting() + "]";
	result = result + "]";
	return result;
	
}
private String myTypeBar(Course course){//geeft 3 getallen in een string voor bar-stat: Les/oef/zs (samen met volgend methode in 1 stat!!)
	String result = "[";
	result= result+ data.getDuurLes(course)+","+data.getDuurOefenzitting(course)+","+data.getDuurZelfstudie(course)+"]";
	return result;
	
}
private String overallMeanTypeBar(Course course){//zelfde als hierboven, ma dan voor het gemiddelde
	String result = "[";
	result= result+ data.getGemiddeldeLes(course)+","+data.getGemiddeldeOefenzitting(course)+","+data.getGemiddeldeStudie(course)+"]";
	return result;

}

public ArrayList<String> typeBar(Course course){//1mytype 2 overallmean type 3types
	ArrayList<String> list = new ArrayList<String>();
	list.add(myTypeBar(course));
	list.add(overallMeanTypeBar(course));
	
	list.add("'"+"Lecture"+"',"+"'"+"Practice"+"',"+"'"+"Individual study"+"'");
	return list;
	
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
	result = result + "["+"'"+(start.getTime().getMonth()+1)+"/"+ start.getTime().getDate() +"/"+ (start.getTime().getYear()+1900) +"'"+ "," + getal + "]";	start.roll(Calendar.DAY_OF_YEAR,true);
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
		result = result + "["+"'"+(start.getTime().getMonth()+1)+"/"+ start.getTime().getDate() +"/"+ (start.getTime().getYear()+1900) +"'"+ "," + getal + "]";	start.roll(Calendar.DAY_OF_YEAR,true);
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
	result = result + "["+"'"+(start.getTime().getMonth()+1)+"/"+ start.getTime().getDate() +"/"+ (start.getTime().getYear()+1900) +"'"+ "," + getal + "]";	start.roll(Calendar.DAY_OF_YEAR,true);
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
		result = result + "["+"'"+(start.getTime().getMonth()+1)+"/"+ start.getTime().getDate() +"/"+ (start.getTime().getYear()+1900) +"'"+ "," + getal + "]";	start.roll(Calendar.DAY_OF_YEAR,true);
		if(start.before(stop)){
			result = result + ",";
		}
	
	}
	result= result+"]";
	return result;
}
public String overallMeanFunInTime(){
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
	result = result + "["+"'"+(start.getTime().getMonth()+1)+"/"+ start.getTime().getDate() +"/"+ (start.getTime().getYear()+1900) +"'"+ "," + getal + "]";	start.roll(Calendar.DAY_OF_YEAR,true);
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
	if(goal!=0){
	result = result/goal;}
	else{result = 100;}
	if (result>100){
		result = 100;
	}
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
		
		r1 = r1 + (go.getGoal(c)-go.getTotalWeek(c));
		
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
public String myTime(){
	int result=0;

	 String currentUserName = UserManager.getInstance().getCurrentUserName();
	 ArrayList<Course> courseList = UserManager.getInstance().getCourses();
	 Iterator<Course> it = courseList.iterator();
	 while(it.hasNext()){
		 Course course = it.next();
		result=result+ data.getTotalScolair(course);
}
	 int uur = result/60;
	 int minuten = result%60; 
	 String show = "You studied already " + uur +" hours and " + minuten + " minutes.";
	 return show;
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
	String result = "0";
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
	int min = maxDuur%60;
	int uur = maxDuur/60;
	result = uur+" h "+min + " min " + "for "+ maxCourse.toString();
	}
	return result;
}
public String myRatingBar(Course course){
	int een=0;
	int twee=0;
	int drie=0;
	int vier=0;
	int vijf=0;
	for(Activity act: UserManager.getInstance().getActivities()){
		if(act.getActivityType().equals("scolair")&&act.getStop()!=null){
			CurricularActivity scol = (CurricularActivity) act;
			if(scol.getCourse().toString().equals(course.toString())){
				if(scol.getRating() == 1){
					een++;
				}
				if(scol.getRating() == 2){
					twee++;
				}
				if(scol.getRating() == 3){
					drie++;
				}
				if(scol.getRating() == 4){
					vier++;
				}
				if(scol.getRating() == 5){
					vijf++;
				}
			}
		}
	}
	String result = "["+een +","+twee +","+drie +","+vier +","+vijf +","+"]";
	return result;
}
public ArrayList<String> myMeanRatingBar(){//1 de gemiddelde rating voor elk vak 2 de vakken
	String r1 = "[";
	String r2 ="[";
	
	Iterator<Course> it = UserManager.getInstance().getCourses().iterator();
	while(it.hasNext()){
		Course course = it.next();
		int i = 0;
		int rating =0;
		int gem = 0;
	for(Activity act: UserManager.getInstance().getActivities()){
		if(act.getActivityType().equals("scolair")&&act.getStop()!=null){
			CurricularActivity scol = (CurricularActivity) act;
			if(scol.getCourse().toString().equals(course.toString())){
				rating = rating + scol.getRating();
				if(scol.getRating()!=0){i++;}
			}
		}
	}
	if(rating!=0&&i!=0){
	gem = rating/i;}
	else{gem = 0;}
	r1 = r1 + gem;
	r2 = r2 +"'"+course.toString()+"'";
	if(it.hasNext()){
		r1 = r1+",";
		r2 = r2 +",";
	}
	}
	r1 = r1+"]";
	r2 = r2 +"]";
	ArrayList<String> list = new ArrayList<String>();
			list.add(0,r1);
			list.add(1,r2);
			return list;
}
public ArrayList<String> myStuddyBuddys(){//1 hoeveel keer de buddy voorkomt 2 de namen van de buddys.
	ArrayList<String> list = new ArrayList<String>();
	String waarden = "[";
	String names ="[";
	int j = 0;
	for(String buddy: UserManager.getInstance().getUserNames() ){
		int w = 0;
		for(Activity act:UserManager.getInstance().getActivities()){
			if(act.getType().equals("Zelfstudie")&&act.getStop()!=null){
			IndividualStudy stu = (IndividualStudy) act;
				if(stu.getStudyBuddy().equals(buddy)){
					w++;
				}
			}	
		}
		if(w!=0){
			if(j==0){
			waarden=waarden +w;
			names = names +"'"+buddy+"'";
			j++;
			}
			else{waarden=waarden +","+w;
			names = names + ","+buddy;
			}
		}
	}
	waarden = waarden+"]";
	names = names ="]";
	list.add(waarden);
	list.add(names);
	return list;
}
public ArrayList<String> myMeanPlaceRatingBar(){//1 de gemiddelde rating voor elke locatie 2 de locaties
	String r1 = "[";
	String r2 ="[";

	ArrayList<StudyLocation> lis =StudyLocation.getStudyLocationAsList();
	Iterator<StudyLocation> it = lis.iterator();
	while(it.hasNext()){
		StudyLocation st = it.next();
		int i = 0;
		int rating =0;
		int gem = 0;
	for(Activity act: UserManager.getInstance().getActivities()){
		if(act.getType().equals("Zelfstudie")&&act.getStop()!=null){
			IndividualStudy scol = (IndividualStudy) act;
			if(scol.getStudyLocation()!=null){
			if(scol.getStudyLocation().equals(st)){
				rating = rating + scol.getRating();
				if(scol.getRating()!=0){i++;}
			}
		}}
	}
	if(rating!=0&&i!=0){
	gem = rating/i;}
	else{gem = 0;}
	r1 = r1 + gem;
	r2 = r2 +"'"+st.toString()+"'";
	if(it.hasNext()){
		r1 = r1+",";
		r2 = r2 +",";
	}
	}
	r1 = r1+"]";
	r2 = r2 +"]";
	ArrayList<String> list = new ArrayList<String>();
			list.add(0,r1);
			list.add(1,r2);
			return list;
}
public ArrayList<String> myMeanBuddyRatingBar(){//1 de gemiddelde rating voor elke buddy 2 de buddy's
	String r1 = "[";
	String r2 ="[";

	ArrayList<String> list =UserManager.getInstance().getUserNames();
	Iterator<String> ix = list.iterator();
	while(ix.hasNext()){
		String buddy = ix.next();
		int i = 0;
		int rating =0;
		int gem = 0;
	for(Activity act: UserManager.getInstance().getActivities()){
		
		if(act.getType().equals("ZelfStudie")&& act.getStop()!=null){
			IndividualStudy scol = (IndividualStudy) act;
			if(scol.getStudyBuddy().equals(buddy)){
				rating = rating + scol.getRating();
				if(scol.getRating()!=0){i++;}
			}
		}
	}
	if(rating!=0&&i!=0){
	gem = rating/i;}
	else{gem = 0;}
	if (i!=0){
	r1 = r1 + gem;
	r2 = r2 +"'"+buddy+"'";
	if(ix.hasNext()){
		r1 = r1+",";
		r2 = r2 +",";
	}}
	}
	r1 = r1+"]";
	r2 = r2 +"]";
	ArrayList<String> listje = new ArrayList<String>();
			listje.add(0,r1);
			listje.add(1,r2);
			return listje;
}
public String overallTypeCheese(){
	String result = "[";
	result = result +"['" +  "Zelfstudie" + "'," + (data.getGemiddeldeStudie()*UserManager.getInstance().getUserNames().size()) + "],";
	result = result +"['" +  "Les" + "'," + (data.getGemiddeldeLes()*UserManager.getInstance().getUserNames().size()) + "],";
	result = result +"['" +  "Oefenzitting" + "'," + (data.getGemiddeldeOefenzitting()*UserManager.getInstance().getUserNames().size()) + "]";
	result = result + "]";
	return result;
	
}
public String overallActiTypeCheese(){
	int scol = 0;
	int fun = 0;
	for(Activity act:ActivityManager.getInstance().getAllActivities()){
		if(act.getActivityType().equals("scolair")){
			scol = scol+act.getDuration();
		}
		else{
			fun = fun+act.getDuration();
		}
	}
	String result = "[";
	result = result +"['" +  "Curricular activity" + "'," + scol + "],";
	result = result +"['" +  "Extra-curricular activity" + "'," + fun + "]";
	
	result = result + "]";
	return result;
}
public String overallPlacesCheese(){
	int bib = 0;
	int home = 0;
	int kot = 0;
	int ot = 0;
	for(Activity act:ActivityManager.getInstance().getAllActivities()){
		if(act.getType().equals("Zelfstudie")){
			IndividualStudy a = (IndividualStudy) act;
			if(a.getLocation().equals("Library")){
				bib = bib+ a.getDuration();
			}
			if(a.getLocation().equals("Home")){
				home = home+ a.getDuration();
			}
			if(a.getLocation().equals("Kot")){
				kot = kot+ a.getDuration();
			}
			if(a.getLocation().equals("Other")){
				ot = ot+ a.getDuration();
			}
		}
	}
	String result = "[";
	result = result +"['" +  "Library" + "'," + bib + "],";
	result = result +"['" +  "Home" + "'," + home + "],";
	result = result +"['" +  "Kot" + "'," + kot + "],";
	result = result +"['" +  "Other" + "'," + ot + "]";
	result = result + "]";
	return result;
}
}
