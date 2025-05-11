package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Model.Ordine;
import com.progetto.viniliprogetto.Model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {
/*
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
*/

    private Ordine creaOrdine(ResultSet rs) throws SQLException {
        Ordine o = new Ordine();
        o.setId(rs.getInt(1));
        o.setOraordine(rs.getString(2));
        o.setTotale(rs.getInt(4));
        o.setStato(rs.getString(5));
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

    /*
        //da controllare
        public ArrayList<Ordine> doRetrieveByUserId(int idutente) {
            try {
                ArrayList<Ordine> ordini = new ArrayList<>();
                Connection con = ConPool.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT id,oradiordine,totale,stato " +
                        "FROM ordine " +
                        "WHERE id_utente=?  " +
                        "order by oradiordine desc");
                ps.setInt(1, idutente);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ordini.add(creaOrdine(rs));
                }
                return ordini;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;
            return null;
        }

        public Ordine doRetriveById(int idordine){
            try{
                Ordine ordine = new Ordine;

            }
        }
    */
    //da controllare
    public Ordine doRetrievebyUserIdAndOra(String ora, int idutente) {
        try {
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT oradiordine,totale " +
                    "FROM ordine " +
                    "WHERE id_utente=? and oradiordine=?  order by oradiordine");
            ps.setInt(1, idutente);
            ps.setString(2, ora);
            Ordine ordine = new Ordine();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordine.setOraordine(rs.getString(1));
                ordine.setTotale(rs.getInt(2));
                if (ordine.getVinile().size() > 0) {
                    return ordine;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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
