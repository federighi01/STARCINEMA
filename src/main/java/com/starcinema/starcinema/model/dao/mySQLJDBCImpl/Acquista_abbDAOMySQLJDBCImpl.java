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
    public Acquista_abb create(Long cod_acq_abb, Utente utente, Abbonamento abbonamento, String data_acq_abb/*, Integer num_ingressi*/) {
        PreparedStatement ps;
        Acquista_abb acquista_abb = new Acquista_abb();
        acquista_abb.setCod_acq_abb(cod_acq_abb);
        acquista_abb.setUtente(utente);
        acquista_abb.setAbbonamento(abbonamento);
        acquista_abb.setData_acq_abb(data_acq_abb);
        //acquista_abb.setNum_ingressi(num_ingressi);

        try{
            String sql
                    = " INSERT INTO acquista "
                    + "   ( cod_acq_abb,"
                    + "     username,"
                    + "     cod_abb,"
                    + "     data_acq_abb,"
                    + "     num_ingressi,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,'10','N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, acquista_abb.getCod_acq_abb());
            ps.setString(i++, acquista_abb.getUtente().getUsername());
            ps.setLong(i++, acquista_abb.getAbbonamento().getCod_abb());
            ps.setString(i++, acquista_abb.getData_acq_abb());
            //ps.setInt(i++, acquista_abb.getNum_ingressi());

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
            ps.setString(i++, acquista_abb.getData_acq_abb());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Acquista_abb acquista_abb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Acquista_abb findLoggedAcquista_abb() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Acquista_abb read(ResultSet rs) {
        Acquista_abb acquista_abb = new Acquista_abb();
        Utente utente = new Utente();
        Abbonamento abbonamento = new Abbonamento();
        acquista_abb.setUtente(utente);
        acquista_abb.setAbbonamento(abbonamento);

        try {
            acquista_abb.setCod_acq_abb(rs.getLong("cod_acq_abb"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.getUtente().setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.getAbbonamento().setCod_abb(rs.getLong("cod_abb"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.setData_acq_abb(rs.getString("data_acq_abb"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.setNum_ingressi(rs.getInt("num_ingressi"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }

        return acquista_abb;
    }
}
