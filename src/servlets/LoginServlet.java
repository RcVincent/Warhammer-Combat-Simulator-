package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DBMethodsController;
import model.User;

public class LoginServlet {

	private static final long serialVersionUID = 1L;
	DBMethodsController controller = new DBMethodsController();
	
	//private matchUsernameWithPassword match = null;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		String username = null;
		String password = null;
		username = req.getParameter("username");
		password = req.getParameter("password");
		
		ArrayList<User> user = null;
		//TODO: Find a way to make this work.
		
		if(user.size()>0){
			User u = user.get(0);
			 
			
			if(controller.authenticate(u, password)){
				//Set the session true and set their username
				req.getSession(true).setAttribute("username", username);
				
				req.getSession().setAttribute("userID", u.getUserID());
				
				if(u.getAccountType().equals("admin")){
					resp.sendRedirect(req.getContextPath() + "/AdminPage");
				}
				
				
				resp.sendRedirect(req.getContextPath() + "/Homepage");
				

			}
			
			else{
				errorMessage = "Incorrect Username or Password";
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
			}
		}
		//otherwise, print an error message
		else{
			errorMessage = "Incorrect Username or Password";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
		}
	
		

}
}
