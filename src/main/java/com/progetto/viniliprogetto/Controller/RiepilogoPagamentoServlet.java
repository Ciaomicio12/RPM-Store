package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cliente/riepilogopagamento")
public class RiepilogoPagamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            throw new MyServletException("Errore nel parametro", 404);
        }
        int id = Integer.parseInt(idStr);
        UtenteDAO dao = new UtenteDAO();
        Utente utente = dao.doRetrieveById(id);
        if (utente == null) {
            throw new MyServletException("Utente non trovato", 404);
        }
        request.setAttribute("utente", utente);
        request.getRequestDispatcher("/Pagine/Cliente/riepilogo-pagamento.jsp").forward(request, response);
    }
}
