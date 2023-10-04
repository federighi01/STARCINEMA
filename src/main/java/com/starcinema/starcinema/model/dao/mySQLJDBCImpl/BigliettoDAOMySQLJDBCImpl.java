package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.BigliettoDAO;
import com.starcinema.starcinema.model.mo.Biglietto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BigliettoDAOMySQLJDBCImpl implements BigliettoDAO {
    Connection conn;

    public BigliettoDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Biglietto create(String cod_b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Biglietto biglietto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Biglietto biglietto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Biglietto read(ResultSet rs) {

        Biglietto biglietto = new Biglietto();
        try {
            biglietto.setCod_b(rs.getString("cod_b"));
        } catch (SQLException sqle) {
        }

        return biglietto;
    }
}
