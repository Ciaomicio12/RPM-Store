package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.OrdineDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Ordine;
import com.progetto.viniliprogetto.Model.Utente;
import com.progetto.viniliprogetto.Model.Vinile;
import com.progetto.viniliprogetto.Model.VinileInOrdine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/dettagliordine")
public class DettagliOrdineServlet extends HttpServlet {

    public static final String AZIONE_ANNULLA = "annulla";
    public static final String AZIONE_SPEDISCI = "spedisci";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idordineStr = request.getParameter("ordine");
        String azione = request.getParameter("azione");
        if ((azione == null || idordineStr == null))
            throw new MyServletException("Errore nei parametri", 400);
        String nuovoStato;
        if (azione.equals(AZIONE_ANNULLA))
            nuovoStato = "A";
        else if (azione.equals(AZIONE_SPEDISCI))
            nuovoStato = "S";
        else
            throw new MyServletException("Azione non valida", 400);
        int idordine;
        try {
            idordine = Integer.parseInt(idordineStr);
        } catch (NumberFormatException e) {
            throw new MyServletException("id ordine non valido", 400);
        }
        OrdineDAO dao = new OrdineDAO();
        Ordine ordine = dao.doRetriveById(idordine);
        // impostare lo stato (controllare che il valore passato sia giusto)
        if (ordine == null) throw new MyServletException("Errore parametro ordine non trovato", 404);
        // Chiamare doCambiaStato passando solo l'ordine aggiornato
        if (azione.equals(AZIONE_ANNULLA)) {
            VinileDAO dao2 = new VinileDAO();
            for (VinileInOrdine vio : ordine.getViniliInOrdineList()) {
                Vinile v = vio.getVinile();
                v.setNumeroDisponibili(v.getNumeroDisponibili() + vio.getQuantita());
                dao2.doUpdate(v);
            }
        }
        ordine.setStato(nuovoStato);
        dao.doCambiaStatoOrdine(ordine);
        response.sendRedirect(request.getContextPath() + "/user/dettagliordine?id=" + idordine);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente != null && utente.isAdmin() == false) {
            request.setAttribute("utente", utente);
            String id = request.getParameter("id");
            OrdineDAO dao = new OrdineDAO();
            Ordine ordine = dao.doRetriveById(Integer.parseInt(id));
            if (ordine == null) throw new MyServletException("Ordine non trovato");
            request.setAttribute("ordine", ordine);
            request.getRequestDispatcher("/WEB-INF/Pagine/ordine-in-dettaglio.jsp").forward(request, response);
        } else if (utente != null && utente.isAdmin() == true) {
            request.setAttribute("utente", utente);
            String id = request.getParameter("id");
            OrdineDAO dao = new OrdineDAO();
            Ordine ordine = dao.doRetriveById(Integer.parseInt(id));
            if (ordine == null) throw new MyServletException("Ordine non trovato");
            request.setAttribute("ordine", ordine);
            request.getRequestDispatcher("/WEB-INF/Pagine/ordine-in-dettaglio.jsp").forward(request, response);
        } else {
            throw new MyServletException("Sezione dedicata agli utenti registrati");
        }
    }
}
