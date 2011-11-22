package dashboardcwb3;
import java.util.*;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

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
					
					
					int totalCourses= CourseManager.getInstance().getAllCourses().size();
					req.setAttribute("amountCourses", totalCourses);
						req.setAttribute("course", CourseManager.getInstance().getAllCourses());
					
					
					
					getServletContext().getRequestDispatcher("/courseselection.jsp").forward(req, resp);
				}
				else{
					req.setAttribute("message", "Sign up failed, please try again, or login.");
					getServletContext().getRequestDispatcher("/registratie.jsp").forward(req, resp);
				}
	
			}
	

}


