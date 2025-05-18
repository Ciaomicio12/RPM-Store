package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.GenereDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Genere;
import com.progetto.viniliprogetto.Model.Utente;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;


@MultipartConfig
@WebServlet("/caricavinile")
public class CaricaVinileServlet extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "img";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("utente");
        if (user != null && user.isAdmin() == true) {
            String titolo = request.getParameter("titolo");
            String autore = request.getParameter("autore");
            String numeroDisponibili = request.getParameter("numerodisponibile");
            String ean = request.getParameter("ean");
            String annoPubblicazione = request.getParameter("anno");
            String prezzo = request.getParameter("prezzo");
            String edit = request.getParameter("edit");
            if (ean == null) {
                ean = edit;
            }
            String errore = "";
            int nDisponibili = -1, aPub = -1;
            int prezzoint = -1;
            VinileDAO vdao = new VinileDAO();

            if (titolo.length() == 0) {
                errore = errore + "Il campo titolo non può essere vuoto<br>";
            }
            if (autore.length() == 0) {
                errore = errore + "Il campo autore non può essere vuoto<br>";
            }
            if (prezzo.length() == 0) {
                errore = errore + "Il campo prezzo non può essere vuoto<br>";
            }
            try {
                prezzo = prezzo.replace("-", "");
                String[] array = new String[0];
                if (prezzo.contains(",") || prezzo.contains(".")) {
                    if (prezzo.contains(",")) {
                        array = prezzo.split(",");
                    } else if (prezzo.contains(".")) {
                        array = prezzo.split(Pattern.quote("."));
                    }
                    if (array.length == 2) {
                        try {
                            prezzoint = Integer.parseInt(array[0] + array[1].substring(0, 2));
                        } catch (StringIndexOutOfBoundsException er) {
                            prezzoint = Integer.parseInt(array[0] + array[1]);
                        }

                    } else {
                        errore = errore + "Il prezzo non é valido";
                    }
                } else {
                    prezzoint = Integer.parseInt(prezzo);
                    prezzoint = prezzoint * 100;
                }
            } catch (NumberFormatException er) {
                errore = errore + "Il campo prezzo deve contenere solo numeri<br>";
            }
            if (numeroDisponibili == null) {
                numeroDisponibili = "0";
            }
            if (numeroDisponibili != null) {
                try {
                    nDisponibili = Integer.parseInt(numeroDisponibili);
                } catch (NumberFormatException er) {
                    errore = errore + "Il campo numero disponibili deve contenere solo numeri<br>";
                }
            }
            if (ean.length() == 0) {
                errore = errore + "Il campo isbn non può essere vuoto<br>";
            }

            if (edit != null && edit.equals(ean) == false) {
                errore = errore + "Il campo isbn non può essere modificato<br>";
            }
            if (ean.matches("/^[0-9]{13}$/g")) {
                errore = errore + "Il campo isbn deve contenere solo numeri e deve essere lungo 13 caratteri<br>";
            }

            if (vdao.doRetrieveByEan(ean) != null && edit == null) {
                errore = errore + "Esiste già un libro con questo isbn<br>";
            }
            if (annoPubblicazione.length() == 0) {
                errore = errore + "Il campo anno pubblicazioni non può essere vuoto<br>";
            }
            try {
                Date date = new Date();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                aPub = Integer.parseInt(annoPubblicazione);
                if (aPub > year) {
                    errore = errore + "L'anno inserito da te non é valido, l'anno deve essere inferiore alla date attuale<br>";
                }
            } catch (NumberFormatException er) {
                errore = errore + "Il campo anno pubblicazione deve contenere solo numeri<br>";
            }
            GenereDAO cdao = new GenereDAO();
            List<Genere> listCat = cdao.doRetrieveAll();
            ArrayList<Genere> listCatform = new ArrayList<Genere>();
            int id = -1;
            for (int i = 0; i < listCat.size(); i++) {
                try {
                    String f = request.getParameter(String.valueOf(listCat.get(i).getId()));
                    if (f != null) id = Integer.parseInt(f);
                } catch (NumberFormatException er) {
                    errore = errore + "Il campo categoria non é valido<br>";
                    break;
                }
                if (listCat.get(i).getId() == id) {
                    listCatform.add(listCat.get(i));
                }
            }
            Vinile v = null;
            if (edit != null) {
                v = vdao.doRetrieveByEan(ean);
            } else {
                v = new Vinile();
                v.setEan(ean);
            }
            v.setTitolo(titolo);
            v.setAutore(autore);
            v.setNumeroDisponibili(nDisponibili);
            v.setAnnoPubblicazione(aPub);
            v.setPrezzo(prezzoint);
            v.setGeneri(listCatform);
            Part filePart = request.getPart("img");
            if (filePart.getSize() == 0 && edit == null) {
                errore = errore + "Non hai inserito alcuna copertina<br>";
            } else if (edit == null && filePart.getContentType().endsWith("jpg") == false && filePart.getContentType().endsWith("jpeg") == false && filePart.getContentType().endsWith("png") == false) {
                errore = errore + "La copertina non ha un estensione valida<br>";
            }

            if (errore.length() > 0) {
                throw new MyServletException("Sono stati trovati i seguenti errori:<br><br>" + errore);
            }

            if (filePart.getSize() != 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                for (int i = 2; Files.exists(pathDestinazione); i++) {
                    fileName = i + "_" + fileName;
                    destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                    pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                }
                InputStream fileInputStream = filePart.getInputStream();
                Files.createDirectories(pathDestinazione.getParent());
                Files.copy(fileInputStream, pathDestinazione);
                v.setCopertina(fileName);
            }

            if (edit == null) {
                vdao.doSave(v);
            } else if (edit != null) {
                vdao.doUpdate(v);
            }
            response.sendRedirect("vinile?id=" + v.getEan());
        } else {
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        throw new MyServletException("Metodo non permesso");
    }

}
