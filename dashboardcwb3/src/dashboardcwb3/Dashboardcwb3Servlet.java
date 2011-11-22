package dashboardcwb3;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Dashboardcwb3Servlet extends HttpServlet {
	
	private LoginController network;
	public Dashboardcwb3Servlet() {
		network = new LoginController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		
		throws ServletException, IOException {
			resp.setContentType("text/plain");
			
			if(req.getParameter("logout")==null){
			
			
			
				if(UserManager.getInstance().getCurrentUser()==null){  //atribuut currentuser definieren
					req.setAttribute("currentuser", "No user online.");
				}
				else{
					req.setAttribute("currentuser", UserManager.getInstance().getCurrentUser().getUserName());
				}
			
			
			
					String userName=req.getParameter("username");    //login afhandelen
					String password=req.getParameter("password");
						if(network.login(userName,password)) {    
							req.setAttribute("currentUser", UserManager.getInstance().getCurrentUser().getUserName());
							getServletContext().getRequestDispatcher("/home").forward(req, resp);
						}
						else {
							req.setAttribute("message", "Login failed, please try again, or sign up.");
							getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
						}
					
			}
			else{
				
				network.logout();
				
				if(UserManager.getInstance().getCurrentUser()==null){  //atribuut currentuser definieren
					req.setAttribute("currentuser", "No user online.");
				}
				else{
					req.setAttribute("currentuser", UserManager.getInstance().getCurrentUser().getUserName());
				}
				getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				
				
			}
				
		}
	
	
	
}