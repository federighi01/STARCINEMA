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
    public Acquista_abb create(Utente utente, Abbonamento abbonamento, String data_acq_abb, String metodo_p, String numero_carta) {
        PreparedStatement ps;
        Acquista_abb acquista_abb = new Acquista_abb();
        acquista_abb.setUtente(utente);
        acquista_abb.setAbbonamento(abbonamento);
        acquista_abb.setData_acq_abb(data_acq_abb);
        acquista_abb.setMetodo_p(metodo_p);
        acquista_abb.setNumero_carta(numero_carta);

        try{
            String sql
                    = " INSERT INTO acquista_abb "
                    + "   ( username,"
                    + "     cod_abbonamento,"
                    + "     data_acq_abb,"
                    + "     metodo_p,"
                    + "     numero_carta,"
                    + "     num_ingressi,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,'10','N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista_abb.getUtente().getUsername());
            ps.setLong(i++, acquista_abb.getAbbonamento().getCod_abb());
            ps.setString(i++, acquista_abb.getData_acq_abb());
            ps.setString(i++, acquista_abb.getMetodo_p());
            ps.setString(i++, acquista_abb.getNumero_carta());

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
                    + "   num_ingressi = num_ingressi - 1 "
                    + " WHERE "
                    + "   cod_acq_abb = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, acquista_abb.getCod_acq_abb());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Acquista_abb acquista_abb) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE acquista_abb "
                    + " SET deleted = 'Y' "
                    + " WHERE "
                    + " cod_acq_abb = ? ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, acquista_abb.getCod_acq_abb());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Acquista_abb findLoggedAcquista_abb() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Acquista_abb findAcqByUsername(Utente utente) {
        PreparedStatement ps;
        Acquista_abb acquista_abb = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM acquista_abb "
                    + " WHERE "
                    + "   username = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, utente.getUsername());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                acquista_abb = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return acquista_abb;
    }

    @Override
    public Acquista_abb findAcqByCod_acq_abb(Long cod_acq_abb) {
        PreparedStatement ps;
        Acquista_abb acquista_abb = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM acquista_abb "
                    + " WHERE "
                    + "   cod_acq_abb = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_acq_abb);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                acquista_abb = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return acquista_abb;
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
            acquista_abb.getAbbonamento().setCod_abb(rs.getLong("cod_abbonamento"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.setData_acq_abb(rs.getString("data_acq_abb"));
        } catch (SQLException sqle) {
        }
        try {
            acquista_abb.setMetodo_p(rs.getString("metodo_p"));
        } catch (SQLException sqle) {
        }
        try{
            acquista_abb.setNumero_carta(rs.getString("numero_carta"));
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
