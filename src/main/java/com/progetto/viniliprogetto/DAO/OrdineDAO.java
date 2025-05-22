package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Controller.MyServletException;
import com.progetto.viniliprogetto.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {

    public void doSave(Ordine ordine, Utente utente) {
        int ordine_id = -1;
        try (Connection conn = ConPool.getConnection()) {
            String sql = "Insert into ordine (indirizzo,oradiordine,id_utente,totale,stato) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, utente.getIndirizzo().getId());
            ps.setString(2, ordine.getOraordine());
            ps.setInt(3, utente.getId());
            ps.setFloat(4, ordine.getTotale());
            ps.setString(5, ordine.getStato());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ordine_id = rs.getInt(1);
                ordine.setId(ordine_id);
            } else {
                throw new MyServletException("Errore doSave in ordineDao");
            }
            rs.close();
            String sql2 = "Insert into vinile_in_ordine (ordine_id,quantita,vinile_ean,prezzoacq) values(?,?,?,?)";
            for (VinileInOrdine vinile : ordine.getViniliInOrdineList()) {
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, ordine_id);
                ps.setInt(2, vinile.getQuantita());
                ps.setString(3, vinile.getVinile().getEan());
                ps.setFloat(4, vinile.getPrezzo());
                ps.executeUpdate();
            }
            conn.close();
            ps.close();
        } catch (SQLException | MyServletException e) {
            throw new RuntimeException(e);
        }

    }

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

    public ArrayList<Ordine> doRetrieveAll() {
        try {
            ArrayList<Ordine> ordini = new ArrayList<Ordine>();
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT O.oradiordine AS ora,O.totale,U.username AS Username, O.id, U.id AS idutente " +
                    "FROM ordine O " +
                    "  INNER JOIN utente U on o.id_utente = u-id " +
                    "where O.id_utente=U.id order by O.oradiordine DESC ");
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
                if (ordine.getViniliInOrdineList().size() > 0) {
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

    public Ordine doRetriveById(int idOrdine) {
        try {
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT o.id AS idordine,\n" +
                    "       o.id_utente          AS idutenteordine,\n" +
                    "       o.oradiordine        AS oradiordine,\n" +
                    "       o.totale             AS totaleordine,\n" +
                    "       o.stato              AS ordinestato,\n" +
                    "       u.nome               AS nomeutente,\n" +
                    "       u.cognome            AS cognomeutente,\n" +
                    "       u.username           AS username,\n" +
                    "       i.id                 AS idindirizzo,\n" +
                    "       i.cap                AS capindirizzo,\n" +
                    "       i.citta              AS cittaindirizzo,\n" +
                    "       i.numero_civico      AS numerocivicoindirizzo,\n" +
                    "       i.strada             AS stradaindirizzo,\n" +
                    "       i.telefono           AS indirizzotelefono\n" +
                    "from ordine o\n" +
                    "         inner join utente u on o.id_utente = u.id\n" +
                    "         inner join indirizzo i on o.indirizzo = i.id\n" +
                    "where o.id = ?");
            ps.setInt(1, idOrdine);
            Ordine ordine = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //creare ordine indirizzo e utente dal result set
                ordine = new Ordine();
                Utente utente = new Utente();
                Indirizzo indirizzo = new Indirizzo();
                ordine.setTotale(rs.getInt(4));
                ordine.setOraordine(rs.getString(3));
                ordine.setId(rs.getInt(1));
                ordine.setStato(rs.getString(5));
                indirizzo.setId(rs.getInt("idindirizzo"));
                indirizzo.setCap(rs.getString("capindirizzo"));
                indirizzo.setCitta(rs.getString("cittaindirizzo"));
                indirizzo.setStrada(rs.getString("stradaindirizzo"));
                indirizzo.setNumeroCivico(rs.getString("numerocivicoindirizzo"));
                indirizzo.setTelefono(rs.getString("indirizzotelefono"));
                utente.setNome(rs.getString("nomeutente"));
                utente.setCognome(rs.getString("cognomeutente"));
                utente.setUsername(rs.getString("username"));
                utente.setIndirizzo(indirizzo);
                ordine.setUtente(utente);
                ordine.setIndirizzo(indirizzo);

                ps = con.prepareStatement("SELECT *\n" +
                        "from vinile_in_ordine\n" +
                        "inner join vinile v on vinile_in_ordine.vinile_ean = v.EAN\n" +
                        "where ordine_id = ?");
                ps.setInt(1, idOrdine);
                rs = ps.executeQuery();
                List<VinileInOrdine> vinileInOrdineList = new ArrayList<>();
                while (rs.next()) {
                    VinileInOrdine vinileInOrdine = new VinileInOrdine();
                    vinileInOrdine.setQuantita(rs.getInt(2));
                    vinileInOrdine.setPrezzo(rs.getFloat(3));
                    Vinile vinile = new Vinile();
                    vinile.setEan(rs.getString(4));
                    vinile.setAnnoPubblicazione(rs.getInt(5));
                    vinile.setPrezzo(rs.getFloat(6));
                    vinile.setNumeroDisponibili(rs.getInt(7));
                    vinile.setAutore(rs.getString(8));
                    vinile.setTitolo(rs.getString(9));
                    vinile.setCopertina(rs.getString(10));
                    vinileInOrdine.setVinile(vinile);
                    vinileInOrdineList.add(vinileInOrdine);
                }
                ordine.setViniliInOrdineList(vinileInOrdineList);
            }
            return ordine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
