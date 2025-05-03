package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.GenereDAO;
import com.progetto.viniliprogetto.Model.Genere;
import com.progetto.viniliprogetto.Model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deletegenere")
public class DeleteGenereServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == true) {
            String id = request.getParameter("id");
            GenereDAO dao = new GenereDAO();
            int idnum = -1;
            try {
                idnum = Integer.parseInt(id);
            } catch (NumberFormatException ex) {
                throw new MyServletException("Il genere da eliminare non esiste");
            }
            if (dao.doRetriveById(idnum) != null) {
                dao.doDelete(idnum);
                List<Genere> genere = dao.doRetrieveAll();
                getServletContext().setAttribute("genere", genere);
                request.getRequestDispatcher("WEB-INF/jsp/deletegenere.jsp").forward(request, response);
            } else {
                throw new MyServletException("Il genere da eliminare non esiste");
            }
        } else {
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}
