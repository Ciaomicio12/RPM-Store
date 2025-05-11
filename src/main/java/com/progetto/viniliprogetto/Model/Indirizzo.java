package com.progetto.viniliprogetto.Model;

public class Indirizzo {
    private int id;
    private String strada;
    private String citta;
    private String cap;
    private String numero_civico;
    private String telefono;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getNumero_civico() {
        return numero_civico;
    }

    public void setNumero_civico(String numero_civico) {
        this.numero_civico = numero_civico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
