package servlet;
import java.util.*;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import user.User;//betekent: User uit package user
import user.UserManager;

import course.Course;
import course.CourseManager;

import activity.Lecture;
import activity.Practice;
import activity.TimerController;
import activity.IndividualStudy;



@SuppressWarnings("serial")

public class HomeServlet extends HttpServlet{
	private TimerController timerController;
	public HomeServlet() {
		timerController=new TimerController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user =UserManager.getInstance().getCurrentUser();
				if(req.getParameter("ok")==null){
				}
				else{
					if(req.getParameter("wat")!=null){
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
				
				if(req.getParameter("stop")==null){
				}
				else{
					timerController.stopTiming();
				}
				
				if(timerController.isBusy()){
					req.setAttribute("bezig", "ja");
					req.setAttribute("voorstel",req.getSession().getAttribute("type"));
					
				}
				if(!timerController.isBusy()){
					req.setAttribute("bezig", "nee");
				}
				ArrayList<Course> allCourses = CourseManager.getInstance().getAllCourses();
				ArrayList<Course> courses = user.getCourses();
				req.setAttribute("allcourses", allCourses);
				req.setAttribute("courses", courses);
				req.setAttribute("activities", user.getActivities());
				req.setAttribute("username", UserManager.getInstance().getCurrentUser().getUserName());
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	

}


