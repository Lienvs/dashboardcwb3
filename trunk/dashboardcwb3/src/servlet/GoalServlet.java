package servlet;
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

	public GoalServlet() {

	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user =UserManager.getInstance().getCurrentUser();
				ArrayList<Course> courses=user.getCourses();
				for(int i=0; i<courses.size();i++){
					if(req.getParameter(courses.get(i).toString())!=null){
						
					}
				}
				
				
				
				
				
				
	
			}
	

}


