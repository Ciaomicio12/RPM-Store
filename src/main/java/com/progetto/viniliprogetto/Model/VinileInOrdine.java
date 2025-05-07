package com.progetto.viniliprogetto.Model;

public class VinileInOrdine {
    private Ordine ordine;
    private Vinile vinile;
    private int quantita;
    private int prezzo;

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Vinile getVinile() {
        return vinile;
    }

    public void setVinile(Vinile vinile) {
        this.vinile = vinile;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }
}
