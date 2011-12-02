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

import activity.Lecture;
import activity.Practice;
import activity.TimerController;
import activity.IndividualStudy;
import activity.StudyLocation;



@SuppressWarnings("serial")

public class HomeServlet extends HttpServlet{
	private TimerController timerController;
	private StatisticController statController;
	public HomeServlet() {
		timerController=new TimerController();
		statController=new StatisticController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user =UserManager.getInstance().getCurrentUser();
				// indien activity gestart:
				if(req.getParameter("ok")!=null){
					if(req.getParameter("wat")!=null){
						String a="a";
						if(req.getParameter("wat").equals("Scolair")){
							String vak=req.getParameter("vak");
							Course course=null;
							for(int i=0; i<user.getCourses().size(); i++){
								if(user.getCourses().get(i).toString().equals(vak)){
									course=user.getCourses().get(i);
								}
							}
							if(req.getParameter("type").equals("Les")){
								Lecture les=new Lecture(course);
								user.addActivity(les);
								timerController.startTiming(les);
							}
							if(req.getParameter("type").equals("Zelfstudie")){
								IndividualStudy zelfstudie=new IndividualStudy(course);
								user.addActivity(zelfstudie);
								timerController.startTiming(zelfstudie);
							}
							if(req.getParameter("type").equals("Oefenzitting")){
								Practice oefenzitting=new Practice(course);
								user.addActivity(oefenzitting);
								timerController.startTiming(oefenzitting);
							}
						}
						if(req.getParameter("wat").equals("Extrascolair")){
							
						}
					}
				}
				
				//indien activity gestopt
				if(req.getParameter("stop")!=null){
					
					String place=(String) req.getParameter("place");
					String stype=(String) req.getParameter("stype");
					int rating=-1; 
					if(req.getParameter("rating")!=null){
						rating=new Integer(req.getParameter("rating"));
					}
					String comment=(String) req.getParameter("comment");

					timerController.getCurrentActivity().submitVragenLijst(place, stype, comment, rating);
					timerController.stopTiming();
					
				}
				
				//indien er een activiteit bezig is
				if(timerController.isBusy()){
					req.setAttribute("bezig", "ja");
					req.setAttribute("curract", timerController.getCurrentActivity());	
					req.setAttribute("watbezig", timerController.getCurrentActivity().toString());	
				}
				//indien er geen activiteit bezig is
				if(!timerController.isBusy()){
					req.setAttribute("bezig", "nee");
				}
				
				//indien van courseselectionservlet message terug, message overnemen
				if(req.getAttribute("message")!=null){
					req.setAttribute("message", req.getAttribute("message"));
				}
				
				// vakken student, vakken,username, plaatsen
				ArrayList<Course> allCourses = CourseManager.getInstance().getAllCourses();
				ArrayList<Course> courses = user.getCourses();
				req.setAttribute("allcourses", allCourses);
				req.setAttribute("courses", courses);
				req.setAttribute("plaatsen", StudyLocation.getStudyLocationAsList());
					// nodig?  req.setAttribute("activities", user.getActivities());
				req.setAttribute("username", UserManager.getInstance().getCurrentUser().getUserName());
				//einde attributen
				//statistieken doorgeven
				req.setAttribute("comparisonCourses", statController.getComparisonCourses());
				//einde stats
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	

}


