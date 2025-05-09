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
            response.sendRedirect("profilo");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Pagine/registrazione.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO utentedao = new UtenteDAO();
        String username = request.getParameter("username");
        String error = "";
        username = request.getParameter("usernamesubmit");
        String password = request.getParameter("password");
        String passwordconfirm = request.getParameter("confirm-password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String sesso = request.getParameter("sesso");
        String email = request.getParameter("email");
        Boolean ceck = true;
        if (nome.length() == 0) {
            error = error + "Il nome non può essere vuoto<br>";
            ceck = false;
        } else if (!nome.matches("[a-zA-Z]+")) {
            error = error + "Il nome deve contenere solo caratteri<br>";
            ceck = false;
        }
        if (cognome.length() == 0) {
            error = error + "Il cognome non può essere vuoto<br>";
            ceck = false;
        } else if (!cognome.matches("[a-zA-Z]+")) {
            error = error + "Il cognome deve contenere solo caratteri<br>";
            ceck = false;
        }
        Utente us = utentedao.doRetrieveByUsername(username);
        if (username.length() == 0) {
            error = error + "L'username non può essere vuoto<br>";
            ceck = false;
        } else if (us != null) {
            error = error + "L'username é già stato usato<br>";
            ceck = false;
        }
        if (email.length() == 0) {
            error = error + "L'email non può essere vuota<br>";
            ceck = false;
        } else if (utentedao.doRetrieveByEmail(email) == true) {
            error = error + "L'email é già stata usata da un altro account<br>";
            ceck = false;
        } else if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")) {
            error = error + "L'email é mal formata, l'email deve contere una @ e un . ad esempio: \"example@email.com\"<br>";
            ceck = false;
        }
        if (password.length() == 0) {
            error = error + "La password non può essere vuota<br>";
            ceck = false;
        } else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")) {
            error = error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32<br>&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola<br>&nbsp;&nbsp;&nbsp;Deve contenere un numero<br>";
            ceck = false;
        } else if (!password.equals(passwordconfirm)) {
            error = error + "La password e la conferma password sono diverse<br>";
            ceck = false;
        }
        if (passwordconfirm.length() == 0) {
            error = error + "La conferma password non può essere vuota<br>";
            ceck = false;
        }
        if (!(sesso.equals("Maschio") || sesso.equals("Femmina"))) {
            error = error + "Sesso non valido<br>";
            ceck = false;
        }
        if (ceck == true) {
            Utente utente = new Utente();
            utente.setAdmin(false);
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setUsername(username);
            utente.setPassword(password);
            utente.setSesso(sesso);
            utente.setEmail(email);
            utentedao.doSave(utente);
            utente = utentedao.doRetrieveByUsernamePassword(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("utente", utente);
            if (utente != null) {
                response.sendRedirect("profilo");
            } else {
                throw new MyServletException("errore");
            }
        } else {
            error = "Sono stati trovati i seguenti errori: <br><br>" + error;
            request.setAttribute("fomrerror", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Pagine/registrazione.jsp");
            dispatcher.forward(request, response);
        }
    }
}
