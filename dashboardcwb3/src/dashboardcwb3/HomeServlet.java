package dashboardcwb3;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class HomeServlet extends HttpServlet{
	private TimerController timerController;
	public HomeServlet() {
		timerController=new TimerController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user =UserManager.getInstance().getCurrentUser();
				if(req.getParameter("ok")==null){
				}
				else{
					if(req.getSession().getAttribute("scol")!=null){
						String vak=req.getParameter("vak");
						Course course=null;
						for(int i=0; i<user.getCourses().size(); i++){
							if(user.getCourses().get(i).toString().equals(vak)){
								course=user.getCourses().get(i);
							}
						}
						if(req.getSession().getAttribute("type").equals("les")){
							Les les=new Les(course);
							user.addActivity(les);
							timerController.startTiming(les);
						}
						if(req.getSession().getAttribute("type").equals("zelfstudie")){
							Zelfstudie zelfstudie=new Zelfstudie(course);
							user.addActivity(zelfstudie);
							timerController.startTiming(zelfstudie);
						}
						if(req.getSession().getAttribute("type").equals("oefenzitting")){
							Oefenzitting oefenzitting=new Oefenzitting(course);
							user.addActivity(oefenzitting);
							timerController.startTiming(oefenzitting);
						}
						
					}
					if(req.getSession().getAttribute("extra")!=null){
					//add extrascolair
					}	
				}
				
				if(req.getParameter("stop")==null){
				}
				else{
					timerController.stopTiming();
				}
				
				if(timerController.isBusy()){
					req.setAttribute("bezig", "ja");
					req.setAttribute("voorstel",req.getSession().getAttribute("type"));
					
				}
				if(!timerController.isBusy()){
					req.setAttribute("bezig", "nee");
				}
				req.setAttribute("courses", user.getCourses());
				req.setAttribute("activities", user.getActivities());
				req.setAttribute("username", UserManager.getInstance().getCurrentUser().toString());
				getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);	
	
			}
	

}


