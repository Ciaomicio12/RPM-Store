package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/cancellavinile")
public class CancellaVinileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ean = request.getParameter("ean");
        if (ean == null) throw new MyServletException("Ean non presente nella richiesta", 400);
        VinileDAO dao = new VinileDAO();
        dao.doDelete(ean);
        response.sendRedirect(request.getContextPath() + "/admin/inventario");
    }
}
