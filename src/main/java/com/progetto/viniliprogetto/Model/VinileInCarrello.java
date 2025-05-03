package com.progetto.viniliprogetto.Model;

public class VinileInCarrello {
    private Carrello carrello;
    private Vinile vinile;
    private int quantita;

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
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
}
