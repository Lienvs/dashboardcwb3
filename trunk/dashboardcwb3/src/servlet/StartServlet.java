package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import user.User;//betekent: User uit package user
import user.UserManager;


import course.Course;

import activity.Lecture;
import activity.Practice;
import activity.TimerController;
import activity.IndividualStudy;
import activity.ExtraFun;
import activity.ExtraCurricularActivity;



@SuppressWarnings("serial")

public class StartServlet extends HttpServlet{
	public StartServlet() {
	}
	//indien activiteit gestart
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user =UserManager.getInstance().getCurrentUser();
				// indien scolair gestart:
						if(req.getParameter("wat").equals("Scolair")){
							//course 
							String vak=req.getParameter("vak");
							Course course=null;
							for(int i=0; i<user.getCourses().size(); i++){
								if(user.getCourses().get(i).toString().equals(vak)){
									course=user.getCourses().get(i);
								}
							}
							//les
							if(req.getParameter("type").equals("Les")){
								Lecture les=new Lecture(course);
								user.addActivity(les);
								TimerController.getInstance().startTiming(les);
							}
							//zs
							if(req.getParameter("type").equals("Zelfstudie")){
								IndividualStudy zelfstudie=new IndividualStudy(course);
								user.addActivity(zelfstudie);
								TimerController.getInstance().startTiming(zelfstudie);
							}
							//oz
							if(req.getParameter("type").equals("Oefenzitting")){
								Practice oefenzitting=new Practice(course);
								user.addActivity(oefenzitting);
								TimerController.getInstance().startTiming(oefenzitting);
							}
						}
				// indien extrascolair gestart:
						if(req.getParameter("wat").equals("Extrascolair")){
							ExtraFun extra=ExtraFun.getExtraFun(req.getParameter("watextra"));
							ExtraCurricularActivity act=new ExtraCurricularActivity(extra);
							TimerController.getInstance().startTiming(act);
						}
				getServletContext().getRequestDispatcher("/home").forward(req, resp);	
	
			}
	

}


