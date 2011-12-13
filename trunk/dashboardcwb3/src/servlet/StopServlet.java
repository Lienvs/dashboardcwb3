package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import user.UserManager;

import course.CourseManager;

import activity.ActivityManager;
import activity.TimerController;




@SuppressWarnings("serial")

public class StopServlet extends HttpServlet{
	private TimerController timerControler;
	public StopServlet() {
		timerControler=new TimerController();
	}
	//indien activity gestopt
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
					String place ="";
					place=(String) req.getParameter("place");
					String stype ="";
					stype=(String) req.getParameter("stype");
					String studyBuddy = (String) req.getParameter("studybuddy");
					int rating=-1; 
					if(req.getParameter("rating")!=null){
						rating=new Integer(req.getParameter("rating"));
					}
					String comment=(String) req.getParameter("comment");
					timerControler.stopTiming();
					ActivityManager.getInstance().updateActivity(rating,comment,place,stype, studyBuddy);
					
				
			getServletContext().getRequestDispatcher("/home").forward(req, resp);	
	
			}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
		
			doPost(req,resp);
	}
	

}
