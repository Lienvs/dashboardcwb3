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

import activity.Activity;
import activity.ExtraCurricularActivity;
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
				//User user =UserManager.getInstance().getCurrentUser();
				
				//indien er een activiteit bezig is
				if(timerControler.isBusy()){
					req.setAttribute("bezig", "ja");
					req.setAttribute("allusersstring", UserManager.getInstance().getUsersString());
					req.setAttribute("curract", timerControler.getCurrentActivity());
					req.setAttribute("startDate", timerControler.getCurrentActivity().getStart().getTime());
				}
				
				ArrayList<Course> allCourses = CourseManager.getInstance().getAllCourses();
				ArrayList<Course> courses = UserManager.getInstance().getCourses();
				
				
				req.setAttribute("allcourses", allCourses);//alle vakken 
				req.setAttribute("courses", courses);//vakken student
				req.setAttribute("plaatsen", StudyLocation.getStudyLocationAsList());//plaatsen waar men kan studeren
				req.setAttribute("username", UserManager.getInstance().getCurrentUserName());//username
				ArrayList<Integer> goal= UserManager.getInstance().getGoals();
				req.setAttribute("goal", goal);//goals
				
				ArrayList<Activity> act=UserManager.getInstance().getActivities();
				ArrayList<Activity> scol=new ArrayList<Activity>();
				ArrayList<Activity> extra=new ArrayList<Activity>();
				for(int i=0; i<act.size(); i++){
					if(act.get(i).getType().equals("les") ||act.get(i).getType().equals("Oefenzitting") ||act.get(i).getType().equals("Zelfstudie")){
						scol.add(act.get(i));
					}
					else{
						extra.add(act.get(i));
					}
				}
								
				req.setAttribute("scol", scol);
				req.setAttribute("extra", extra);
				//einde attributen
				
				
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	
	
	
}
