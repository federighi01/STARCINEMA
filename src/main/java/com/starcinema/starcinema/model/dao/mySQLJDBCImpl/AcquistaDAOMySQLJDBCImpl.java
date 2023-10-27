package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.AcquistaDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AcquistaDAOMySQLJDBCImpl implements AcquistaDAO {
    Connection conn;

    public AcquistaDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Acquista create(Utente utente, Film film, Posto posto, Proiezione proiezione, String data_acq, String metodo_p) {
        PreparedStatement ps;
        Acquista acquista = new Acquista();
        acquista.setUtente(utente);
        acquista.setFilm(film);
        acquista.setPosto(posto);
        acquista.setProiezione(proiezione);
        acquista.setData_acq(data_acq);
        acquista.setMetodo_p(metodo_p);

        try{
            String sql
                    = " INSERT INTO acquista "
                    + "   ( id_utente,"
                    + "     id_f,"
                    + "     num_posto,"
                    + "     codice_proiezione,"
                    + "     data_acquisto,"
                    + "     metodo_p"
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setLong(i++, acquista.getFilm().getCod_film());
            ps.setString(i++, acquista.getPosto().getNum_posto());
            ps.setLong(i++, acquista.getProiezione().getCod_pro());
            ps.setString(i++, acquista.getData_acq());
            ps.setString(i++, acquista.getMetodo_p());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return acquista;
    }

    @Override
    public void update(Acquista acquista) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE acquista "
                    + " SET "
                    + "   metodo_p = ? "
                    + " WHERE "
                    + "   id_utente = ? AND"
                    + "   num_posto = ? AND"
                    + "   id_f = ? AND"
                    + "   codice_proiezione = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setLong(i++, acquista.getFilm().getCod_film());
            ps.setString(i++, acquista.getPosto().getNum_posto());
            ps.setLong(i++, acquista.getProiezione().getCod_pro());
            ps.setString(i++, acquista.getMetodo_p());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Acquista acquista) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Acquista findLoggedAcquista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Acquista read(ResultSet rs) {
        Acquista acquista = new Acquista();
        Utente utente = new Utente();
        Film film = new Film();
        Posto posto = new Posto();
        Proiezione proiezione = new Proiezione();
        acquista.setUtente(utente);
        acquista.setFilm(film);
        acquista.setPosto(posto);
        acquista.setProiezione(proiezione);

        try {
            acquista.getUtente().setUsername(rs.getString("id_utente"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getFilm().setCod_film(rs.getLong("id_f"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getPosto().setNum_posto(rs.getString("num_posto"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getProiezione().setCod_pro(rs.getLong("codice_proiezione"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.setData_acq(rs.getString("data_acquisto"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.setMetodo_p(rs.getString("metodo_p"));
        } catch (SQLException sqle) {
        }

        return acquista;
    }
}
