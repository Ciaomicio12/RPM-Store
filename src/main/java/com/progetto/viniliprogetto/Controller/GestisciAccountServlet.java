package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/gestisciutenti")
public class GestisciAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == true) {
            UtenteDAO dao = new UtenteDAO();
            ArrayList<Utente> li = (ArrayList<Utente>) dao.doRetrieveAll();
            li.remove(dao.doRetrieveById(utente.getId()));
            request.setAttribute("utenti", li);
            request.getRequestDispatcher("/WEB-INF/jsp/gestisciutenti.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli amministratori");
        }
    }
}
