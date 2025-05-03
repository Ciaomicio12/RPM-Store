package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Model.Categoria;
import com.progetto.viniliprogetto.Model.Vinile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VinileDAO extends Vinile {

    private static ArrayList<Categoria> getCategorie(Connection con, String isbn) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "SELECT categoria.id, nome, descrizione FROM categoria,vinile_categoria where vinile_categoria.id=categoria.id and isbn=?");
        ps.setString(1, isbn);
        ArrayList<Categoria> categorie = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Categoria c = new Categoria();
            c.setId(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setDescrizione(rs.getString(3));
            categorie.add(c);
        }
        return categorie;
    }

    public int countdoRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT COUNT(*) FROM libro");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vinile> doRetrieveAll(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT EAN, anno_pubblicazione,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM vinile  LIMIT ?, ?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            List<Vinile> vinile = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vinile v = new Vinile();
                v.setEan(rs.getString(1));
                v.setAnnoPubblicazione(rs.getInt(2));
                v.setPrezzo(rs.getInt(3));
                v.setNumeroDisponibili(rs.getInt(4));
                v.setDescrizione(rs.getString(5));
                v.setAutore(rs.getString(6));
                v.setTitolo(rs.getString(7));
                v.setCopertina(rs.getString(8));
                v.setCategorie(getCategorie(con, v.getEan()));
                vinile.add(v);
            }
            return vinile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vinile doRetrieveByIsbn(String ean) {
        if (ean.indexOf("-") < 0 && ean.length() > 5) {
            String a = ean.substring(0, 3);
            String b = ean.substring(3);
            ean = a + "-" + b;
        }
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM vinile WHERE ean=?");
            ps.setString(1, ean);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vinile v = new Vinile();
                v.setEan(rs.getString(1));
                v.setAnnoPubblicazione(rs.getInt(2));
                v.setPrezzo(rs.getInt(3));
                v.setNumeroDisponibili(rs.getInt(4));
                v.setDescrizione(rs.getString(5));
                v.setAutore(rs.getString(6));
                v.setTitolo(rs.getString(7));
                v.setCopertina(rs.getString(8));
                v.setCategorie(getCategorie(con, v.getEan()));
                return v;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vinile> doRetrieveByCategoria(int categoria, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT vinile.ean, anno_pubblicazione,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM vinile LEFT JOIN vinile_categoria ON vinile.ean=vinile_categoria.ean WHERE id=? LIMIT ?, ?");
            ps.setInt(1, categoria);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Vinile> vinile = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vinile v = new Vinile();
                v.setEan(rs.getString(1));
                v.setAnnoPubblicazione(rs.getInt(2));
                v.setPrezzo(rs.getInt(3));
                v.setNumeroDisponibili(rs.getInt(4));
                v.setDescrizione(rs.getString(5));
                v.setAutore(rs.getString(6));
                v.setTitolo(rs.getString(7));
                v.setCopertina(rs.getString(8));
                v.setCategorie(getCategorie(con, v.getEan()));
                vinile.add(v);
            }
            return vinile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vinile> doRetrieveByNomeOrDescrizione(String against, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM vinile WHERE MATCH(titolo, descrizione) AGAINST(?) LIMIT ?,?");
            ps.setString(1, against);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ArrayList<Vinile> vinile = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vinile v = new Vinile();
                v.setEan(rs.getString(1));
                v.setAnnoPubblicazione(rs.getInt(2));
                v.setPrezzo(rs.getInt(3));
                v.setNumeroDisponibili(rs.getInt(4));
                v.setDescrizione(rs.getString(5));
                v.setAutore(rs.getString(6));
                v.setTitolo(rs.getString(7));
                v.setCopertina(rs.getString(8));
                v.setCategorie(getCategorie(con, v.getEan()));
                vinile.add(v);
            }
            return vinile;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByCategoria(int categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT COUNT(*) FROM vinile LEFT JOIN vinile_categoria ON vinile.ean=vinile_categoria.ISBN WHERE vinile_categoria.id=?");
            ps.setInt(1, categoria);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Vinile vinile) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO vinile(anno_pubblicazione,prezzo,numero_disponibili,descrizione,autore,titolo,copertina,ean) VALUES (?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, vinile.getAnnoPubblicazione());
            ps.setInt(2, vinile.getPrezzo());
            ps.setInt(3, vinile.getNumeroDisponibili());
            ps.setString(4, vinile.getDescrizione());
            ps.setString(5, vinile.getAutore());
            ps.setString(6, vinile.getTitolo());
            ps.setString(7, vinile.getCopertina());
            ps.setString(8, vinile.getEan());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            PreparedStatement psCa = con
                    .prepareStatement("INSERT INTO vinile_categoria (ean, id) VALUES (?, ?)");
            for (Categoria c : vinile.getCategorie()) {
                psCa.setString(1, vinile.getEan());
                psCa.setInt(2, c.getId());
                psCa.addBatch();
            }
            psCa.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Vinile vinile) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE  vinile SET  anno_pubblicazione=?,prezzo=?,numero_disponibili=?,descrizione=?,autore=?,titolo=?,copertina=? WHERE ean=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, vinile.getAnnoPubblicazione());
            ps.setInt(2, vinile.getPrezzo());
            ps.setInt(3, vinile.getNumeroDisponibili());
            ps.setString(4, vinile.getDescrizione());
            ps.setString(5, vinile.getAutore());
            ps.setString(6, vinile.getTitolo());
            ps.setString(7, vinile.getCopertina());
            ps.setString(8, vinile.getEan());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

            PreparedStatement psCa2 = con
                    .prepareStatement("DELETE FROM vinile_categoria WHERE ean=?");
            psCa2.setString(1, vinile.getEan());
            psCa2.executeUpdate();

            PreparedStatement psCa = con
                    .prepareStatement("INSERT INTO vinile_categoria (ean, id) VALUES (?, ?)");
            for (Categoria c : vinile.getCategorie()) {
                psCa.setString(1, vinile.getEan());
                psCa.setInt(2, c.getId());
                psCa.addBatch();
            }
            psCa.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String isbn) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM vinile WHERE isbn=?");
            ps.setString(1, isbn);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
            PreparedStatement psCa = con.prepareStatement("DELETE FROM vinile_categoria WHERE isbn=?");
            psCa.setString(1, isbn);
            psCa.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Vinile> getListOrderBook() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM vinile where tipo=\"cartaceo\" order by acquisti desc LIMIT 0, 15");
            ArrayList<Vinile> vinile = new ArrayList<Vinile>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vinile v = new Vinile();
                v.setEan(rs.getString(1));
                v.setAnnoPubblicazione(rs.getInt(2));
                v.setPrezzo(rs.getInt(3));
                v.setNumeroDisponibili(rs.getInt(4));
                v.setDescrizione(rs.getString(5));
                v.setAutore(rs.getString(6));
                v.setTitolo(rs.getString(7));
                v.setCopertina(rs.getString(8));
                vinile.add(v);
            }
            return vinile;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
