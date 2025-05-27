package com.progetto.viniliprogetto.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Vinile {
    private String ean;
    private int annoPubblicazione;
    private float prezzo;
    private int numeroDisponibili;
    private String autore;
    private String titolo;
    private String copertina;
    private String genereString = "";
    private List<Genere> generi = new ArrayList<>();

    public String getEan() {
        return ean;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setCopertina(String Copertina) {
        this.copertina = Copertina;
    }

    public List<Genere> getGeneri() {
        return generi;
    }

    public void setGeneri(List<Genere> generi) {
        this.generi = generi;
    }

    public String getGenereString() {
        List<String> generi = new ArrayList<>();
        this.generi.forEach(genere1 -> generi.add(genere1.getNome()));
        return String.join(",", generi);
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getPrezzoString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String prezzoFormattato = decimalFormat.format(prezzo);
        return prezzoFormattato;
    }

    public int getNumeroDisponibili() {
        return numeroDisponibili;
    }

    public void setNumeroDisponibili(int numeroDisponibili) {
        this.numeroDisponibili = numeroDisponibili;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
}
