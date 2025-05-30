package com.progetto.viniliprogetto.Model;

public class Indirizzo {
    private int id = -1;
    private String strada;
    private String citta;
    private String cap;
    private String numeroCivico;
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

    public boolean equals(Indirizzo obj) {
        if (!obj.getStrada().equals(strada)) return false;
        if (!obj.getCitta().equals(citta)) return false;
        if (!obj.getCap().equals(cap)) return false;
        if (!obj.getTelefono().equals(telefono)) return false;
        if (!obj.getNumeroCivico().equals(numeroCivico)) return false;
        return true;

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

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
