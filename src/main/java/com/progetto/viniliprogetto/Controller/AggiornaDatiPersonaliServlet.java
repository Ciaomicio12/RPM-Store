package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/cambiadatipersonali")
public class AggiornaDatiPersonaliServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String email = request.getParameter("email");
        String error = "";
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        UtenteDAO dao = new UtenteDAO();
        Boolean check = true;
        if (nome.length() == 0) {
            error = error + "Il nome non può essere vuoto<br>";
            check = false;
        } else if (!nome.matches("[a-zA-Z]+")) {
            error = error + "Il nome deve contenere solo caratteri<br>";
            check = false;
        }
        if (cognome.length() == 0) {
            error = error + "Il cognome non può essere vuoto<br>";
            check = false;
        } else if (!cognome.matches("[a-zA-Z]+")) {
            error = error + "Il cognome deve contenere solo caratteri<br>";
            check = false;
        }
        if (email.length() == 0) {
            error = error + "L'email non può essere vuota<br>";
            check = false;
        } else if (!utente.getEmail().trim().equals(email) && dao.mailEsistente(email)) {
            error = error + "L'email é già stata usata da un altro account<br>";
            check = false;
        } else if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")) {
            error = error + "L'email é mal formata, l'email deve contere una @ e un . ad esempio: \"example@email.com\"<br>";
            check = false;
        }
        if (check) {
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            dao.doUpdateUtente(utente);
            response.sendRedirect(request.getContextPath() + "/user/profilo");
        } else
            throw new MyServletException(error, 400);
    }
}
