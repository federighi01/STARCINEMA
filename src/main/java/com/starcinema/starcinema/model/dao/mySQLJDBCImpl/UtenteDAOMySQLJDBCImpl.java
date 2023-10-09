package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.UtenteDAO;
import com.starcinema.starcinema.model.mo.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class UtenteDAOMySQLJDBCImpl implements UtenteDAO {

    Connection conn;

    public UtenteDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Utente create(String username, String pw, String email, String tipo, String cognome, String nome, /*Date data_n,*/ String luogo_n, String indirizzo, Long tel) {
        PreparedStatement ps;
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPw(pw);
        utente.setEmail(email);
        utente.setCognome(cognome);
        utente.setNome(nome);
        //utente.setData_n(data_n);
        utente.setLuogo_n(luogo_n);
        utente.setIndirizzo(indirizzo);
        utente.setTel(tel);

        try{
            String sql
                    = " INSERT INTO utente "
                    + "   ( username,"
                    + "     pw,"
                    + "     email,"
                    + "     tipo,"
                    + "     cognome,"
                    + "     nome,"
                    //+ "     data_n,"
                    + "     luogo_n,"
                    + "     indirizzo,"
                    + "     tel,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, utente.getUsername());
            ps.setString(i++, utente.getPw());
            ps.setString(i++, utente.getEmail());
            ps.setString(i++, utente.getTipo());
            ps.setString(i++, utente.getCognome());
            ps.setString(i++, utente.getNome());
            //ps.setTime(i++, (Time) utente.getData_n());
            ps.setString(i++, utente.getLuogo_n());
            ps.setString(i++, utente.getIndirizzo());
            ps.setLong(i++, utente.getTel());

            ps.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return utente;
    }

    @Override
    public void update(Utente utente) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE utente "
                    + " SET "
                    + "   pw = ?, "
                    + "   email = ?, "
                    + "   cognome = ?, "
                    + "   nome = ?, "
                    + "   data_n = ?, "
                    + "   luogo_n = ?, "
                    + "   indirizzo = ? "
                    + "   tel = ? "
                    + " WHERE "
                    + "   username = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, utente.getPw());
            ps.setString(i++, utente.getEmail());
            ps.setString(i++, utente.getCognome());
            ps.setString(i++, utente.getNome());
            ps.setTime(i++, (Time) utente.getData_n());
            ps.setString(i++, utente.getLuogo_n());
            ps.setString(i++, utente.getIndirizzo());
            ps.setLong(i++, utente.getTel());
            ps.setString(i++, utente.getUsername());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Utente utente) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE utente "
                    + " SET deleted='Y' "
                    + " WHERE "
                    + " username=? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, utente.getUsername());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente findLoggedUtente() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Utente findByUsername(String username) {
        PreparedStatement ps;
        Utente utente = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM utente "
                    + " WHERE "
                    + "   username = ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                utente = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utente;
    }

    Utente read(ResultSet rs) {

        Utente utente = new Utente();
        try {
            utente.setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setPw(rs.getString("pw"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setEmail(rs.getString("email"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setTipo(rs.getString("tipo"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setCognome(rs.getString("cognome"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setNome(rs.getString("nome"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setData_n(rs.getTime("data_n"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setLuogo_n(rs.getString("luogo_n"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setIndirizzo(rs.getString("indirizzo"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setTel(rs.getLong("tel"));
        } catch (SQLException sqle) {
        }
        try {
            utente.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }
        return utente;
    }

}
