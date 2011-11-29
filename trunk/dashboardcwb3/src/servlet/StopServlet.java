package servlet;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import activity.TimerController;



@SuppressWarnings("serial")

public class StopServlet extends HttpServlet{
	private TimerController timerController;
	public StopServlet() {
		timerController=new TimerController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				timerController.stopTiming();
				
	}
}


