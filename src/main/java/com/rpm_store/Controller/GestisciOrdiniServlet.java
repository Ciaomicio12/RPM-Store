package com.rpm_store.Controller;

import com.rpm_store.DAO.OrdineDAO;
import com.rpm_store.Model.Ordine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/gestisciordini")
public class GestisciOrdiniServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdineDAO odao = new OrdineDAO();
        ArrayList<Ordine> li = odao.doRetrieveAll();
        request.setAttribute("ordini", li);
        request.getRequestDispatcher("/WEB-INF/Pagine/Admin/lista-ordini-amministratore.jsp").forward(request, response);
    }
}

