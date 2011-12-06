package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import activity.TimerController;




@SuppressWarnings("serial")

public class StopServlet extends HttpServlet{
	public StopServlet() {
	}
	//indien activity gestopt
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				
					String place=(String) req.getParameter("place");
					String stype=(String) req.getParameter("stype");
					int rating=-1; 
					if(req.getParameter("rating")!=null){
						rating=new Integer(req.getParameter("rating"));
					}
					String comment=(String) req.getParameter("comment");

					TimerController.getInstance().getCurrentActivity().submitVragenLijst(place, stype, comment, rating);
					TimerController.getInstance().stopTiming();
				
			getServletContext().getRequestDispatcher("/home").forward(req, resp);	
	
			}
	

}


