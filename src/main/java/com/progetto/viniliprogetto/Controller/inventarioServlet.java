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
import java.util.Collections;
import java.util.List;

@WebServlet("/admin/inventario")
public class inventarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //da controllare
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == true) {
            VinileDAO vinileDAO = new VinileDAO();
            List<Vinile> vinili = vinileDAO.doRetrieveAll();
            Collections.shuffle(vinili);
            request.setAttribute("vinili", vinili);
            request.getRequestDispatcher("/Pagine/Admin/inventario.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti amministratori");
        }
    }
}