package servlet;
import java.util.*;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import user.User;//betekent: User uit package user
import user.UserManager;

import statistics.StatisticController;

import course.Course;
import course.CourseManager;

import activity.TimerController;
import activity.StudyLocation;



@SuppressWarnings("serial")

public class HomeServlet extends HttpServlet{
	private StatisticController statController;
	private TimerController timerControler;
	public HomeServlet() {
		statController=new StatisticController();
		timerControler=new TimerController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				String userName =UserManager.getInstance().getCurrentUserName();
				
				//indien er een activiteit bezig is
				if(timerControler.isBusy()){
					req.setAttribute("bezig", "ja");
					req.setAttribute("allusersstring", UserManager.getInstance().getUserNames());
					req.setAttribute("curract", timerControler.getCurrentActivity());
				}
				
				ArrayList<Course> allCourses = CourseManager.getInstance().getAllCourses();
				ArrayList<Course> courses = UserManager.getInstance().getAllCourses(UserManager.getInstance().getCurrentUserName());
				req.setAttribute("allcourses", allCourses);//alle vakken 
				req.setAttribute("courses", courses);//vakken student
				req.setAttribute("plaatsen", StudyLocation.getStudyLocationAsList());//plaatsen waar men kan studeren
				req.setAttribute("username", UserManager.getInstance().getCurrentUserName());//username
				//einde attributen
				
				//stats
				req.setAttribute("myCoursesCheese", statController.myCoursesCheese());
				req.setAttribute("myPlacesCheese", statController.myPlacesCheese());
				req.setAttribute("myCourseBar1", statController.myCourseBar().get(0));
				req.setAttribute("myCourseBar2", statController.myCourseBar().get(1));
				req.setAttribute("myCourseBar3", statController.myCourseBar().get(2));
				req.setAttribute("myFunInTime", statController.myFunInTime());
				req.setAttribute("myNightlifeInTime", statController.myNightlifeInTime());
				req.setAttribute("mySleepInTime", statController.mySleepInTime());
				req.setAttribute("mySportInTime", statController.mySportInTime());
				req.setAttribute("myTime", statController.myTime());
				req.setAttribute("myTimeInTime", statController.myTimeInTime());
				req.setAttribute("overallTime", statController.overallTime());
				HashMap<Course, ArrayList<String>> hashmap=new HashMap<Course,ArrayList<String>>();//hashmap met vakkan als key en arraylist van string
				ArrayList<String> statscourse=new ArrayList<String>();
				for(int i=0;i<courses.size();i++){
					statscourse.add(statController.myTypeCheese(courses.get(i)));
					statscourse.add(statController.overallMeanTypeBar(courses.get(i)));	
					statscourse.add(statController.myTypeBar(courses.get(i)));
					
					hashmap.put(courses.get(i), statscourse);
				}
				req.setAttribute("arrcoursesstats", hashmap);//hashmap met vakkan als key en arraylist van string
				//einde stats
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	

}


