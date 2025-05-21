package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Model.*;

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

    public ArrayList<Ordine> doRetrieveAll(int limit, int offset) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


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

    //da controllare
    public Ordine doRetrievebyUserIdAndOra(String ora, int idutente) {
        try {
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id,oradiordine,totale,stato " +
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

    public Boolean checkIfExistbyEanAndUserID(String ean, int idutente) {
        try {
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ordine " +
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

    public Ordine doRetriveById(int idordine) {
        try {
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT o.id AS idordine, o.id_utente AS idutenteordine, o.oradiordine AS oradiordine,\n" +
                    "        o.totale AS totaleordine, o.stato AS ordinestato,\n" +
                    "        vio.quantita AS vinileordinequantita, vio.vinile_ean AS vinileordineean,\n" +
                    "        vio.prezzoacq AS vinileordineprezzoacq, v.anno_pubblicazione AS vinileanno,\n" +
                    "        v.prezzo AS vinileprezzo, v.numero_disponibili AS vinilenumero,\n" +
                    "        v.autore AS vinileautore, v.titolo AS viniletitolo,\n" +
                    "        v.copertina AS vinilecopertina,\n" +
                    "        u.nome AS nomeutente, u.cognome AS cognomeutente,\n" +
                    "        u.username AS username, i.id AS idindirizzo,\n" +
                    "        i.cap AS capindirizzo, i.citta AS cittaindirizzo,\n" +
                    "        i.numero_civico AS numerocivicoindirizzo, i.strada AS stradaindirizzo,\n" +
                    "        i.telefono AS indirizzotelefono\n" +
                    "        from ordine o\n" +
                    "        inner join vinile_in_ordine vio on o.id = vio.ordine_id\n" +
                    "        inner join vinile v on vio.vinile_ean = v.EAN\n" +
                    "        inner join utente u on o.id_utente = u.id\n" +
                    "        inner join indirizzo i on o.indirizzo = i.id\n" +
                    "        where o.id=?");
            ps.setInt(1, idordine);
            Ordine ordine = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (ordine == null) { //creare ordine indirizzo e utente dal result set
                    ordine = new Ordine();
                    Utente utente = new Utente();
                    Indirizzo indirizzo = new Indirizzo();
                    ordine.setUtente(utente);
                    ordine.setIndirizzo(indirizzo);
                    ordine.setTotale(rs.getInt(4));
                    ordine.setOraordine(rs.getString(3));
                    ordine.setId(rs.getInt(1));
                    ordine.setStato(rs.getString(5));
                    indirizzo.setId(rs.getInt("cittaindirizzo"));
                    indirizzo.setCap(rs.getString("capindirizzo"));
                    indirizzo.setCitta(rs.getString("cittaindirizzo"));
                    indirizzo.setStrada(rs.getString("stradaindirizzo"));
                    indirizzo.setNumero_civico(rs.getString("numerocivicoindirizzo"));
                    indirizzo.setTelefono(rs.getString("indirizzotelefono"));
                    utente.setNome(rs.getString("nomeutente"));
                    utente.setCognome(rs.getString("cognomeutente"));
                    utente.setUsername(rs.getString("username"));
                    utente.setIndirizzo(indirizzo);
                }
                VinileInOrdine vio = new VinileInOrdine();
                Vinile vinile = new Vinile();
                vio.setVinile(vinile);
                vio.setOrdine(ordine);
                vio.setQuantita(rs.getInt("vinileordinequantita"));
                vio.setPrezzo(rs.getInt("vinileordineprezzoacq"));
                vinile.setCopertina(rs.getString("vinilecopertina"));
                vinile.setPrezzo(rs.getInt("vinileprezzo"));
                vinile.setTitolo(rs.getString("viniletitolo"));
                vinile.setAnnoPubblicazione(rs.getInt("vinileanno"));
                vinile.setNumeroDisponibili(rs.getInt("vinilenumero"));
                vinile.setAutore(rs.getString("vinileautore"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
