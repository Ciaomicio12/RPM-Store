package com.progetto.viniliprogetto.DAO;

import com.progetto.viniliprogetto.Model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO extends Utente {

    public List<Utente> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT id, username, passwordhash, nome, cognome, sesso, email, admin, disabled FROM utente");
            ArrayList<Utente> utenti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
                utenti.add(p);
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ceckPassword(String username, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT passwordhash from utente WHERE username=? AND passwordhash=SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernamePassword(String username, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, nome, cognome, sesso, email, admin,disabled FROM utente WHERE username=? AND passwordhash=SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPasswordhash(rs.getString(3));
                p.setNome(rs.getString(4));
                p.setCognome(rs.getString(5));
                p.setSesso(rs.getString(6));
                p.setEmail(rs.getString(7));
                p.setAdmin(rs.getBoolean(8));
                p.setDisabled(rs.getBoolean(9));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsername(String username) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, nome, cognome, sesso, email, admin , disabled FROM utente WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, nome, cognome, sesso, email, admin, disabled FROM utente WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, passwordhash, nome, cognome, sesso, email, admin FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateUserInfo(Utente u) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente set nome=?,cognome=?,email=?,username=? where id=?;",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getUsername());
            ps.setInt(5, u.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePassword(String username, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente set passwordhash=SHA1(?) where username=?;",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, password);
            ps.setString(2, username);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDisableProfile(Utente utente, Boolean disabled) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente set disabled=? where id=?");
            ps.setInt(2, utente.getId());
            ps.setBoolean(1, disabled);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSetAdmin(Utente utente, Boolean admin) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente set admin=? where id=?");
            ps.setBoolean(1, admin);
            ps.setInt(2, utente.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (username, passwordhash, nome, cognome, sesso, email, admin) VALUES(?,?,?,?,?,?,?)",
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
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            utente.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
