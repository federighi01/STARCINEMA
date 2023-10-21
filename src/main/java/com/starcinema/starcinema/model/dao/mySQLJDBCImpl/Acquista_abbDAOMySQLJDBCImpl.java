package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.Acquista_abbDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Acquista_abbDAOMySQLJDBCImpl implements Acquista_abbDAO {

    Connection conn;

    public Acquista_abbDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Acquista_abb create(Utente utente, Abbonamento abbonamento, Date data_acq_abb) {
        PreparedStatement ps;
        Acquista_abb acquista_abb = new Acquista_abb();
        acquista_abb.setUtente(utente);
        acquista_abb.setAbbonamento(abbonamento);
        acquista_abb.setData_acq_abb(data_acq_abb);

        try{
            String sql
                    = " INSERT INTO acquista "
                    + "   ( username,"
                    + "     cod_abb,"
                    + "     data_acq_abb,"
                    + "   ) "
                    + " VALUES (?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista_abb.getUtente().getUsername());
            ps.setLong(i++, acquista_abb.getAbbonamento().getCod_abb());
            // Utilizza java.sql.Date per la data
            java.sql.Date dataAcquisto_abbSQL = new java.sql.Date(acquista_abb.getData_acq_abb().getTime());
            ps.setDate(i++, dataAcquisto_abbSQL);

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return acquista_abb;
    }

    @Override
    public void update(Acquista_abb acquista_abb) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE acquista_abb "
                    + " SET "
                    + "   data_acq_abb = ?, "
                    + " WHERE "
                    + "   username = ? "
                    + "   cod_abb = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista_abb.getUtente().getUsername());
            ps.setLong(i++, acquista_abb.getAbbonamento().getCod_abb());
            // Utilizza java.sql.Date per la data
            java.sql.Date dataAcquisto_abbSQL = new java.sql.Date(acquista_abb.getData_acq_abb().getTime());
            ps.setDate(i++, dataAcquisto_abbSQL);
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Acquista_abb acquista_abb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Acquista_abb read(ResultSet rs) {
        Acquista_abb acquista_abb = new Acquista_abb();
        Utente utente = new Utente();
        Abbonamento abbonamento = new Abbonamento();
        acquista_abb.setUtente(utente);
        acquista_abb.setAbbonamento(abbonamento);

        try {
            acquista_abb.getUtente().setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.getAbbonamento().setCod_abb(rs.getLong("cod_abb"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.setData_acq_abb(rs.getTimestamp("data_acq_abb"));
        } catch (SQLException sqle) {
        }

        return acquista_abb;
    }
}
