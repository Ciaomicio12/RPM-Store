package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VinileDAO vinileDAO = new VinileDAO();
        try {
            List<Vinile> vinili = vinileDAO.doRetrieveAll(0, 3);
            request.setAttribute("Vinili", vinili);
            request.getRequestDispatcher("/WEB-INF/Pagine/Home.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
