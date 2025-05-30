package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkemail")
public class VerificaEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            UtenteDAO dao = new UtenteDAO();
            response.setContentType("application/text");
            if (dao.mailEsistente(id) == true) {
                response.getWriter().append("true");
            } else {
                response.getWriter().append("no");
            }
        } else {
            throw new MyServletException("Errore");
        }
    }
}
