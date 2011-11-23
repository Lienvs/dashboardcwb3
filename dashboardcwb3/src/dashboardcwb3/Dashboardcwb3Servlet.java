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
	private RegisterController registreNetwork;
	public Dashboardcwb3Servlet() {
		network = new LoginController();
		registreNetwork=new RegisterController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		
		throws ServletException, IOException {
			resp.setContentType("text/plain");
			
			if(req.getParameter("logout")!=null){
				network.logout();
				getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				
			}
			if(req.getParameter("login")!=null){
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
			if(req.getParameter("signup")!=null){
				String userName = req.getParameter("username");
				String password = req.getParameter("password");
				String confirmedPassword = req.getParameter("confirmedpassword");
				String firstName = req.getParameter("firstname");
				String lastName = req.getParameter("lastname");
				String gender = req.getParameter("gender");
				String rNumber = req.getParameter("rnumber");
				if(registreNetwork.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber)){
					req.setAttribute("course", CourseManager.getInstance().getAllCourses());
					
					
					
					getServletContext().getRequestDispatcher("/courseselection.jsp").forward(req, resp);
				}
				else{
					req.setAttribute("message", "Sign up failed, please try again, or login.");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}
			}
			
			
	
	
	}
}