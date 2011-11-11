package dashboardcwb3;

import java.io.IOException;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Dashboardcwb3Servlet extends HttpServlet {
	
	private UserManagement network;
	public Dashboardcwb3Servlet() {
		network = new UserManagement();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		if(network.login(req.getParameter("userName"),req.getParameter("password"))) {
			resp.getWriter().println(req.getParameter("userName") + ", u bent ingelogd.");
		}
		else {
			resp.getWriter().println("Uw login is niet geslaagd.");
		}
		
	}
	
	
}