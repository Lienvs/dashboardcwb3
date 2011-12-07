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
	private TimerController timerControler;
	public StartServlet() {
		timerControler=new TimerController();
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
								timerControler.startTiming(les);
							}
							//zs
							if(req.getParameter("type").equals("Zelfstudie")){
								IndividualStudy zelfstudie=new IndividualStudy(course);
								user.addActivity(zelfstudie);
								timerControler.startTiming(zelfstudie);
							}
							//oz
							if(req.getParameter("type").equals("Oefenzitting")){
								Practice oefenzitting=new Practice(course);
								user.addActivity(oefenzitting);
								timerControler.startTiming(oefenzitting);
							}
						}
				// indien extrascolair gestart:
						if(req.getParameter("wat").equals("Extrascolair")){
							String wa=(String) req.getParameter("watextra");
							ExtraFun extra=ExtraFun.getExtraFun(wa);
							ExtraCurricularActivity act=new ExtraCurricularActivity(extra);
							user.addActivity(act);
							timerControler.startTiming(act);
						}
				getServletContext().getRequestDispatcher("/home").forward(req, resp);	
	
			}
	

}


