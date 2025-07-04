package com.rpm_store.DAO;

import com.rpm_store.Model.Indirizzo;
import com.rpm_store.Model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO extends Utente {

    public List<Utente> doRetrieveAll() {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, username, password, nome, cognome, sesso, email, admin, disabled " +
                    "FROM utente");
            ArrayList<Utente> utenti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                utenti.add(creatUtente(rs));
            }
            conn.close();
            return utenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void doUpdateUtente(Utente utente) {
        try {
            Connection conn = ConPool.getConnection();
            if (utente.getIndirizzo().getId() == -1) {
                // Nuovo indirizzo
                PreparedStatement ps = conn.prepareStatement("INSERT INTO indirizzo (strada,citta,cap,numero_civico,telefono) " +
                        "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, utente.getIndirizzo().getStrada());
                ps.setString(2, utente.getIndirizzo().getCitta());
                ps.setString(3, utente.getIndirizzo().getCap());
                ps.setString(4, utente.getIndirizzo().getNumeroCivico());
                ps.setString(5, utente.getIndirizzo().getTelefono());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                    utente.getIndirizzo().setId(rs.getInt(1));
                rs.close();
                ps.close();
            } else {
                PreparedStatement ps = conn.prepareStatement("UPDATE indirizzo set strada=?, citta=?, cap=?, numero_civico=?, telefono=? where id=?");
                ps.setString(1, utente.getIndirizzo().getStrada());
                ps.setString(2, utente.getIndirizzo().getCitta());
                ps.setString(3, utente.getIndirizzo().getCap());
                ps.setString(4, utente.getIndirizzo().getNumeroCivico());
                ps.setString(5, utente.getIndirizzo().getTelefono());
                ps.setInt(6, utente.getIndirizzo().getId());
                ps.executeUpdate();
                ps.close();
            }
            PreparedStatement ps = conn.prepareStatement("UPDATE utente set nome=?, cognome=?, email=? where id=?");
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setInt(4, utente.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String email, String password) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT password " +
                    "from utente " +
                    "WHERE email=? AND password=SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                conn.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Utente doRetrieveByEmailPassword(String email, String password) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT u.id,\n" +
                            "       username,\n" +
                            "       password,\n" +
                            "       nome,\n" +
                            "       cognome,\n" +
                            "       sesso,\n" +
                            "       email,\n" +
                            "       admin,\n" +
                            "       disabled,\n" +
                            "       i.id,\n" +
                            "       strada,\n" +
                            "       citta,\n" +
                            "       cap,\n" +
                            "       numero_civico,\n" +
                            "       telefono\n" +
                            "FROM utente u\n" +
                            "LEFT join indirizzo i on u.indirizzo = i.id\n" +
                            "WHERE email = ?\n" +
                            "  AND password = SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                Utente utente = creatUtente(rs);
                // controllo se l'id dell'indirizzo è NUll: se non lo è lo creo
                rs.getInt(10);
                if (!rs.wasNull())
                    utente.setIndirizzo(creaIndirizzo(rs));
                return utente;
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Utente doRetrieveByUsername(String username) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, username, password, nome, cognome, sesso, email, admin , disabled " +
                            "FROM utente " +
                            "WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return creatUtente(rs);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Utente doRetrieveById(int id) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, username, password, nome, cognome, sesso, email, admin, disabled " +
                            "FROM utente " +
                            "WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return creatUtente(rs);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean mailEsistente(String email) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, username, password, nome, cognome, sesso, email, admin " +
                            "FROM utente " +
                            "WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void doUpdateUserInfo(Utente u) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE utente set nome=?,cognome=?,email=?,username=? " +
                            "where id=?;",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getUsername());
            ps.setInt(5, u.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void doUpdatePassword(String username, String password) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE utente set password=SHA1(?) " +
                            "where username=?;",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, password);
            ps.setString(2, username);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void doDisableProfile(Utente utente, Boolean disabled) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE utente set disabled=? " +
                            "where id=?");
            ps.setInt(2, utente.getId());
            ps.setBoolean(1, disabled);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doSave(Utente utente) {
        try {
            Connection conn = ConPool.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO utente (username, password, nome, cognome, sesso, email, admin) " +
                            "VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPasswordhash());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setString(5, utente.getSesso());
            ps.setString(6, utente.getEmail());
            ps.setBoolean(7, utente.isAdmin());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            utente.setId(this.lastInsertId(conn));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Utente creatUtente(ResultSet rs) throws SQLException {
        Utente p = new Utente();
        p.setId(rs.getInt(1));
        p.setUsername(rs.getString(2));
        p.setPassword(rs.getString(3));
        p.setNome(rs.getString(4));
        p.setCognome(rs.getString(5));
        p.setSesso(rs.getString(6));
        p.setEmail(rs.getString(7));
        p.setAdmin(rs.getBoolean(8));
        p.setDisabled(rs.getBoolean(9));
        return p;
    }

    private Indirizzo creaIndirizzo(ResultSet rs) throws SQLException {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setId(rs.getInt(10));
        indirizzo.setStrada(rs.getString(11));
        indirizzo.setCitta(rs.getString(12));
        indirizzo.setCap(rs.getString(13));
        indirizzo.setNumeroCivico(rs.getString(14));
        indirizzo.setTelefono(rs.getString(15));
        return indirizzo;
    }

    private int lastInsertId(Connection conn) throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
}
