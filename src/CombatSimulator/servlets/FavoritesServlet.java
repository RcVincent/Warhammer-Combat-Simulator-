package CombatSimulator.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CombatSimilator.model.Favorites;
import CombatSimulator.controller.DBMethodsController;
public class FavoritesServlet {
	private static final long serialVersionUID = 1L;
	private DBMethodsController dbc = new DBMethodsController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("username");
		if (user == null) {
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		
		String faction = null;
		ArrayList<Favorites> favs = new ArrayList<Favorites>();
		
		int userId =(int) req.getSession().getAttribute("userID");
		int facId = (int) req.getSession().getAttribute("FactionID");
		
		faction = req.getParameter("faction");
		
		dbc.addFactionToFavs(faction, facId, userId);
		favs = dbc.GetFavorites(userId);
		
		if(favs != null) {
			req.setAttribute("faction", favs);
		}
		
		req.getRequestDispatcher("/_view/Favorites.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ArrayList<Favorites> favs = new ArrayList<Favorites>();
		int userId =(int) req.getSession().getAttribute("userID");
		
		favs = dbc.GetFavorites(userId);
		
		if(favs!=null){
			req.setAttribute("faction", favs);
		}
		
		req.getRequestDispatcher("/_view/Favorites.jsp").forward(req, resp);
	}
}
