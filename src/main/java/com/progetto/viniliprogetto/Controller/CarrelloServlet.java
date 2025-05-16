package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Carrello;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/carrello")
public class CarrelloServlet extends HttpServlet {

    public static final String AGGIUNGI_VINILE = "aggiungi";
    public static final String RIMUOVI_VINILE = "rimuovi";
    public static final String MODIFICA_QUANTITA = "modifica";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Pagine/carrello.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        String action = request.getParameter("action");
        if (action == null)
            throw new MyServletException("campo action non trovato", 400);
        switch (action) {
            case AGGIUNGI_VINILE:
                aggiungiVinile(request, carrello);
                break;
            case RIMUOVI_VINILE:
                break;
            case MODIFICA_QUANTITA:
                break;
            default:
                throw new MyServletException("campo action non valido: " + action, 400);
        }
        response.sendRedirect("/carrello");
    }

    private void aggiungiVinile(HttpServletRequest request, Carrello carrello) throws MyServletException {
        String ean = request.getParameter("ean");
        if (ean == null)
            throw new MyServletException("campo ean non trovato: " + ean, 400);
        VinileDAO dao = new VinileDAO();
        Vinile vinile = dao.doRetrieveByEan("ean");
        if (vinile == null)
            throw new MyServletException("ean non valido: " + ean, 404);
        if (vinile.getNumeroDisponibili() > 1)
            carrello.aggiungiVinile(vinile);
        else throw new MyServletException("vinile non disponibile: ", 400);
    }

    private void modificaQuantita(HttpServletRequest request, Carrello carrello) throws MyServletException {
        String ean = request.getParameter("ean");
        if (ean == null)
            throw new MyServletException("campo ean non trovato: " + ean, 400);
        VinileDAO dao = new VinileDAO();
        Vinile vinile = dao.doRetrieveByEan("ean");
        if (vinile == null)
            throw new MyServletException("ean non valido: " + ean, 404);
        String tmp = request.getParameter("quantita");
        if (tmp == null)
            throw new MyServletException("quantita non valida: " + tmp, 400);
        int quantita = Integer.parseInt(tmp);
        if (quantita <= 0)
            throw new MyServletException("quantita non valida: " + quantita, 400);
        if (vinile.getNumeroDisponibili() < quantita)
            throw new MyServletException("quantita maggiore del numero dei vinili disponibili: ", 400);
        carrello.modificaQuantita(vinile, quantita);
    }
}
