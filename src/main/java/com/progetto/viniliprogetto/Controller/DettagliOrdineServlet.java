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
            String id = request.getParameter("id");
            OrdineDAO dao = new OrdineDAO();
            Ordine ordine = dao.doRetrievebyUserIdAndOra(id, utente.getId());
            if (ordine == null) throw new MyServletException("Errore");
            request.setAttribute("ordine", ordine);
            request.getRequestDispatcher("WEB-INF/jsp/dettagliordine.jsp").forward(request, response);
        } else if (utente != null && utente.isAdmin() == true) {
            String id = request.getParameter("id");
            String userid = request.getParameter("userid");
            int useridn;
            try {
                useridn = Integer.parseInt(userid);
            } catch (NumberFormatException er) {
                throw new MyServletException("Utente non trovato");
            }
            OrdineDAO dao = new OrdineDAO();
            Ordine ordine = dao.doRetrievebyUserIdAndOra(id, useridn);
            Utente user = new Utente();
            UtenteDAO ldao = new UtenteDAO();
            user = ldao.doRetrieveById(useridn);
            if (ordine == null) throw new MyServletException("Errore");
            request.setAttribute("ordine", ordine);
            request.setAttribute("utenteo", user);
            request.getRequestDispatcher("WEB-INF/jsp/dettagliordine.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
