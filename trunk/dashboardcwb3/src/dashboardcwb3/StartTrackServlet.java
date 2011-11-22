package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class StartTrackServlet extends HttpServlet{
	private UserManager userManager;
	public StartTrackServlet() {
		userManager= new UserManager();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				
				
				
				
	
			}
	

}


