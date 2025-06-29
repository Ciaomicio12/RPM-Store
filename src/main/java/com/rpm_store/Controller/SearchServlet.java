package com.rpm_store.Controller;

import com.rpm_store.DAO.VinileDAO;
import com.rpm_store.Model.Vinile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Serve per reinderizzare ed è collegata nelle jsp questo ad esempio è collegata nel form di Header.jsp
@WebServlet("/cerca")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VinileDAO viniledao = new VinileDAO();
        List<Vinile> vinili = viniledao.doRetrieveByNome(request.getParameter("ricerca"), 0, 20);
        request.setAttribute("vinili", vinili);
        request.setAttribute("ricerca", request.getParameter("ricerca"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/search.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
