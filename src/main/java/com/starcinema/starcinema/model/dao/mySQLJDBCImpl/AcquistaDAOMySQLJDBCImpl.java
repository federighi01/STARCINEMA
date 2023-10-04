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
    public Acquista create(Utente utente, Biglietto biglietto, Film film, Date data_acq, String metodo_p) {
        PreparedStatement ps;
        Acquista acquista = new Acquista();
        acquista.setUtente(utente);
        acquista.setFilm(film);
        acquista.setBiglietto(biglietto);
        acquista.setData_acq(data_acq);
        acquista.setMetodo_p(metodo_p);

        try{
            String sql
                    = " INSERT INTO acquista "
                    + "   ( username,"
                    + "     cod_b,"
                    + "     cod_film,"
                    + "     data_acq,"
                    + "     metodo_p,"
                    + "   ) "
                    + " VALUES (?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setString(i++, acquista.getBiglietto().getCod_b());
            ps.setLong(i++, acquista.getFilm().getCod_film());
            ps.setDate(i++, (java.sql.Date) acquista.getData_acq());
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
                    + "   metodo_p = ?, "
                    + " WHERE "
                    + "   username = ? "
                    + "   cod_b = ? "
                    + "   cod_film = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setString(i++, acquista.getBiglietto().getCod_b());
            ps.setLong(i++, acquista.getFilm().getCod_film());
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
    Acquista read(ResultSet rs) {
        Acquista acquista = new Acquista();
        Utente utente = new Utente();
        Biglietto biglietto = new Biglietto();
        Film film = new Film();
        acquista.setUtente(utente);
        acquista.setBiglietto(biglietto);
        acquista.setFilm(film);

        try {
            acquista.getUtente().setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getBiglietto().setCod_b(rs.getString("cod_b"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getFilm().setCod_film(rs.getLong("cod_film"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.setData_acq(rs.getDate("data_acq"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.setMetodo_p(rs.getString("metodo_p"));
        } catch (SQLException sqle) {
        }

        return acquista;
    }
}
