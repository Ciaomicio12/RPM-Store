package com.rpm_store.DAO;

import com.rpm_store.Model.Genere;
import com.rpm_store.Model.Vinile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VinileDAO {

    private ArrayList<Genere> getGeneri(String ean) throws SQLException {
        Connection conn = ConPool.getConnection();
        String sql = "SELECT genere.id, nome " +
                "FROM genere,vinile_genere " +
                "where vinile_genere.id=genere.id " +
                "and ean=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ean);
        ArrayList<Genere> genere = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Genere g = new Genere(rs.getInt(1), rs.getString(2));
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

    private Vinile createVinile(ResultSet rs) throws SQLException {
        Vinile v = new Vinile();
        v.setEan(rs.getString(1));
        v.setAnnoPubblicazione(rs.getInt(2));
        v.setPrezzo(rs.getFloat(3));
        v.setNumeroDisponibili(rs.getInt(4));
        v.setAutore(rs.getString(5));
        v.setTitolo(rs.getString(6));
        v.setCopertina(rs.getString(7));
        return v;
    }

    public List<Vinile> doRetrieveAllRandomly(int limit) {
        return doRetrieveAll(0, limit, true);
    }

    public List<Vinile> doRetrieveAll(int offset, int limit) {
        return doRetrieveAll(offset, limit, false);
    }

    //Recupero tutti i vinili
    public List<Vinile> doRetrieveAll(int offset, int limit, boolean ordineCasuale) {
        try {
            Connection conn = ConPool.getConnection();
            String query = " SELECT ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina\n"
                    + " FROM Vinile limit ?,? ";
            PreparedStatement st = conn.prepareStatement(query);
            if (ordineCasuale) query += "ORDER BY RAND()";
            st.setInt(1, offset);
            st.setInt(2, limit);
            ResultSet rs = st.executeQuery();
            List<Vinile> vinili = new ArrayList<>();
            while (rs.next()) {
                vinili.add(createVinile(rs));
            }
            rs.close();
            st.close();
            conn.close();
            return vinili;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vinile doRetrieveByEan(String ean) {
        try {
            if (ean.length() < 11 || ean.length() > 13) return null;
            Connection conn = ConPool.getConnection();
            String query = "SELECT v.ean, anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina,nome,g.id\n" +
                    "FROM vinile v\n" +
                    "LEFT JOIN vinile_genere vg on v.EAN = vg.EAN\n" +
                    "LEFT join genere g on vg.id = g.id\n" +
                    "WHERE v.ean=?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ean);
            ResultSet rs = st.executeQuery();
            Vinile v = null;
            List<Genere> generi = new ArrayList<>();
            while (rs.next()) {
                if (v == null) {
                    v = createVinile(rs);
                }
                generi.add(new Genere(rs.getInt(9), rs.getString(8)));
            }
            if (v != null) {
                v.setGeneri(generi);
            }
            rs.close();
            st.close();
            conn.close();
            return v;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            vinile.add(createVinile(rs));
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
            vinile.add(createVinile(rs));
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

    public void doSave(Vinile vinile, List<Genere> generi) {
        try {
            Connection conn = ConPool.getConnection();
            String sql = "INSERT INTO vinile(ean,anno_pubblicazione,prezzo,numero_disponibili,autore,titolo,copertina) " +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, vinile.getEan());
            st.setInt(2, vinile.getAnnoPubblicazione());
            st.setFloat(3, vinile.getPrezzo());
            st.setInt(4, vinile.getNumeroDisponibili());
            st.setString(5, vinile.getAutore());
            st.setString(6, vinile.getTitolo());
            st.setString(7, vinile.getCopertina());
            st.executeUpdate();
            st.close();
            this.doAggiungiGeneriVinile(vinile);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void doRemoveGeneriVinile(Vinile vinile) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement psCa = conn.prepareStatement("DELETE from  Vinile_genere " +
                    "Where ean = ?");
            psCa.setString(1, vinile.getEan());
            psCa.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doAggiungiGeneriVinile(Vinile vinile) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement psCa = conn.prepareStatement("INSERT INTO vinile_genere (ean, id) VALUES (?, ?)");
            for (Genere genere : vinile.getGeneri()) {
                psCa.setString(1, vinile.getEan());
                psCa.setInt(2, genere.getId());
                psCa.addBatch();
            }
            psCa.executeBatch();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doUpdate(Vinile vinile) {
        try {
            Connection conn = ConPool.getConnection();
            String sql = "UPDATE  vinile " +
                    "SET  anno_pubblicazione=?,prezzo=?,numero_disponibili=?,autore=?,titolo=?,copertina=? " +
                    "WHERE ean=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vinile.getAnnoPubblicazione());
            ps.setFloat(2, vinile.getPrezzo());
            ps.setInt(3, vinile.getNumeroDisponibili());
            ps.setString(4, vinile.getAutore());
            ps.setString(5, vinile.getTitolo());
            ps.setString(6, vinile.getCopertina());
            ps.setString(7, vinile.getEan());
            // Verifica se la lista dei generi è vuota
            if (vinile.getGeneri().isEmpty()) {
                // Non fare niente, perché non hai inserito alcun genere
            } else {
                // Rimuovi i generi esistenti prima di aggiungere i nuovi generi
                this.doRemoveGeneriVinile(vinile);
                this.doAggiungiGeneriVinile(vinile);
            }
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(String ean) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Vinile> doRetrieveByNome(String against, int offset, int limit) {
        try {
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
                vinile.add(createVinile(rs));
            }
            conn.close();
            return vinile;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
