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
    public Acquista create(Utente utente, Biglietto biglietto, Film film, Posto posto, Date data_acq, String metodo_p) {
        PreparedStatement ps;
        Acquista acquista = new Acquista();
        acquista.setUtente(utente);
        acquista.setBiglietto(biglietto);
        acquista.setFilm(film);
        acquista.setPosto(posto);
        acquista.setData_acq(data_acq);
        acquista.setMetodo_p(metodo_p);

        try{
            String sql
                    = " INSERT INTO acquista "
                    + "   ( id_utente,"
                    + "     cod_biglietto,"
                    + "     id_film,"
                    + "     num_posto,"
                    + "     data_acq,"
                    + "     metodo_p,"
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setLong(i++, acquista.getBiglietto().getCod_b());
            ps.setLong(i++, acquista.getFilm().getCod_film());
            ps.setString(i++, acquista.getPosto().getNum_posto());
            // Utilizza java.sql.Date per la data
            java.sql.Date dataAcquistoSQL = new java.sql.Date(acquista.getData_acq().getTime());
            ps.setDate(i++, dataAcquistoSQL);
            //ps.setDate(i++, (java.sql.Date) acquista.getData_acq());
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
                    + "   id_utente = ? "
                    + "   cod_biglietto = ? "
                    + "   num_posto = ? "
                    + "   id_film = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setLong(i++, acquista.getBiglietto().getCod_b());
            ps.setLong(i++, acquista.getFilm().getCod_film());
            ps.setString(i++, acquista.getPosto().getNum_posto());
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
        Biglietto biglietto = new Biglietto();
        Film film = new Film();
        Posto posto = new Posto();
        acquista.setUtente(utente);
        acquista.setBiglietto(biglietto);
        acquista.setFilm(film);
        acquista.setPosto(posto);

        try {
            acquista.getUtente().setUsername(rs.getString("id_utente"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getBiglietto().setCod_b(rs.getLong("cod_biglietto"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getFilm().setCod_film(rs.getLong("id_film"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.getPosto().setNum_posto(rs.getString("num_posto"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.setData_acq(rs.getTimestamp("data_acq"));
        } catch (SQLException sqle) {
        }
        try {
            acquista.setMetodo_p(rs.getString("metodo_p"));
        } catch (SQLException sqle) {
        }

        return acquista;
    }
}
