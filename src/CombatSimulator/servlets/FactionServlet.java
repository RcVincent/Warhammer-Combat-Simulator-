package CombatSimulator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FactionServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("username");
		if (user == null) {
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		req.getRequestDispatcher("/_view/Faction.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String faction = req.getParameter("faction");
		String utype = null;
		String userType = (String) req.getSession().getAttribute("type");
		
		if(userType.equals("admin")) {
			utype = "admin";
		}
		req.setAttribute("utype", utype);
		
		req.getSession().setAttribute("faction", faction);
		
		req.getRequestDispatcher("/_view/Faction.jsp").forward(req, resp);
	}
}
