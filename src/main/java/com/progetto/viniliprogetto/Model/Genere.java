package com.progetto.viniliprogetto.Model;

public class Genere {
    private int id;
    private String nome;

    public Genere(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String first = "" + nome.charAt(0);
        String upfirst = first.toUpperCase();
        nome = upfirst + nome.substring(1);
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Genere [id=" + id + ", nome=" + nome + "]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Genere other = (Genere) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}
