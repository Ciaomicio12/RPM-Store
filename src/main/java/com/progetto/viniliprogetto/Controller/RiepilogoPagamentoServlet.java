package com.progetto.viniliprogetto.Controller;

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
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null)
            throw new MyServletException("Utente non trovato", 404);
        request.setAttribute("utente", utente);
        request.getRequestDispatcher("/WEB-INF/Pagine/Cliente/riepilogo-pagamento.jsp").forward(request, response);
    }
}
