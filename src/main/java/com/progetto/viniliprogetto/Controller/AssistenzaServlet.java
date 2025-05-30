package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/assistenza")
public class AssistenzaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if ((utente != null && !utente.isAdmin()) || utente == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/assistenza.jsp");
            requestDispatcher.forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti");
        }
    }
}
