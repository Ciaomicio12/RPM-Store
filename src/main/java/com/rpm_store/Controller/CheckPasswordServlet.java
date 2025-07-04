package com.rpm_store.Controller;

import com.rpm_store.DAO.UtenteDAO;
import com.rpm_store.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkpassword")
public class CheckPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("email");
        String password = request.getParameter("password");
        if (user != null && password != null) {
            UtenteDAO utenteDAO = new UtenteDAO();
            response.setContentType("application/text");
            if (utenteDAO.checkPassword(user.getEmail(), password)) {
                response.getWriter().append("false");
            } else {
                response.getWriter().append("true");
            }
        }
    }
}
