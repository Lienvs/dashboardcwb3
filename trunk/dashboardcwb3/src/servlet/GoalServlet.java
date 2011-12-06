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
				User user =UserManager.getInstance().getCurrentUser();
				ArrayList<Course> courses=user.getCourses();
				for(int i=0; i<courses.size();i++){
					if(req.getParameter(courses.get(i).toString())!=null){
						double aDouble = Double.parseDouble(req.getParameter(courses.get(i).toString()));
						goalController.setGoal(courses.get(i), aDouble);
					}
				}
				
				getServletContext().getRequestDispatcher("/home").forward(req, resp);	
	
			}
	

}


