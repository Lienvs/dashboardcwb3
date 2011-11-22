package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class CourseSelectionServlet extends HttpServlet{

	public CourseSelectionServlet() {

	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");

				for(int i=0; i<CourseManager.getInstance().getAllCourses().size() ;i++){
					if(req.getParameter(CourseManager.getInstance().getAllCourses().get(i).toString())==null){}
					else{
						Student student =(Student) UserManager.getInstance().getCurrentUser();
						student.addCourse(CourseManager.getInstance().getAllCourses().get(i));
						
					}
					
				}
				
				getServletContext().getRequestDispatcher("/home").forward(req, resp);	
				
				
	
			}
	

}


