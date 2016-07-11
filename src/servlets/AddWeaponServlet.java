package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DBMethodsController;


public class AddWeaponServlet {
	private static final long seriealVersionUID = 1L;
	
	//need to build two database methods and two controller methods before anymore work can be done on these. 
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
			
			req.getRequestDispatcher("/_view/AddWeapon.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String message = null;
		String weaponToRemove = null;
		String weaponToAdd = null;
		String faction_name = (String)req.getSession().getAttribute("faction");
		
		weaponToRemove = req.getParameter("itemToRemove");
		weaponToAdd = req.getParameter("item");
		
		if(weaponToAdd!=null){	
			
			c.AddWeapon(weaponToAdd, faction_name);
			System.out.println("Success");
			message = "Success";
		}
		if(weaponToRemove!=null){	
			
			c.RemoveWeapon(weaponToRemove);
			System.out.println("Success");
			message = "Success";
		}
		req.setAttribute("message", message);
		req.getRequestDispatcher("/_view/AddWeapon.jsp").forward(req, resp);
	}
}
