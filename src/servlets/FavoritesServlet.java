package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Favorites;
import controller.DBMethodsController;
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
		faction = req.getParameter("faction");
		
		
		req.getRequestDispatcher("/_view/Favorites.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ArrayList<Favorites> favs = new ArrayList<Favorites>();
	}
}
