package servlet;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import user.LoginController;
import user.RegisterController;

import course.CourseManager;




@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private LoginController network;
	public LoginServlet() {
		network = new LoginController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		
		throws ServletException, IOException {
			resp.setContentType("text/plain");

			String userName=req.getParameter("username");    //login afhandelen
			String password=req.getParameter("password");
				if(network.login(userName,password)) {    
					//req.setAttribute("currentUser", UserManager.getInstance().getCurrentUser().getUserName());
					//req.setAttribute("course", CourseManager.getInstance().getAllCourses());
					getServletContext().getRequestDispatcher("/home").forward(req, resp);
				}
				else {
					req.setAttribute("message1", "Login failed.");
					req.setAttribute("message2", "Please try again, or sign up.");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}

		}

}

