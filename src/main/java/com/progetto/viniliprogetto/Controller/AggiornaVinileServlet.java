package com.progetto.viniliprogetto.Controller;

import com.progetto.viniliprogetto.DAO.GenereDAO;
import com.progetto.viniliprogetto.DAO.VinileDAO;
import com.progetto.viniliprogetto.Model.Genere;
import com.progetto.viniliprogetto.Model.Vinile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

@MultipartConfig
@WebServlet("/admin/aggiornamodifiche")
public class AggiornaVinileServlet extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "img/Cover";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ean = (String) request.getParameter("ean");
        if (ean == null) {
            throw new MyServletException("Manca ean", 400);
        } else {
            VinileDAO vinileDAO = new VinileDAO();
            Vinile vinile = vinileDAO.doRetrieveByEan(ean);
            if (vinile == null) {
                throw new MyServletException("Errore vinile non trovato", 404);
            }
            request.setAttribute("vinile", vinile);
            String titolo = request.getParameter("titolo");
            String autore = request.getParameter("autore");
            String numeroDisponibili = request.getParameter("numerodisponibile");
            String annoPubblicazione = request.getParameter("anno");
            String prezzo = request.getParameter("prezzo");

            int nDisponibili = -1, aPub = -1;
            float prezzoint = -1;
            VinileDAO vdao = new VinileDAO();

            if (titolo.length() == 0) {
                throw new MyServletException("il titolo non può essere vuoto", 400);
            }
            if (autore.length() == 0) {
                throw new MyServletException("il campo autore non può essere vuoto", 400);
            }
            if (prezzo.length() == 0) {
                throw new MyServletException("il campo prezzo non può essere vuoto", 400);
            }
            try {
                prezzoint = Float.parseFloat(prezzo);
            } catch (NumberFormatException er) {
                throw new MyServletException("Il campo prezzo deve contenere solo numeri", 400);

            }
            if (numeroDisponibili == null) {
                numeroDisponibili = "0";
            }
            if (numeroDisponibili != null) {
                try {
                    nDisponibili = Integer.parseInt(numeroDisponibili);
                } catch (NumberFormatException er) {
                    throw new MyServletException("il campo numeri disponibili, deve contenre solo numeri ", 400);
                }
            }
            if (ean.length() == 0) {
                throw new MyServletException("il campo ean non può essere null", 400);
            }

            if (Pattern.matches("[a-zA-Z]+", ean) == false && ean.length() == 13) {
                throw new MyServletException("il campo ean può contenre solo numeri e deve essere da 13 carratteri", 400);
            }

            if (annoPubblicazione.length() == 0) {
                throw new MyServletException("il campo anno non può essere vuoto", 400);
            }
            try {
                Date date = new Date();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                aPub = Integer.parseInt(annoPubblicazione);
                if (aPub > year) {
                    throw new MyServletException("L'anno inserito da te non é valido, l'anno deve essere inferiore alla date attuale", 400);
                }
            } catch (NumberFormatException er) {
                throw new MyServletException("Il campo anno pubblicazione deve contenere solo numeri", 400);

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
                    throw new MyServletException("Il campo genere non é valido", 400);
                }
                if (listCat.get(i).getId() == id) {
                    listCatform.add(listCat.get(i));
                }
            }
            Vinile v = null;
            v.setTitolo(titolo);
            v.setAutore(autore);
            v.setNumeroDisponibili(nDisponibili);
            v.setAnnoPubblicazione(aPub);
            v.setPrezzo(prezzoint);
            v.setGeneri(listCatform);
            Part filePart = request.getPart("cover");
            if (filePart.getSize() == 0) {
                throw new MyServletException("Non hai inserito alcuna copertina", 400);
            } else if (filePart.getContentType().endsWith("jpg") == false && filePart.getContentType().endsWith("jpeg") == false && filePart.getContentType().endsWith("png") == false) {
                throw new MyServletException("La copertina non ha un estensione valida", 400);
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
            response.sendRedirect(request.getContextPath().concat("/vinile?ean=").concat(v.getEan()));
        }
    }
}
