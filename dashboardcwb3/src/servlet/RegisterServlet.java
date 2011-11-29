package servlet;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import user.RegisterController;

import course.CourseManager;



@SuppressWarnings("serial")

public class RegisterServlet extends HttpServlet{
	
	private RegisterController network;
	public RegisterServlet() {
		network = new RegisterController();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
				resp.setContentType("text/plain");
				String userName = req.getParameter("username");
				String password = req.getParameter("password");
				String confirmedPassword = req.getParameter("confirmedpassword");
				String firstName = req.getParameter("firstname");
				String lastName = req.getParameter("lastname");
				String gender = req.getParameter("gender");
				String rNumber = req.getParameter("rnumber");
				if(network.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber)){
					req.setAttribute("course", CourseManager.getInstance().getAllCourses());
					
					String str="[['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14],['Out of home', 16],['Commuting', 7], ['Orientation', 9]]";
					req.setAttribute("stri", str);
					
					getServletContext().getRequestDispatcher("/courseselection.jsp").forward(req, resp);   //courseselection.jsp
				}
				else{
					req.setAttribute("message1", "Sign up failed.");
					req.setAttribute("message2", "Please try again, or login.");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}
	
			}
}


