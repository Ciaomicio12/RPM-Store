package com.rpm_store.Controller;

import com.rpm_store.DAO.VinileDAO;
import com.rpm_store.Model.Carrello;
import com.rpm_store.Model.Vinile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cliente/carrello")
public class CarrelloServlet extends HttpServlet {

    public static final String AGGIUNGI_VINILE = "aggiungi";
    public static final String RIMUOVI_VINILE = "rimuovi";
    public static final String MODIFICA_QUANTITA = "modifica";


    private Carrello getCarrello(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }
        return carrello;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello carrello = getCarrello(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Pagine/carrello.jsp");
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
                carrello = getCarrello(request);
                aggiungiVinile(request, response, carrello);
                break;
            case RIMUOVI_VINILE:
                rimuoviVinile(request, response, carrello);
                break;
            case MODIFICA_QUANTITA:
                modificaQuantita(request, response, carrello);
                break;
            default:
                throw new MyServletException("campo action non valido: " + action, 400);
        }
    }

    public void rimuoviVinile(HttpServletRequest request, HttpServletResponse response, Carrello carrello) throws MyServletException, IOException {
        String ean = request.getParameter("ean");
        if (ean == null) {
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \"campo ean non trovato: " + ean + "\"}");
            return;
        }
        VinileDAO dao = new VinileDAO();
        Vinile vinile = dao.doRetrieveByEan(ean);
        if (vinile == null) {
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \"ean non valido: " + ean + "\"}");
            return;
        }
        carrello.rimuoviVinile(vinile);
        response.getWriter().println("{\"status\": \"OK\", \"totale\": " + carrello.getTotale() + ", \"quantita\": " + carrello.getQuantita() + "}");
    }

    private void aggiungiVinile(HttpServletRequest request, HttpServletResponse response, Carrello carrello) throws MyServletException, IOException {
        String ean = request.getParameter("ean");
        if (ean == null)
            throw new MyServletException("campo ean non trovato: " + ean, 400);
        VinileDAO dao = new VinileDAO();
        Vinile vinile = dao.doRetrieveByEan(ean);
        if (vinile == null)
            throw new MyServletException("ean non valido: " + ean, 404);
        if (vinile.getNumeroDisponibili() > 1)
            carrello.aggiungiVinile(vinile);
        else throw new MyServletException("vinile non disponibile: ", 400);
        response.sendRedirect(request.getContextPath() + "/cliente/carrello");
    }

    private void modificaQuantita(HttpServletRequest request, HttpServletResponse response, Carrello carrello) throws MyServletException, IOException {
        String ean = request.getParameter("ean");
        if (ean == null)
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \" campo ean non trovato: " + ean + "\"}");
        VinileDAO dao = new VinileDAO();
        Vinile vinile = dao.doRetrieveByEan(ean);
        if (vinile == null)
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \" ean non valido: " + ean + "\"}");
        String tmp = request.getParameter("quantita");
        if (tmp == null)
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \" quantita non valida: " + tmp + "\"}");
        int quantita = Integer.parseInt(tmp);
        if (quantita <= 0)
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \" quantita non valida: " + quantita + "\"}");
        if (vinile.getNumeroDisponibili() < quantita)
            response.getWriter().println("{\"status\": \"ERROR\", \"messaggio\": \" quantita maggiore del numero dei vinili disponibili: }");
        carrello.modificaQuantita(vinile, quantita);
        response.getWriter().println("{\"status\": \"OK\", \"totale\": " + carrello.getTotale() + ", \"quantita\": " + carrello.getQuantita() + "}");
    }
}
