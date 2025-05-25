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
        o.setId(rs.getInt("ordineid"));
        o.setOraordine(rs.getString("ora"));
        o.setTotale(rs.getInt("ordinetotale"));
        o.setStato(rs.getString("stato"));
        return o;
    }

    private Utente creaUtenteOrdine(ResultSet rs) throws SQLException {
        Utente utente = new Utente();
        utente.setId(rs.getInt("idutente"));
        utente.setUsername(rs.getString("Username"));
        utente.setNome(rs.getString("utenteNome"));
        utente.setCognome(rs.getString("utenteCognome"));
        return utente;
    }

    public ArrayList<Ordine> doRetrieveAll() {
        try {
            ArrayList<Ordine> ordini = new ArrayList<Ordine>();
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT O.id AS ordineid, O.oradiordine AS ora,O.totale AS ordinetotale, o.stato AS stato, " +
                    "U.username AS Username, U.id AS idutente, U.nome AS utenteNome, U.cognome AS utenteCognome " +
                    "FROM ordine O " +
                    "  INNER JOIN utente U on O.id_utente = U.id " +
                    "where O.id_utente=U.id order by O.oradiordine DESC ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ordine ordine = creaOrdine(rs);
                ordine.setUtente(creaUtenteOrdine(rs));
                ordini.add(ordine);
            }
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //da controllare
    public ArrayList<Ordine> doRetrieveByUserId(int idutente) {
        try {
            ArrayList<Ordine> ordini = new ArrayList<>();
            Connection con = ConPool.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT O.id AS ordineid, O.oradiordine AS ora,O.totale AS ordinetotale, o.stato AS stato " +
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

    public void doCambiaStatoOrdine(Ordine ordine) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE ordine SET stato=? where id=?");
            ps.setString(1, ordine.getStato());
            ps.setInt(2, ordine.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                        "from vinile_in_ordine vio " +
                        "inner join vinile v on vio.vinile_ean = v.EAN\n" +
                        "where ordine_id = ?");
                ps.setInt(1, idOrdine);
                rs = ps.executeQuery();
                List<VinileInOrdine> vinileInOrdineList = new ArrayList<>();
                while (rs.next()) {
                    VinileInOrdine vinileInOrdine = new VinileInOrdine();
                    vinileInOrdine.setQuantita(rs.getInt(2));
                    vinileInOrdine.setPrezzo(rs.getFloat(4));
                    Vinile vinile = new Vinile();
                    vinile.setEan(rs.getString(3));
                    vinile.setAnnoPubblicazione(rs.getInt(6));
                    vinile.setPrezzo(rs.getFloat(7));
                    vinile.setNumeroDisponibili(rs.getInt(8));
                    vinile.setAutore(rs.getString(9));
                    vinile.setTitolo(rs.getString(10));
                    vinile.setCopertina(rs.getString(11));
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
