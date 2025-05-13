package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.GenereDAO;
import com.progetto.viniliprogetto.Model.Genere;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/checknomecategoria")
public class VerificaGenereServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("titolo");
        GenereDAO generedao = new GenereDAO();
        ArrayList<Genere> genere = generedao.doRetriveByNome(nome);
        if (genere == null) {
            response.setContentType("application/text");
            response.getWriter().append("false");
        } else {
            response.getWriter().append("true");
        }
    }
}
