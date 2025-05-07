package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Controller.MyServletException;
import com.progetto.viniliprogetto.Model.Ordine;
import com.progetto.viniliprogetto.Model.Utente;
import com.progetto.viniliprogetto.Model.Vinile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {

    public void doSave(Ordine ordini, Utente utente) throws MyServletException {
        try (Connection con = ConPool.getConnection()) {
            String sql = "Insert into ordini (oradiordine,id_utente,ean,anno_pubblicazione,prezzo,autore,titolo,copertina,quantita,totale) values";
            for (int i = 0; i < ordini.getVinile().size(); i++) {
                Vinile v = ordini.getVinile().get(i);

                if (i != ordini.getVinile().size() - 1) {
                    sql = sql + ",";
                }
            }
            sql = sql + ";";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private Ordine creaOrdine(ResultSet rs) throws SQLException {
        Ordine o = new Ordine();
        o.setid(rs.getString(1));
        o.setOraordine(rs.getString(2));
        o.setTotale(rs.getInt(4));
        Utente utente = new Utente();
        utente.setId(rs.getInt("idutente"));
        utente.setUsername(rs.getString("Username"));
        o.setUtente(utente);
        return o;
    }

    public ArrayList<Ordine> doRetrieveAll(int limit, int offset) throws SQLException {
        ArrayList<Ordine> ordini = new ArrayList<Ordine>();
        Connection con = ConPool.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT O.oradiordine AS ora,O.totale,U.username AS Username, O.id, U.id AS idutente " +
                "FROM ordine O " +
                "  INNER JOIN utente U on o.id_utente = u-id " +
                "where O.id_utente=U.id order by O.oradiordine DASH LIMIT ?,?");
        ps.setInt(1, offset);
        ps.setInt(2, limit);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ordini.add(creaOrdine(rs));
        }
        return ordini;
    }


    public ArrayList<Ordine> doRetrieveByUserId(int idutente) {
        ArrayList<Ordine> o = new ArrayList<Ordine>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT oradiordine,quantita,totale,ean, anno_pubblicazione,prezzo,descrizione,autore,titolo,copertina,quantita " +
                            "FROM ordini " +
                            "WHERE id_utente=?  " +
                            "order by oradiordine");
            ps.setInt(1, idutente);
            ResultSet rs = ps.executeQuery();
            String next = "0", prec = "0";
            int i = 0;
            Ordine temp = null;
            while (rs.next()) {
                next = rs.getString(1);
                if (prec.equals(next)) {
                    Vinile ltemp = new Vinile();
                    ltemp.setQuantitaCarrello(rs.getInt(8));
                    ltemp.setEan(rs.getString(1));
                    ltemp.setAnnoPubblicazione(rs.getInt(2));
                    ltemp.setPrezzo(rs.getInt(3));
                    ltemp.setAutore(rs.getString(5));
                    ltemp.setTitolo(rs.getString(6));
                    ltemp.setCopertina(rs.getString(7));
                    temp.addVinile(ltemp);
                } else {
                    if (i == 0) {
                        i++;
                    } else {
                        o.add(temp);
                    }
                    prec = next;
                    temp = new Ordine();
                    temp.setOraordine(rs.getString(1));
                    temp.setQuantita(rs.getInt(2));
                    temp.setTotale(rs.getInt(3));
                    Vinile ltemp = new Vinile();
                    ltemp.setEan(rs.getString(4));
                    ltemp.setAnnoPubblicazione(rs.getInt(5));
                    ltemp.setPrezzo(rs.getInt(6));
                    ltemp.setAutore(rs.getString(8));
                    ltemp.setTitolo(rs.getString(9));
                    ltemp.setCopertina(rs.getString(10));
                    temp.aggiungiVinile(ltemp);
                }
            }
            if (temp != null) o.add(temp);
            return o;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrievebyUserIdAndOra(String ora, int idutente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT oradiordine,quantita,totale,ean, anno_pubblicazione,prezzo,descrizione,autore,titolo,copertina,quantita " +
                            "FROM ordine " +
                            "WHERE id_utente=? and oradiordine=?  order by oradiordine");
            ps.setInt(1, idutente);
            ps.setString(2, ora);
            Ordine o = new Ordine();
            ResultSet rs = ps.executeQuery();
            Boolean b = true;
            while (rs.next()) {
                if (b) {
                    o.setOraordine(rs.getString(1));
                    o.setQuantita(rs.getInt(2));
                    o.setTotale(rs.getInt(3));
                    b = false;
                }
                Vinile temp = new Vinile();
                temp.setEan(rs.getString(4));
                temp.setAnnoPubblicazione(rs.getInt(5));
                temp.setPrezzo(rs.getInt(6));
                temp.setAutore(rs.getString(8));
                temp.setTitolo(rs.getString(9));
                temp.setCopertina(rs.getString(10));
                temp.setQuantitaCarrello(rs.getInt(11));
                o.addVinile(temp);
            }
            if (o.getVinile().size() > 0) {
                return o;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean ceckIfExistbyIsbnAndUserID(String ean, int idutente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM ordini " +
                            "WHERE ean=? and id_utente = ?");
            ps.setString(1, ean);
            ps.setInt(2, idutente);
            ResultSet s = ps.executeQuery();

            if (s.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
