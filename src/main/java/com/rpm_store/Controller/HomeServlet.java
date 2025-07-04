package com.rpm_store.Controller;

import com.rpm_store.DAO.VinileDAO;
import com.rpm_store.Model.Vinile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

//questo pezzo di codice funziona bene :)
@WebServlet(name = "HomeServlet", urlPatterns = "", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VinileDAO vinileDAO = new VinileDAO();
        List<Vinile> vinili = vinileDAO.doRetrieveAllRandomly(10);
        Collections.shuffle(vinili);
        request.setAttribute("vinili", vinili);
        request.getRequestDispatcher("/WEB-INF/Pagine/home.jsp").forward(request, response);
    }
}
