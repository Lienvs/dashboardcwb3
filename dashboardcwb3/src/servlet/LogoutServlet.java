package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import javax.servlet.*;

import user.LoginController;






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