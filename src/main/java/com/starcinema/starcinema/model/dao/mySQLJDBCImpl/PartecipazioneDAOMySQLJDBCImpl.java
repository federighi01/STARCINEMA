package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.PartecipazioneDAO;
import com.starcinema.starcinema.model.mo.Attore;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Partecipazione;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartecipazioneDAOMySQLJDBCImpl implements PartecipazioneDAO {
    Connection conn;

    public PartecipazioneDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Partecipazione create(Film film, Attore attore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Partecipazione partecipazione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Partecipazione partecipazione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Partecipazione read(ResultSet rs) {

        Partecipazione partecipazione = new Partecipazione();
        Film film = new Film();
        Attore attore = new Attore();
        partecipazione.setFilm(film);
        partecipazione.setAttore(attore);

        try {
            partecipazione.getFilm().setCod_film(rs.getLong("cod_film"));
        } catch (SQLException sqle) {
        }
        try {
            partecipazione.getAttore().setId_attore(rs.getString("id_attore"));
        } catch (SQLException sqle) {
        }

        return partecipazione;
    }

}
