package com.progetto.viniliprogetto.Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

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
                        Vinile temp = VinileDAO.doRetrieveByEan(vic.getVinile().getEan());
                        if (temp == null) {
                            carrello.rimuoviVinile(vic.getVinile());
                        } else if (temp.equals(vic.getVinile()) == false) {
                            quantita = vic.getQuantita();
                            carrello.rimuoviVinile(vic.getVinile());
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
