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
				if(network.register(req.getParameter("username"),req.getParameter("password"),req.getParameter("confirmedpassword"),req.getParameter("fistname"),req.getParameter("lastname"),req.getParameter("gender"),req.getParameter("rnumber"))){
					resp.getWriter().println(req.getParameter("username") + ", you have succesfully been signed up.");
				}
				else{
					req.setAttribute("message", "Sign up failed, please try again, or login.");
					getServletContext().getRequestDispatcher("/registratie.jsp").forward(req, resp);
				}
	
			}
	

}


