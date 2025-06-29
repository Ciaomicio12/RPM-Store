package com.rpm_store.Controller;

import com.rpm_store.DAO.OrdineDAO;
import com.rpm_store.Model.Ordine;
import com.rpm_store.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/cliente/ordini")
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
            for (Ordine ordine : ordini)
                ordine.setUtente(utente);
            request.setAttribute("ordini", ordini);
            request.getRequestDispatcher("/WEB-INF/Pagine/Cliente/lista-ordini-cliente.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
