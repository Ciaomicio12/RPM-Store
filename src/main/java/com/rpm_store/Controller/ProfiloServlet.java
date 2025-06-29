package com.rpm_store.Controller;

import com.rpm_store.Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/profilo")
public class ProfiloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null) {
            RequestDispatcher dispatcher = null;
            if (utente.isAdmin()) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/Admin/dati-personali-amministratore.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/Cliente/dati-personali-cliente.jsp");
            }
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
