package com.rpm_store.Controller;

import com.rpm_store.DAO.GenereDAO;
import com.rpm_store.DAO.VinileDAO;
import com.rpm_store.Model.Genere;
import com.rpm_store.Model.Vinile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/aggiunta-mofica")
public class AggiuntaModificaServlet extends HttpServlet {
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
            request.setAttribute("Vinile", vinile);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/Admin/impostazioni-prodotto.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
