package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DBMethodsController;

public class AddWeaponServlet {
	private static final long seriealVersionUID = 1L;
	
	//need to build two databasemethods and two controller methods before anymore work can be done on these. 
	private static final long serialVersionUID = 1L;
	private DBMethodsController c = new DBMethodsController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
