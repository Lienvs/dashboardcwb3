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

import dashboardcwb3.CourseManager;
import dashboardcwb3.LoginController;
import dashboardcwb3.RegisterController;



@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	
	private LoginController network;
	public LogoutServlet() {
		network = new LoginController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		
		throws ServletException, IOException {
			resp.setContentType("text/plain");

			network.logout();
			getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);

		}
}