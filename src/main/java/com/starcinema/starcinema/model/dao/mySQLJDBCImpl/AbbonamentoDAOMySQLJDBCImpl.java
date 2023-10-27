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
    public Abbonamento create(Long cod_abb, Double prezzo) {
        PreparedStatement ps;
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setCod_abb(cod_abb);
        abbonamento.setPrezzo(prezzo);


        try{
            String sql
                    = " INSERT INTO abbonamento "
                    + "   ( cod_abb,"
                    + "     prezzo,"
                    + "   ) "
                    + " VALUES (?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, abbonamento.getCod_abb());
            ps.setDouble(i++, abbonamento.getPrezzo());


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
            abbonamento.setPrezzo(rs.getDouble("prezzo"));
        } catch (SQLException sqle) {
        }


        return abbonamento;
    }
}
