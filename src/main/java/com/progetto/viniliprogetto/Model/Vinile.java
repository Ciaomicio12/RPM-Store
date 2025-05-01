package com.progetto.viniliprogetto.Model;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Vinile {
    private String EAN;
    private String titolo;
    private String descrizione;
    private int prezzo;
    private int anno_pubblicazione;
    private int numero_disponibili;
    private String autore;
    private String path;
    private List<Categoria> categorie;
    private String sdescrizione;
    private String ssDescrizione;
    private int quantitaCarrello;
    private String categoriestring;
    private int acquisti = 0;

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public int getAnno_pubblicazione() {
        return anno_pubblicazione;
    }

    public void setAnno_pubblicazione(int anno_pubblicazione) {
        this.anno_pubblicazione = anno_pubblicazione;
    }

    public int getNumero_disponibili() {
        return numero_disponibili;
    }

    public void setNumero_disponibili(int numero_disponibili) {
        this.numero_disponibili = numero_disponibili;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    public String getSdescrizione() {
        return sdescrizione;
    }

    public void setSdescrizione(String sdescrizione) {
        this.sdescrizione = sdescrizione;
    }

    public String getSsDescrizione() {
        return ssDescrizione;
    }

    public void setSsDescrizione(String ssDescrizione) {
        this.ssDescrizione = ssDescrizione;
    }

    public int getQuantitaCarrello() {
        return quantitaCarrello;
    }

    public void setQuantitaCarrello(int quantitaCarrello) {
        this.quantitaCarrello = quantitaCarrello;
    }

    public String getCategoriestring() {
        return categoriestring;
    }

    public void setCategoriestring(String categoriestring) {
        this.categoriestring = categoriestring;
    }

    public int getAcquisti() {
        return acquisti;
    }

    public void setAcquisti(int acquisti) {
        this.acquisti = acquisti;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + EAN +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", numero_disponibili=" + numero_disponibili +
                ", descrizione='" + descrizione + '\'' +
                ", autore='" + autore + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vinile)) return false;
        Vinile vinile = (Vinile) o;
        return getPrezzo() == vinile.getPrezzo() &&
                getAnno_pubblicazione() == vinile.getAnno_pubblicazione() &&
                getNumero_disponibili() == vinile.getNumero_disponibili() &&
                getTitolo().equals(vinile.getTitolo()) &&
                getDescrizione().equals(vinile.getDescrizione()) &&
                getDescrizione().equals(vinile.getDescrizione()) &&
                getAutore().equals(vinile.getAutore()) &&
                getPath().equals(vinile.getPath());
    }

    public String getprezzoEuro() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return nf.format(((float) prezzo / 100));
    }
}
