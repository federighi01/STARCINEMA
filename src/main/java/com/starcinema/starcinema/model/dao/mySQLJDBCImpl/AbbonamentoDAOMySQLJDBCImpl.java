package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.AbbonamentoDAO;
import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Biglietto;

import java.sql.Connection;

public class AbbonamentoDAOMySQLJDBCImpl implements AbbonamentoDAO {
    Connection conn;

    public AbbonamentoDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Abbonamento create(Double prezzo, Integer num_ingressi) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Abbonamento abbonamento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Abbonamento abbonamento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
