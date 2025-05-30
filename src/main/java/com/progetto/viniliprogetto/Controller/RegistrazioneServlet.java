package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession login = request.getSession();
        if (login.getAttribute("utente") != null) {
            response.sendRedirect(request.getContextPath());
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Pagine/registrazione.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO utentedao = new UtenteDAO();
        String error = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordconfirm = request.getParameter("confirm-password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String sesso = request.getParameter("sesso");
        String email = request.getParameter("email");
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
        Utente us = utentedao.doRetrieveByUsername(username);
        if (username.length() == 0) {
            error = error + "L'username non può essere vuoto<br>";
            check = false;
        } else if (us != null) {
            error = error + "L'username é già stato usato<br>";
            check = false;
        }
        if (email.length() == 0) {
            error = error + "L'email non può essere vuota<br>";
            check = false;
        } else if (utentedao.mailEsistente(email)) {
            error = error + "L'email é già stata usata da un altro account<br>";
            check = false;
        } else if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")) {
            error = error + "L'email é mal formata, l'email deve contere una @ e un . ad esempio: \"example@email.com\"<br>";
            check = false;
        }
        if (password.length() == 0) {
            error = error + "La password non può essere vuota<br>";
            check = false;
        } else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")) {
            error = error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32<br>&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola<br>&nbsp;&nbsp;&nbsp;Deve contenere un numero<br>";
            check = false;
        } else if (!password.equals(passwordconfirm)) {
            error = error + "La password e la conferma password sono diverse<br>";
            check = false;
        }
        if (passwordconfirm.length() == 0) {
            error = error + "La conferma password non può essere vuota<br>";
            check = false;
        }
        if (!(sesso.equals("Maschio") || sesso.equals("Femmina"))) {
            error = error + "Sesso non valido<br>";
            check = false;
        }

        if (check) {
            Utente utente = new Utente();
            utente.setAdmin(false);
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setUsername(username);
            utente.setPassword(password);
            utente.setSesso(sesso);
            utente.setEmail(email);
            utentedao.doSave(utente);
            HttpSession session = request.getSession();
            session.setAttribute("utente", utente);
            response.sendRedirect(request.getContextPath() + "/Cliente/dati-personali-cliente.jsp");
        } else {
            request.setAttribute("formerror", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Pagine/registrazione.jsp");
            dispatcher.forward(request, response);
        }
    }
}
