package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ceckusername")
public class VerificaUsernameServlet extends HttpServlet {/*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            UtenteDAO dao = new UtenteDAO();
            response.setContentType("application/text");
            try {
                if (dao.doRetrieveByUsername(id) != null) {
                    response.getWriter().append("true");
                } else {
                    response.getWriter().append("no");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new MyServletException("Errore");
        }
    }*/
}
