package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.Utilizzo_abbDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Utilizzo_abbDAOMySQLJDBCImpl implements Utilizzo_abbDAO {

    Connection conn;

    public Utilizzo_abbDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Utilizzo_abb create(Abbonamento abbonamento, Biglietto biglietto, Acquista_abb acquista_abb,  String data_utilizzo) {
        PreparedStatement ps;
        Utilizzo_abb utilizzo_abb = new Utilizzo_abb();
        utilizzo_abb.setAbbonamento(abbonamento);
        utilizzo_abb.setBiglietto(biglietto);
        utilizzo_abb.setAcquista_abb(acquista_abb);
        utilizzo_abb.setData_utilizzo(data_utilizzo);

        try{
            String sql
                    = " INSERT INTO utilizzo_abb "
                    + "   ( cod_abb,"
                    + "     cod_b,"
                    + "     cod_acq_abb,"
                    + "     data_utilizzo,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, utilizzo_abb.getAbbonamento().getCod_abb());
            ps.setLong(i++, utilizzo_abb.getBiglietto().getCod_b());
            ps.setLong(i++, utilizzo_abb.getAcquista_abb().getCod_acq_abb());
            ps.setString(i++, utilizzo_abb.getData_utilizzo());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utilizzo_abb;
    }

    @Override
    public void update(Utilizzo_abb utilizzo_abb) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE utilizzo_abb "
                    + " SET "
                    + "   data_utilizzo = ?, "
                    + " WHERE "
                    + "   cod_abb = ? "
                    + "   cod_b = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, utilizzo_abb.getAbbonamento().getCod_abb());
            ps.setLong(i++, utilizzo_abb.getBiglietto().getCod_b());
            ps.setString(i++, utilizzo_abb.getData_utilizzo());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Utilizzo_abb utilizzo_abb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Utilizzo_abb read(ResultSet rs) {
        Utilizzo_abb utilizzo_abb = new Utilizzo_abb();
        Abbonamento abbonamento = new Abbonamento();
        Biglietto biglietto = new Biglietto();
        Acquista_abb acquista_abb = new Acquista_abb();
        utilizzo_abb.setAbbonamento(abbonamento);
        utilizzo_abb.setBiglietto(biglietto);
        utilizzo_abb.setAcquista_abb(acquista_abb);

        try {
            utilizzo_abb.getAcquista_abb().setCod_acq_abb(rs.getLong("cod_ut_abb"));
        } catch (SQLException sqle) {
        }
        try {
            utilizzo_abb.getAbbonamento().setCod_abb(rs.getLong("cod_abb"));
        } catch (SQLException sqle) {
        }
        try {
            utilizzo_abb.getBiglietto().setCod_b(rs.getLong("cod_b"));
        } catch (SQLException sqle) {
        }
        try {
            utilizzo_abb.getAcquista_abb().setCod_acq_abb(rs.getLong("cod_acq_abb"));
        } catch (SQLException sqle) {
        }
        try {
            utilizzo_abb.setData_utilizzo(rs.getString("data_utilizzo"));
        } catch (SQLException sqle) {
        }
        try {
            utilizzo_abb.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }

        return utilizzo_abb;
    }
}
