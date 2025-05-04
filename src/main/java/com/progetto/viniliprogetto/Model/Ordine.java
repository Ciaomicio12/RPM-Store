package com.progetto.viniliprogetto.Model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Ordine {
    private String oraordine;
    private String id;
    private int totale;
    private Utente utente;

    private ArrayList<VinileInOrdine> vinili = new ArrayList<>();

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getData() {
        long unixSeconds = Long.parseLong(oraordine);
        Date date = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public String getOra() {
        long unixSeconds = Long.parseLong(oraordine);
        Date date = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
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

    public ArrayList<VinileInOrdine> getVinili() {
        return vinili;
    }

    public void addVinile(Vinile vinile) {
        vinili.add(vinile);
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public String getTotaleEuro() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return nf.format(((float) totale / 100));
    }

    public String convertiEuro(int prezzo) {
        int x = prezzo / 100;
        int y = prezzo % 100;
        if (y == 0) {
            return x + ",00";
        }
        return x + "," + y;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public void aggiungiVinile(Vinile vinile, int quantita) {
        VinileInOrdine vinileInOrdine = new VinileInOrdine();
        vinileInOrdine.setVinile(vinile);
        vinileInOrdine.setPrezzo(vinile.getPrezzo());
        vinileInOrdine.setQuantita(quantita);
        vinileInOrdine.setOrdine(this);
        vinili.add(vinileInOrdine);
    }

    public ArrayList<VinileInOrdine> getVinile() {
        return vinili;
    }
}
