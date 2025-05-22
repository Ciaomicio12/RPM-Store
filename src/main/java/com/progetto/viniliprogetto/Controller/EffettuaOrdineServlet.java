package com.progetto.viniliprogetto.Controller;


import com.progetto.viniliprogetto.DAO.OrdineDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/effettuaordine")
public class EffettuaOrdineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdineDAO dao = new OrdineDAO();
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente utente = (Utente) session.getAttribute("utente");
        VinileDAO ldao = new VinileDAO();
        Vinile ltemp;
        if (utente != null && utente.isAdmin() == false) {
            if (carrello != null && carrello.getViniliInCarrello().size() > 0) {
                Ordine o = new Ordine();
                List<VinileInOrdine> vinileInOrdineList = new ArrayList<>();
                for (VinileInCarrello vinileInCarrello : carrello.getViniliInCarrello()) {
                    VinileInOrdine vinileInOrdine = new VinileInOrdine();
                    vinileInOrdine.setQuantita(vinileInCarrello.getQuantita());
                    vinileInOrdine.setVinile(vinileInCarrello.getVinile());
                    vinileInOrdine.setPrezzo(vinileInCarrello.getQuantita() * vinileInCarrello.getVinile().getPrezzo());
                    vinileInOrdineList.add(vinileInOrdine);
                }
                o.setViniliInOrdineList(vinileInOrdineList);
                o.setOraordine("" + Instant.now().getEpochSecond());
                int totale = 0;
                for (VinileInOrdine vinileInOrdine : vinileInOrdineList) {
                    totale += vinileInOrdine.getPrezzo();
                }
                o.setTotale(totale);
                for (VinileInOrdine vinileInOrdine : vinileInOrdineList) {
                    Vinile vinile = ldao.doRetrieveByEan(vinileInOrdine.getVinile().getEan());
                    if (vinile.getNumeroDisponibili() >= vinileInOrdine.getQuantita()) {
                        vinile.setNumeroDisponibili(vinile.getNumeroDisponibili() - vinileInOrdine.getQuantita());
                        ldao.doUpdate(vinile);
                    } else {
                        throw new MyServletException("la quantita eccede quella disponibile");
                    }
                }
                dao.doSave(o, utente);
                session.setAttribute("carrello", null);
                response.sendRedirect("dettagliordine?id=" + o.getId());
            } else {
                throw new MyServletException("Non hai prodotti nel carrello");
            }
        } else {
            throw new MyServletException("Non sei loggato o sei un amministratore");
        }
    }

}
