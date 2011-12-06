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

public class CourseSelectionServlet extends HttpServlet{

	public CourseSelectionServlet() {

	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user =UserManager.getInstance().getCurrentUser();
				
				ArrayList<Course> courses=user.getCourses();
				
				Boolean possible=false;
				for(int i=0; i<CourseManager.getInstance().getAllCourses().size() && !possible; i++){
					if(req.getParameter(CourseManager.getInstance().getAllCourses().get(i).toString())!=null){
						possible=true;
					}
				}
				
				if(possible){
					for(int i=0; i<CourseManager.getInstance().getAllCourses().size() ;i++){
						if(req.getParameter(CourseManager.getInstance().getAllCourses().get(i).toString())==null){
							if(courses.contains(CourseManager.getInstance().getAllCourses().get(i))){
								user.removeCourse((CourseManager.getInstance().getAllCourses().get(i)));
							}
						}
						else{
							if(courses.contains(CourseManager.getInstance().getAllCourses().get(i))){}
							else{
								user.addCourse(CourseManager.getInstance().getAllCourses().get(i));
								
							}
						}
					}
					//updateUserCourses()
				
					getServletContext().getRequestDispatcher("/home").forward(req, resp);	
				}
				if(!possible){
					req.setAttribute("message", "Please select at least one course");
					req.setAttribute("course", CourseManager.getInstance().getAllCourses());
					if(req.getParameter("submitcourse")!=null){
						getServletContext().getRequestDispatcher("/courseselection.jsp").forward(req, resp);	
					}
				}
				
				
				
				
				
	
			}
	

}


