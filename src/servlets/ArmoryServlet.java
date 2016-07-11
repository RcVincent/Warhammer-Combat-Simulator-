package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Armory;
import model.User;
import controller.DBMethodsController;
public class ArmoryServlet {
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
		
		String utype = null;
		String userType = (String) req.getSession().getAttribute("type");
		if(userType.equals("admin")) {
			utype = "admin";	
		}
		
		//set the user account type
		req.setAttribute("utype", utype);
		ArrayList<Armory> weapons = null; 
		String faction_name = (String)req.getSession().getAttribute("faction");
		
		weapons = c.getArmory(faction_name);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
