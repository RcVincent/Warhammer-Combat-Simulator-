package CombatSimulator.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CombatSimilator.model.User;
import CombatSimilator.model.Weapon;
import CombatSimulator.controller.DBMethodsController;

public class WeaponServlet {
	private static final long serialVersionUID = 1L;
	private Weapon weaponToAdd = null;
	DBMethodsController c = new DBMethodsController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("username");
		if (user == null) {
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		User u = c.getInfo((String)req.getSession().getAttribute("username")).get(0);
		
		
		req.getRequestDispatcher("/_view/Weapon.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/_view/Weapon.jsp").forward(req, resp);
		
	}
}
