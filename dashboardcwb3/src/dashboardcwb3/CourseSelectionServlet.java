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
				int j=0;
				for(int i=0; i<courseManager.getAllCourses().size() ;i++){
					if(req.getParameter(courseManager.getAllCourses().get(i).toString())==null){}
					else{
					j++;   //toevoegen aan student?	
					}
					
				}
				req.setAttribute("intCourses", Integer.toString(j));
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
				
				
	
			}
	

}


