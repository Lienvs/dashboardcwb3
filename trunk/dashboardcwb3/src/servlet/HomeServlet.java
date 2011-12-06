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
				User user =UserManager.getInstance().getCurrentUser();
				
				//indien er een activiteit bezig is
				if(timerControler.isBusy()){
					req.setAttribute("bezig", "ja");
					req.setAttribute("allusersstring", UserManager.getInstance().getAllUsers());
					req.setAttribute("curract", timerControler.getCurrentActivity());
				}
				
				ArrayList<Course> allCourses = CourseManager.getInstance().getAllCourses();
				ArrayList<Course> courses = user.getCourses();
				req.setAttribute("allcourses", allCourses);//alle vakken 
				req.setAttribute("courses", courses);//vakken student
				req.setAttribute("plaatsen", StudyLocation.getStudyLocationAsList());//plaatsen waar men kan studeren
					// nodig?  req.setAttribute("activities", user.getActivities());
				req.setAttribute("username", UserManager.getInstance().getCurrentUser().getUserName());//username
				//einde attributen
				
				//statistieken doorgeven
				req.setAttribute("myCoursesCheese", statController.myCoursesCheese()); 
				req.setAttribute("myPlacesCheese", statController.getComparisonPlace());
				//req.setAttribute("", statController.);
				//req.setAttribute("", statController.);
				//req.setAttribute("", statController.);
				//req.setAttribute("", statController.);
				//einde stats
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	

}


