package com.rpm_store.Controller;

import com.rpm_store.DAO.UtenteDAO;
import com.rpm_store.Model.Indirizzo;
import com.rpm_store.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cliente/aggiornaindirizzo")
public class AggiornaIndirizzoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utente utente = (Utente) request.getSession().getAttribute("utente");
            Indirizzo indirizzo = ProcessaIndirizzo.getIndirizzoFromRequest(request);
            utente.setIndirizzo(indirizzo);
            UtenteDAO dao = new UtenteDAO();
            dao.doUpdateUtente(utente);
            response.sendRedirect(request.getContextPath() + "/user/profilo");
        } catch (Exception e) {
            throw new MyServletException(e.getMessage(), 404);
        }
    }
}
