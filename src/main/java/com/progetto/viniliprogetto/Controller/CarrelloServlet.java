package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.OrdineDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Carrello;
import com.progetto.viniliprogetto.Model.Utente;
import com.progetto.viniliprogetto.Model.Vinile;
import com.progetto.viniliprogetto.Model.VinileInCarrello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {
/*
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Utente user = (Utente) session.getAttribute("utente");
        if (user == null || (user != null && user.isAdmin() == false)) {
            VinileDAO VinileDAO = new VinileDAO();
            if (request.getParameter("id") != null) {
                if (session.getAttribute("carrello") == null) {
                    carrello = new Carrello();
                }
                Vinile vinile = VinileDAO.doRetrieveByEan(request.getParameter("id"));
                if (vinile.getNumeroDisponibili() == 0) {
                    throw new MyServletException("Il vinile da lei selezionato è terminato");
                }
                OrdineDAO dao = new OrdineDAO();
                if (user != null && user.isAdmin() == false) {
                    if (dao.ceckIfExistbyIsbnAndUserID(vinile.getEan(), user.getId())) {
                        throw new MyServletException("Il vinile da lei selezionato è già presente nei suoi ordini");
                    }
                }
                if (carrello.getVinili().size() == 0) {
                    carrello.aggiungiVinile(vinile);
                    session.setAttribute("carrello", carrello);
                    response.sendRedirect("carrello");
                } else {
                    for (VinileInCarrello vic: carrello.getVinili()) {
                        if (vic.getVinile().getEan().equals(vinile.getEan())) {
                            throw new MyServletException("Errore");
                        } else if (vic.getVinile().getEan().equals(vinile.getEan())) {
                            vic.setQuantita(carrello.getVinili().get(i).getQuantitaCarrello() + 1);
                            break;
                        } else {
                            carrello.aggiungiVinile(vinile);
                            break;
                        }
                    }

                    session.setAttribute("carrello", carrello);
                    response.sendRedirect("carrello");
                    return;
                }

            } else {
                if (carrello != null) {
                    int quantita = 0;
                    for (VinileInCarrello vic: carrello.getVinili()) {
                        Vinile temp = VinileDAO.doRetrieveByEan(carrello.getVinili().get(i).getEan());
                        if (temp == null) {
                            carrello.rimuoviVinile(carrello.getVinili().get(i));
                            i--;
                        } else if (temp.equals(carrello.getVinili().get(i)) == false) {
                            quantita = carrello.getVinili().get(i).getQuantitaCarrello();
                            carrello.rimuoviVinile(carrello.getVinili().get(i));
                            temp.setQuantitaCarrello(quantita);
                            carrello.aggiungiVinile(temp);
                        }
                    }
                    session.setAttribute("carrello", carrello);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            throw new MyServletException("Sezione dedicata agli utenti standard o non registrati");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
*/
}
