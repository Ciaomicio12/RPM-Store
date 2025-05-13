package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Utente;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/inventario")
public class inventarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //da controllare
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == true) {
            VinileDAO vinilidao = new VinileDAO();
            ArrayList<Vinile> vinili = (ArrayList<Vinile>) vinilidao.doRetrieveAll(0, 100);
            request.setAttribute("vinili", vinili);
            request.getRequestDispatcher("WEB-INF/jsp/inventario.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti amministratori");
        }
    }
}