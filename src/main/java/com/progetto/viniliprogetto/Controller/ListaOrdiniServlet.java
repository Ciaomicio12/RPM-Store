package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.OrdineDAO;
import com.progetto.viniliprogetto.Model.Ordine;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ordini")
public class ListaOrdiniServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && !utente.isAdmin()) {
            OrdineDAO o = new OrdineDAO();
            ArrayList<Ordine> ordini = o.doRetrieveByUserId(utente.getId());
            request.setAttribute("ordini", ordini);
            request.getRequestDispatcher("WEB-INF/jsp/ordini.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
