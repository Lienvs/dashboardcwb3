package statistics;
import java.util.*;

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
	result = result +"['" +  "Bibliotheek" + "'," + data.getDuurBibZelfstudie() + "],";
	result = result +"['" +  "Thuis" + "'," + data.getDuurThuisZelfstudie() + "],";
	result = result +"['" +  "Kot" + "'," + data.getDuurKotZelfstudie() + "]";
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

}
