package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class CourseSelectionServlet extends HttpServlet{
	private CourseManager courseManager;
	public CourseSelectionServlet() {
		courseManager= new CourseManager();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				int totalCourses= courseManager.getCourses().size();
				req.setAttribute("amountCourses", totalCourses);
				for(int i=0; i<totalCourses; i++){
					req.setAttribute("course"+i, courseManager.getCourses().get(i).toString());
				}
				
	
			}
	

}


