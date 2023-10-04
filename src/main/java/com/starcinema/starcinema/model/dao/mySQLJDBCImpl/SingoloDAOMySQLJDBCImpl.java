package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.SingoloDAO;
import com.starcinema.starcinema.model.mo.Biglietto;
import com.starcinema.starcinema.model.mo.Singolo;

import java.sql.Connection;

public class SingoloDAOMySQLJDBCImpl implements SingoloDAO {
    Connection conn;

    public SingoloDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Singolo create(Double prezzo, String tipo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Singolo singolo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Singolo singolo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
