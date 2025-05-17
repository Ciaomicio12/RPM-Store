package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Vinile;

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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pagine/album-in-dettaglio.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}