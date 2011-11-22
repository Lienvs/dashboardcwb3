package dashboardcwb3;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class HomeServlet extends HttpServlet{
	private CourseManager courseManager;
	private UserManager userManager;
	private TimerController timerController;
	public HomeServlet() {
		courseManager= new CourseManager();
		userManager=new UserManager();
		timerController=new TimerController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				Student student =(Student) userManager.getCurrentUser();
				if(req.getParameter("ok").equals("OK")){
					if(req.getParameter("scolair").equals("Scolair")){
						String keuze=req.getParameter("gekozenvak");
						Course vak=null;
						Calendar cal= Calendar.getInstance();
						Date date = cal.getTime();
						for(int i=0; i<student.getCourses().size(); i++){
							if(student.getCourses().get(i).toString().equals("gekozenvak")){
								vak=student.getCourses().get(i);
							}
						}
						if(req.getParameter("les").equals("Les")){
							Les les=new Les(vak,date);
							student.addActivity(les);
						}
						if(req.getParameter("zelfstudie").equals("Zelfstudie")){
							Zelfstudie zelfstudie=new Zelfstudie(vak,date);
							student.addActivity(zelfstudie);
						}
						if(req.getParameter("oefenzitting").equals("Oefenzitting")){
							Oefenzitting oefenzitting=new Oefenzitting(vak,date);
							student.addActivity(oefenzitting);
						}
					}
					if(req.getParameter("extrascolair").equals("Extrascolair")){
						//add extrascolair
					}
					
					
				}
				
				if(timerController.isBusy()){
					req.setAttribute("bezig", "ja");	
				}
				else{
					req.setAttribute("bezig", "nee");
				}
				
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	

}


