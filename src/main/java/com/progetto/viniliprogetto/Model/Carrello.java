package com.progetto.viniliprogetto.Model;

import java.util.ArrayList;

public class Carrello {
    private ArrayList<VinileInCarrello> viniliInCarrello = new ArrayList<>();

    public void aggiungiVinile(Vinile vinile) {
        VinileInCarrello vic = cercaPerEan(vinile.getEan());
        if (vic == null) {
            VinileInCarrello vinInCarrello = new VinileInCarrello();
            vinInCarrello.setVinile(vinile);
            vinInCarrello.setQuantita(1);
            viniliInCarrello.add(vinInCarrello);
        } else {
            vic.setQuantita(vic.getQuantita() + 1);
        }
    }

    public int getQuantita() {
        int i = 0;
        for (VinileInCarrello vic : viniliInCarrello) i++;
        return i;
    }

    public void rimuoviVinile(Vinile vinile) {
        VinileInCarrello vic = cercaPerEan(vinile.getEan());
        if (vic != null) viniliInCarrello.remove(vic);
    }

    private VinileInCarrello cercaPerEan(String ean) {
        for (VinileInCarrello vic : viniliInCarrello) {
            if (vic.getVinile().getEan().equals(ean)) return vic;
        }
        return null;
    }

    public ArrayList<VinileInCarrello> getViniliInCarrello() {
        return viniliInCarrello;
    }

    public void modificaQuantita(Vinile vinile, int quant) {
        VinileInCarrello vic = cercaPerEan(vinile.getEan());
        if (vic != null) vic.setQuantita(quant);
    }

    public void setViniliInCarrello(ArrayList<VinileInCarrello> viniliInCarrello) {
        this.viniliInCarrello = viniliInCarrello;
    }

    public float getTotale() {
        int sum = 0;
        for (VinileInCarrello v : viniliInCarrello) {
            sum += (v.getVinile().getPrezzo() * v.getQuantita());
        }
        return sum;
    }

    public void svuota() {
        this.viniliInCarrello = new ArrayList<>();
    }
    /*
    public int getTotprodotti() {
        aggiornaTotProdotti();
        return totprodotti;
    }

    public ArrayList<Vinile> getVinili() {
        return vinili;
    }

    public void aggiornaTotProdotti() {
        totprodotti = 0;
        for (Vinile v : vinili) {
            totprodotti += v.getQuantitaCarrello();
        }
    }

    public int getTotale() {
        int sum = 0;
        for (Vinile v : vinili) {
            sum += (v.getPrezzo() * v.getQuantitaCarrello());
        }
        return sum;
    }

    public int getTotaleNetto() {
        return getTotale() - (getTotale() * 22 / 100);
    }

    public int getIva() {
        return getTotale() - getTotaleNetto();
    }

    public int getCostoSpedizione() {
        if (getTotale() > 10000)
            return 0;
        if (getTotale() > 5000)
            return 750;
        if (getTotale() == 0) {
            return 0;
        } else
            return 1500;
    }

    public int getTotaleLordo() {
        return getTotale() + getCostoSpedizione();
    }

    public String convertiEuro(int prezzo) {
        int x = prezzo / 100;
        int y = prezzo % 100;
        if (y == 0) {
            return x + ",00";
        }
        return x + "," + y;
    }
    */
}