package com.progetto.viniliprogetto.Model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Ordine {
    private String oraordine;
    private int id;
    private float totale;
    private Utente utente;
    private String stato;
    private Indirizzo indirizzo;
    private List<VinileInOrdine> viniliInOrdineList = new ArrayList<>();

    public List<VinileInOrdine> getViniliInOrdineList() {
        return viniliInOrdineList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getData() {
        long unixSeconds = Long.parseLong(oraordine);
        Date date = new java.util.Date(unixSeconds);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String getOraordine() {
        return oraordine;
    }

    public void setOraordine(String oraordine) {
        this.oraordine = oraordine;
    }

    public void setViniliInOrdineList(List<VinileInOrdine> viniliInOrdineList) {
        this.viniliInOrdineList = viniliInOrdineList;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public String getTotaleEuro() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return nf.format(((float) totale / 100));
    }

    public int getTotaleVinili() {
        int totale = 0;
        for (VinileInOrdine vinileInOrdine : viniliInOrdineList) {
            totale += vinileInOrdine.getQuantita();
        }
        return totale;
    }

    public String convertiEuro(int prezzo) {
        int x = prezzo / 100;
        int y = prezzo % 100;
        if (y == 0) {
            return x + ",00";
        }
        return x + "," + y;
    }

    public void aggiungiVinile(Vinile vinile, int quantita) {
        VinileInOrdine vinileInOrdine = new VinileInOrdine();
        vinileInOrdine.setVinile(vinile);
        vinileInOrdine.setPrezzo(vinile.getPrezzo());
        vinileInOrdine.setQuantita(quantita);
        viniliInOrdineList.add(vinileInOrdine);
    }

    public String getStatoStringa() {
        if (this.stato.equals("P"))
            return "Pagato";
        else if (this.stato.equals("S"))
            return "Spedito";
        return "Annullato";
    }

    public void aggiungiVinile(VinileInOrdine vio) {
        viniliInOrdineList.add(vio);
    }
}
