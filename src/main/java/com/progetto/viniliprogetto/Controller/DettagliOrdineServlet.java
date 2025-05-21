package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.OrdineDAO;
import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Ordine;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/dettagliordine")
public class DettagliOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == false) {
            request.setAttribute("utente", utente);
            String id = request.getParameter("id");
            OrdineDAO dao = new OrdineDAO();
            Ordine ordine = dao.doRetriveById(Integer.parseInt(id));
            if (ordine == null) throw new MyServletException("Ordine non trovato");
            request.setAttribute("ordine", ordine);
            request.getRequestDispatcher("Pagine/ordine-in-dettaglio.jsp").forward(request, response);
        } else if (utente != null && utente.isAdmin() == true) {
            request.setAttribute("utente", utente);
            String id = request.getParameter("id");
            OrdineDAO dao = new OrdineDAO();
            Ordine ordine = dao.doRetriveById(Integer.parseInt(id));
            if (ordine == null) throw new MyServletException("Ordine non trovato");
            request.setAttribute("ordine", ordine);
            request.getRequestDispatcher("Pagine/ordine-in-dettaglio.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
