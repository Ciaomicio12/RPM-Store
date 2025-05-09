package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Model.Genere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenereDAO {

    private Genere createGenere(ResultSet rs) throws SQLException {
        Genere g = new Genere();
        g.setId(rs.getInt(1));
        g.setNome(rs.getString(2));
        return g;
    }

    public ArrayList<Genere> doRetriveById(int id) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id, nome " +
                "FROM genere " +
                "WHERE id=?");
        ps.setInt(1, id);
        ArrayList<Genere> genere = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            genere.add(createGenere(rs));
        }
        rs.close();
        ps.close();
        conn.close();
        return genere;
    }

    public ArrayList<Genere> doRetriveByNome(String nome) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id, nome " +
                "FROM genere " +
                "WHERE nome=?");
        ps.setString(1, nome);
        ArrayList<Genere> genere = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Genere g = new Genere();
        while (rs.next()) {
            genere.add(createGenere(rs));
        }
        rs.close();
        ps.close();
        conn.close();
        return genere;
    }

    public List<Genere> doRetrieveAll() throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id, nome " +
                "FROM categoria");
        ArrayList<Genere> genere = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            genere.add(createGenere(rs));
        }
        conn.close();
        return genere;
    }


    public void doSave(Genere genere) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO genere (nome) " +
                "VALUES(?)");
        ps.setString(1, genere.getNome());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        conn.close();
    }

    public void doUpdate(Genere genere) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE categoria SET nome=? " +
                "WHERE id=?");
        ps.setInt(2, genere.getId());
        ps.setString(1, genere.getNome());
        ps.executeUpdate();
        conn.close();
    }

    public void doDelete(int id) throws SQLException {
        Connection conn = ConPool.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM categoria" +
                " WHERE id=?");
        ps.setInt(1, id);
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("DELETE error.");
        }
        conn.close();
    }
}