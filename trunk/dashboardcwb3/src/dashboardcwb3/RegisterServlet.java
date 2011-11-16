package dashboardcwb3;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")

public class RegisterServlet extends HttpServlet{
	private UserManagement network;
	public RegisterServlet() {
		network = new UserManagement();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)

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


