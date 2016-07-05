package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DBMethodsController;
import model.User;

public class AccountServlet {
private static final long serialVersionUID = 1L;
	
	private DBMethodsController c = new DBMethodsController();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("username");
		if (user == null) {
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		req.getRequestDispatcher("/_view/Account.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String firstname = null;
		String lastname = null;
		String email = null;
		String username = (String) req.getSession().getAttribute("username");
		String AccountType = null;
		
		ArrayList<User> u = c.getInfo(username);
		User use = u.get(0);
		firstname = use.getFname();
		lastname = use.getLname();
		email = use.getEmail();
		username = use.getUsername();
		AccountType  = use.getAccountType();

		// Add parameters as request attributes
		req.setAttribute("firstname", firstname);
		req.setAttribute("lastname", lastname);
		req.setAttribute("email", email);
		req.setAttribute("username", username);
		req.setAttribute("AccountType", AccountType);
		

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/Account.jsp").forward(req, resp);
	}
}
