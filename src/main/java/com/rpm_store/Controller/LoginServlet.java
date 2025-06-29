package com.rpm_store.Controller;

import com.rpm_store.DAO.UtenteDAO;
import com.rpm_store.Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Mapping
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession();
        if (login.getAttribute("utente") != null) {
            response.sendRedirect(request.getContextPath() + "/user/profilo");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UtenteDAO dao = new UtenteDAO();
        String password = request.getParameter("password");
        Utente utente = dao.doRetrieveByEmailPassword(email, password);
        if (utente != null && utente.isDisabled() == false) {
            HttpSession session = request.getSession();
            session.setAttribute("utente", utente);
            response.sendRedirect(request.getContextPath() + "/user/profilo");
        } else {
            if (utente != null && utente.isDisabled() == true) {
                request.setAttribute("errorserverlogin", "Il tuo account é stato eliminato");
            } else {
                request.setAttribute("errorserverlogin", "Username o password errati!");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Pagine/login.jsp");
            dispatcher.forward(request, response);
        }
    }

}
