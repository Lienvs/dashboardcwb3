package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class StartServlet extends HttpServlet{
	
	public StartServlet() {
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				User user = UserManager.getInstance().getCurrentUser();
				req.setAttribute("courses", user.getCourses());
				getServletContext().getRequestDispatcher("/start.jsp").forward(req, resp);	
				
				
	
			}
	

}


