package dashboardcwb3;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Dashboardcwb3Servlet extends HttpServlet {
	
	private LoginController network;
	public Dashboardcwb3Servlet() {
		network = new LoginController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		
		throws IOException {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/plain");
			String userName=req.getParameter("username");
			String password=req.getParameter("password");
				if(network.login(userName,password)) {    
					out.println(userName + ", you are now logged in.");
				}
				else {
					out.println("Login failed, please try again");
		
				}
				
		}
	

	
	
}