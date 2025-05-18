package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.GenereDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Genere;
import com.progetto.viniliprogetto.Model.Utente;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/aggiungivinile")

public class AggiungiVinileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == true) {
            GenereDAO generedao = new GenereDAO();
            String ean = request.getParameter("ean");
            Vinile vinile = null;
            List<Genere> list = generedao.doRetrieveAll();
            if (ean != null) {
                request.setAttribute("titolo", "Modifica vinile");
                VinileDAO viniledao = new VinileDAO();
                vinile = viniledao.doRetrieveByEan(ean);
                if (vinile == null) {
                    throw new MyServletException("Vinile non trovato");
                }
                request.setAttribute("vinile", vinile);
                int f = list.size();
                Iterator<Genere> iter1 = list.iterator();
                Genere current = null;
                while (iter1.hasNext()) {//iterazione sul genere da rimuovere
                    current = iter1.next();
                    for (int i = 0; i < vinile.getGeneri().size(); i++) {
                        if (current.getId() == vinile.getGeneri().get(i).getId()) {
                            iter1.remove();
                            break;
                        }
                    }
                }
                request.setAttribute("checked", vinile.getGeneri());
            } else {
                request.setAttribute("titolo", "Aggiugni vinile");
            }
            request.setAttribute("genere", list);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Pagine/Admin/inventario.jsp");
            dispatcher.forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
