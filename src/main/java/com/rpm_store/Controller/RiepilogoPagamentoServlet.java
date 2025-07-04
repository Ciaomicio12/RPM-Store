package com.rpm_store.Controller;

import com.rpm_store.DAO.OrdineDAO;
import com.rpm_store.DAO.VinileDAO;
// import com.progetto.viniliprogetto.Model.*;
import com.rpm_store.Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/cliente/riepilogopagamento")
public class RiepilogoPagamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null)
            throw new MyServletException("Utente non trovato", 404);
        request.setAttribute("utente", utente);
        request.getRequestDispatcher("/WEB-INF/Pagine/Cliente/riepilogo-pagamento.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Ordine ordine = new Ordine();
            Utente utente = (Utente) request.getSession().getAttribute("utente");
            Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
            ordine.setStato("P");
            ordine.setOraordine("" + new Date().getTime());
            ordine.setUtente(utente);
            Indirizzo indirizzo = ProcessaIndirizzo.getIndirizzoFromRequest(request);
            ordine.setIndirizzo(indirizzo);
            float totale = 0;
            VinileDAO dao2 = new VinileDAO();
            for (VinileInCarrello vic : carrello.getViniliInCarrello()) {
                Vinile v = vic.getVinile();
                v.setNumeroDisponibili(v.getNumeroDisponibili() - vic.getQuantita());
                dao2.doUpdate(v);
                ordine.aggiungiVinile(vic.getVinile(), vic.getQuantita());
                totale += vic.getVinile().getPrezzo() * vic.getQuantita();
            }
            ordine.setTotale(totale);
            OrdineDAO dao = new OrdineDAO();
            dao.doSave(ordine);
            // svuota carrello
            carrello.svuota();
            response.sendRedirect(request.getContextPath() + "/cliente/ordini");
        } catch (Exception e) {
            throw new MyServletException(e.getMessage(), 400);
        }
    }
}
