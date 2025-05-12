package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changepassword")
public class ChangePasswordAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        String vecchiapassword = request.getParameter("id");
        String nuovapassword = request.getParameter("id1");
        String passwordconfirm = request.getParameter("id2");
        if (user != null) {
            String error = "Sono stati trovati i seguenti errori:\n\n";
            UtenteDAO dao = new UtenteDAO();
            response.setContentType("application/text");
            if (dao.checkPassword(user.getUsername(), vecchiapassword) == false) {
                error = error + "La password corrente non é corretta!\n";
            }
            if (nuovapassword.length() == 0) {
                error = error + "La password non può essere vuota\n";
            } else if (!nuovapassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")) {
                error = error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32\n&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola\n&nbsp;&nbsp;&nbsp;Deve contenere un numero\n";
            } else if (!nuovapassword.equals(passwordconfirm)) {
                error = error + "La password e la conferma password sono diverse<br>";
            }
            if (error.length() > 44) {
                response.getWriter().append(error);
            } else {
                dao.doUpdatePassword(user.getUsername(), nuovapassword);
                response.getWriter().append("true");
            }
        }
    }
}
