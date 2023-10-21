package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.AbbonamentoDAO;
import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Biglietto;
import com.starcinema.starcinema.model.mo.Posto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbbonamentoDAOMySQLJDBCImpl implements AbbonamentoDAO {
    Connection conn;

    public AbbonamentoDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Abbonamento create(Long cod_abb, String PIN, Double prezzo, Integer num_ingressi) {
        PreparedStatement ps;
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setCod_abb(cod_abb);
        abbonamento.setPIN(PIN);
        abbonamento.setPrezzo(prezzo);
        abbonamento.setNum_ingressi(num_ingressi);

        try{
            String sql
                    = " INSERT INTO abbonamento "
                    + "   ( cod_abb,"
                    + "     PIN,"
                    + "     prezzo,"
                    + "     num_ingressi,"
                    + "   ) "
                    + " VALUES (?,?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, abbonamento.getCod_abb());
            ps.setString(i++, abbonamento.getPIN());
            ps.setDouble(i++, abbonamento.getPrezzo());
            ps.setInt(i++, abbonamento.getNum_ingressi());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return abbonamento;
    }

    @Override
    public void update(Abbonamento abbonamento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Abbonamento abbonamento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Abbonamento read(ResultSet rs) {

        Abbonamento abbonamento = new Abbonamento();
        try {
            abbonamento.setCod_abb(rs.getLong("cod_abb"));
        } catch (SQLException sqle) {
        }
        try {
            abbonamento.setPIN(rs.getString("PIN"));
        } catch (SQLException sqle) {
        }
        try {
            abbonamento.setPrezzo(rs.getDouble("prezzo"));
        } catch (SQLException sqle) {
        }
        try {
            abbonamento.setNum_ingressi(rs.getInt("num_ingressi"));
        } catch (SQLException sqle) {
        }

        return abbonamento;
    }
}
