package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Model.Genere;
import com.progetto.viniliprogetto.Model.Vinile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VinileDAO {

    private ArrayList<Genere> getGenere(String ean) throws SQLException {
        Connection conn = ConPool.getConnection();
        String sql = "SELECT genere.id, nome, descrizione " +
                "FROM genere,vinile_genere " +
                "where vinile_genere.id=categoria.id " +
                "and ean=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ean);
        ArrayList<Genere> genere = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Genere g = new Genere();
            g.setId(rs.getInt(1));
            g.setNome(rs.getString(2));
            genere.add(g);
        }
        conn.close();
        return genere;
    }

    public boolean seEsiste() {
        try (Connection conn = ConPool.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM vinile");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Vinile creatVinile(ResultSet rs) throws SQLException {
        Vinile v = new Vinile();
        v.setEan(rs.getString(1));
        v.setAnnoPubblicazione(rs.getInt(2));
        v.setPrezzo(rs.getInt(3));
        v.setNumeroDisponibili(rs.getInt(4));
        v.setAutore(rs.getString(6));
        v.setTitolo(rs.getString(7));
        v.setCopertina(rs.getString(8));
        return v;
    }

    //Recupero tutti i vinili
    public List<Vinile> doRetrieveAll(int offset, int limit) throws SQLException {
        Connection conn = ConPool.getConnection();
        String query = " SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina\n"
                + " FROM Vinile LIMIT ?,?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, offset);
        st.setInt(2, limit);
        ResultSet rs = st.executeQuery();
        List<Vinile> vinile = new ArrayList<>();
        while (rs.next()) {
            vinile.add(creatVinile(rs));
        }
        rs.close();
        st.close();
        conn.close();
        return vinile;
    }

    public Vinile doRetrieveByEan(String ean) throws SQLException {
        if (ean.length() != 13) return null;
        Connection conn = ConPool.getConnection();
        String query = "SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina" +
                " FROM vinile " +
                "WHERE ean=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, ean);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            conn.close();
            return creatVinile(rs);
        }
        conn.close();
        return null;
    }

    public List<Vinile> doRetrieveByGenere(int genere, int offset, int limit) throws SQLException {
        Connection conn = ConPool.getConnection();
        String query = "SELECT V.ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina " +
                " FROM vinile V " +
                " LEFT JOIN vinile_genere G ON vinile.ean=vinile_genere.ean " +
                " WHERE G.id=? LIMIT ?, ?";
        PreparedStatement ps = conn.prepareStatement(query);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ps.setInt(1, genere);
        ps.setInt(2, offset);
        ps.setInt(3, limit);
        List<Vinile> vinile = new ArrayList<>();
        while (rs.next()) {
            vinile.add(creatVinile(rs));
        }
        conn.close();
        return vinile;
    }

    public List<Vinile> doRetrieveByTitoloOrAutore(String against, int offset, int limit) throws SQLException {
        Connection conn = ConPool.getConnection();
        String query = "SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina " +
                " FROM vinile " +
                "WHERE MATCH(titolo, autore) AGAINST(?) LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(query);
        Statement st = conn.createStatement();
        ps.setString(1, against);
        ps.setInt(2, offset);
        ps.setInt(3, limit);
        ResultSet rs = ps.executeQuery();
        ArrayList<Vinile> vinile = new ArrayList<>();
        while (rs.next()) {
            vinile.add(creatVinile(rs));
        }
        conn.close();
        return vinile;
    }

    public int countByGenere(int genere) throws SQLException {
        Connection conn = ConPool.getConnection();
        String query = "SELECT COUNT(*) \n" +
                "FROM vinile \n" +
                "LEFT JOIN vinile_genere ON vinile.ean=vinile_genere.ean \n" +
                "WHERE vinile_genere.id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        Statement st = conn.createStatement();
        ps.setInt(1, genere);
        ResultSet rs = ps.executeQuery();
        rs.next();
        conn.close();
        return rs.getInt(1);
    }

    public void doSave(Vinile vinile) throws SQLException {
        Connection conn = ConPool.getConnection();
        String sql = "INSERT INTO vinile(ean,anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, vinile.getEan());
        st.setInt(2, vinile.getAnnoPubblicazione());
        st.setInt(3, vinile.getPrezzo());
        st.setInt(4, vinile.getNumeroDisponibili());
        st.setString(5, vinile.getAutore());
        st.setString(6, vinile.getTitolo());
        st.setString(7, vinile.getCopertina());
        st.executeUpdate();
        st.close();
        conn.close();
    }


    public void doUpdate(Vinile vinile) throws SQLException {
        Connection conn = ConPool.getConnection();
        String sql = "UPDATE  vinile " +
                "SET  anno_pubblicazione=?,prezzo=?,numero_disponibili=?,autore=?,titolo=?,copertina=?, " +
                "WHERE ean=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, vinile.getAnnoPubblicazione());
        ps.setInt(2, vinile.getPrezzo());
        ps.setInt(3, vinile.getNumeroDisponibili());
        ps.setString(4, vinile.getAutore());
        ps.setString(5, vinile.getTitolo());
        ps.setString(6, vinile.getCopertina());
        ps.setString(7, vinile.getEan());


        PreparedStatement psCa2 = conn
                .prepareStatement("DELETE FROM vinile_genere WHERE ean=?");
        psCa2.setString(1, vinile.getEan());
        psCa2.executeUpdate();

        PreparedStatement psCa = conn
                .prepareStatement("INSERT INTO vinile_genere (ean, id) VALUES (?, ?)");
        for (Genere c : vinile.getCategorie()) {
            psCa.setString(1, vinile.getEan());
            psCa.setInt(2, c.getId());
            psCa.addBatch();
        }
        psCa.executeBatch();
        conn.close();
    }

    public void doDelete(String ean) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM vinile " +
                "WHERE ean=?");
        ps.setString(1, ean);
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("DELETE error.");
        }
        PreparedStatement psCa = conn.prepareStatement("DELETE FROM vinile_genere " +
                "WHERE ean=?");
        psCa.setString(1, ean);
        psCa.executeUpdate();
        conn.close();
    }


    public List<Vinile> doRetrieveByNome(String against, int offset, int limit) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina " +
                        "FROM vinile " +
                        "WHERE MATCH(titolo) AGAINST(?) LIMIT ?,?");
        ps.setString(1, against);
        ps.setInt(2, offset);
        ps.setInt(3, limit);
        ArrayList<Vinile> vinile = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            vinile.add(creatVinile(rs));
        }
        conn.close();
        return vinile;
    }
}
