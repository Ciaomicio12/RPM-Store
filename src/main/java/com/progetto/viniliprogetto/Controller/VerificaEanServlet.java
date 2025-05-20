package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/verificaean")
public class VerificaEanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ean = request.getParameter("ean");
        if (ean != null) {
            VinileDAO viniledao = new VinileDAO();
            response.setContentType("application/text");
            if (viniledao.doRetrieveByEan(ean) == null) {
                response.getWriter().append("ok");
            } else {
                response.getWriter().append("no");
            }
        } else {
            throw new MyServletException("Errore");
        }
    }
}
