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
    public Biglietto create(String cod_b) {
        return null;
    }

    @Override
    public void update(Biglietto biglietto) {

    }

    @Override
    public void delete(Biglietto biglietto) {

    }

    @Override
    public Singolo create(Double prezzo, String tipo) {
        return null;
    }

    @Override
    public void update(Singolo singolo) {

    }

    @Override
    public void delete(Singolo singolo) {

    }
}
