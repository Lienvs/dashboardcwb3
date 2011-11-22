package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class StartServlet extends HttpServlet{
	private UserManager userManager;
	public StartServlet() {
		userManager= new UserManager();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				
				
				
				
	
			}
	

}


