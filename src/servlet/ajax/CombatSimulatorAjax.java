package servlet.ajax;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CombatSimulator.controller.CombatController;

public class CombatSimulatorAjax {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}

	private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Use a controller to process the request
		CombatController controller = new CombatController();
		//Double result = controller.start();
		
		// Send back a response
		resp.setContentType("text/plain");
		//resp.getWriter().println(result.toString());
	}
}

