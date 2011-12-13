package servlet;

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
				if(network.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber).equals("ok")){
					CourseManager.getInstance().makeCourses();
					req.setAttribute("course", CourseManager.getInstance().getAllCourses());					
					getServletContext().getRequestDispatcher("/courseselection.jsp").forward(req, resp);   //courseselection.jsp
				}
				
				else if(network.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber).equals("userName")){
					
					req.setAttribute("message1", "Sign-up failed");
					req.setAttribute("message2", "Username already in use");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}
				else if(network.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber).equals("rNumber")){
					
					req.setAttribute("message1", "Sign-up failed");
					req.setAttribute("message2", "R-number already in use");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}
				else if(network.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber).equals("password")){
					
					req.setAttribute("message1", "Sign-up failed");
					req.setAttribute("message2", "Unmatching passwords");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}
                else if(network.register(userName,password,confirmedPassword,firstName,lastName,gender,rNumber).equals("fields")){
					
					req.setAttribute("message1", "Sign-up failed");
					req.setAttribute("message2", "Incomplete form");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
				}
                else{
                	req.setAttribute("message1", "Sign-up failed");
					req.setAttribute("message2", "Please try again, or login.");
					getServletContext().getRequestDispatcher("/portal.jsp").forward(req, resp);
                }
			}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)

			throws ServletException, IOException {
		
			doPost(req,resp);
	}
}


