package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.ComposizioneDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComposizioneDAOMySQLJDBCImpl implements ComposizioneDAO {
    Connection conn;

    public ComposizioneDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Composizione create(Sala sala, Posto posto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Composizione composizione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Composizione composizione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Composizione read(ResultSet rs) {

        Composizione composizione = new Composizione();
        Sala sala = new Sala();
        Posto posto = new Posto();
        composizione.setSala(sala);
        composizione.setPosto(posto);

        try {
            composizione.getSala().setNum_sala(rs.getInt("num_sala"));
        } catch (SQLException sqle) {
        }
        try {
            composizione.getPosto().setNum_posto(rs.getString("num_posto"));
        } catch (SQLException sqle) {
        }

        return composizione;
    }
}
