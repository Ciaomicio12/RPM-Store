
package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Vinile;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/ricercaajax")
public class RicercaAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VinileDAO vinileDAO = new VinileDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONArray prodJson = new JSONArray();
		String query = request.getParameter("q");
		if (query != null) {
			List<Vinile> vinile = vinileDAO.doRetrieveByNome(query, 0, 10);
			for (Vinile p : vinile) {
				prodJson.put(p.getTitolo());
			}
		}
		response.setContentType("application/json");
		response.getWriter().append(prodJson.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
