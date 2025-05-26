package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.GenereDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Genere;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/modifica")
public class ModificaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenereDAO genereDAO = new GenereDAO();
        List<Genere> generi = genereDAO.doRetrieveAll();
        request.setAttribute("generi", generi);
        VinileDAO vinileDAO = new VinileDAO();
        String ean = request.getParameter("ean");
        if (ean != null) {
            Vinile vinile = vinileDAO.doRetrieveByEan(ean);
            if (vinile == null)
                throw new MyServletException("Errore vinile non trovato", 404);
            request.setAttribute("vinile", vinile);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Pagine/Admin/modifica-prodotto.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
