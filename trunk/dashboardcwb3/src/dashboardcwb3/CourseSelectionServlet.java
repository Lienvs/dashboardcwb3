package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class CourseSelectionServlet extends HttpServlet{
	private CourseManager courseManager;
	private UserManager userManager;
	public CourseSelectionServlet() {
		courseManager= new CourseManager();
		userManager=new UserManager();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");

				for(int i=0; i<courseManager.getAllCourses().size() ;i++){
					if(req.getParameter(courseManager.getAllCourses().get(i).toString())==null){}
					else{
						Student student =(Student) userManager.getCurrentUser();
						student.addCourse(courseManager.getAllCourses().get(i));
						
					}
					
				}
				
				getServletContext().getRequestDispatcher("/home").forward(req, resp);	
				
				
	
			}
	

}


