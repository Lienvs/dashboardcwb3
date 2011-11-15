package dashboardcwb3;
import java.util.*;

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
			if(req.getParameter("signup").equals("Sign up")){}
			else{
				if(network.login(req.getParameter("username"),req.getParameter("password"))) {    
					resp.getWriter().println(req.getParameter("username") + ", you are now logged in.");
				}
				else {
					resp.getWriter().println("Login failed.");
				}
			}	
		}
	
	public void doReg(HttpServletRequest req, HttpServletResponse resp)

			throws IOException {
			resp.setContentType("text/plain");
			if(network.register(req.getParameter("username"),req.getParameter("password"),req.getParameter("confirmedpassword"),req.getParameter("fistname"),req.getParameter("lastname"),req.getParameter("gender"),req.getParameter("rnumber"))){
				resp.getWriter().println(req.getParameter("username") + ", you have succesfully been signed up.");
			}
			else{
				resp.getWriter().println("Sign up failed.");
			}
	
	}
}