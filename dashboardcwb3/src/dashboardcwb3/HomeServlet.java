package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class HomeServlet extends HttpServlet{
	private CourseManager courseManager;
	private UserManager userManager;
	public HomeServlet() {
		courseManager= new CourseManager();
		userManager=new UserManager();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");

				if(req.getParameter("ok").equals("OK")){
				
					if(req.getParameter("scolair").equals("Scolair")){
						if(req.getParameter("les").equals("Les")){
							Les les=new Les();
						}
						if(req.getParameter("zelfstudie").equals("Zelfstudie")){
							
						}
						if(req.getParameter("oefenzitting").equals("Oefenzitting")){
							
						}
					}
					if(req.getParameter("extrascolair").equals("Extrascolair")){
						//add extrascolair
					}
					
				}
				
				
				
	
			}
	

}


