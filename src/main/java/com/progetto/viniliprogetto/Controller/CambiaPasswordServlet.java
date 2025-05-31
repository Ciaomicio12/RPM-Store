package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/cambiapassword")
public class CambiaPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nuovaPassword = request.getParameter("new-password");
        String vecchiaPassword = request.getParameter("password-attuale");
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String error = "";
        boolean check = true;
        UtenteDAO dao = new UtenteDAO();
        if (dao.checkPassword(utente.getEmail(), vecchiaPassword)) {
            if (nuovaPassword.length() == 0) {
                error = error + "La password non può essere vuota<br>";
                check = false;
            } else if (!nuovaPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")) {
                error = error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32<br>&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola<br>&nbsp;&nbsp;&nbsp;Deve contenere un numero<br>";
                check = false;
            }
            if (check)
                dao.doUpdatePassword(utente.getUsername(), nuovaPassword);
            else
                throw new MyServletException(error, 400);
        } else
            throw new MyServletException("Password non valida", 403);
        response.sendRedirect(request.getContextPath() + "/user/profilo");
    }
}
