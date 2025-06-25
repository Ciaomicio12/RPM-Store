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

@WebServlet("/vinile")
public class DettagliVinileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ean = (String) request.getParameter("ean");
        VinileDAO vinileDAO = new VinileDAO();
        Vinile vinile = vinileDAO.doRetrieveByEan(ean);
        if (vinile != null) {
            request.setAttribute("vinile", vinile);
        } else throw new MyServletException("Vinile non trovato");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/album-in-dettaglio.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}