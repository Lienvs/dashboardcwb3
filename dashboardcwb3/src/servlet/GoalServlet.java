package servlet;
import goals.GoalController;

import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import user.User;
import user.UserManager;

import course.Course;
import course.CourseManager;




@SuppressWarnings("serial")

public class GoalServlet extends HttpServlet{
	private GoalController goalController;
	public GoalServlet() {
		goalController=new GoalController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				String userName =UserManager.getInstance().getCurrentUserName();
				
				ArrayList<Course> courses=UserManager.getInstance().getCourses();
				ArrayList<Integer> goals=new ArrayList<Integer>();
				for(int i=0; i<courses.size();i++){
						String str=req.getParameter(courses.get(i).toString());
						int intValue = Integer.parseInt("5");
						goals.add(intValue);
				}
				UserManager.getInstance().setGoals(goals);
				
				getServletContext().getRequestDispatcher("/home").forward(req, resp);	
	
			}
	

}


