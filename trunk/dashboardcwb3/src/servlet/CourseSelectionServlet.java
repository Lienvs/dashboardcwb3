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
				String userName = UserManager.getInstance().getCurrentUserName();
				
				ArrayList<Course> courses = UserManager.getInstance().getAllCourses(userName);
				ArrayList<Course> allcourses = CourseManager.getInstance().getAllCourses();//welke van de 2?
				Boolean possible=false;
				for(int i=0; i<allcourses.size() && !possible; i++){
					if(req.getParameter(allcourses.get(i).toString())!=null){
						possible=true;
					}
				}
				
				if(possible){
					for(int i=0; i<allcourses.size() ;i++){
						if(req.getParameter(allcourses.get(i).toString())==null){
							for(int j=0; j<courses.size();j++){
								if(courses.get(j).toString().equals(allcourses.get(i).toString())){
									courses.remove(allcourses.get(i));
								}
							}
						}
						else{
							for(int j=0; j<courses.size();j++){
								if(!courses.get(j).toString().equals(allcourses.get(i).toString())){
									courses.add(CourseManager.getInstance().getAllCourses().get(i));
								}
							}
						}
					}
					UserManager.getInstance().updateCourses(userName,courses);
					
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


