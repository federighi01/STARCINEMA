package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.AcquistaDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AcquistaDAOMySQLJDBCImpl implements AcquistaDAO {
    Connection conn;

    public AcquistaDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Acquista create(Utente utente, Film film, Posto posto, Proiezione proiezione, String data_acq, String metodo_p, String num_carta) {
        PreparedStatement ps;
        Acquista acquista = new Acquista();
        acquista.setUtente(utente);
        acquista.setFilm(film);
        acquista.setPosto(posto);
        acquista.setProiezione(proiezione);
        acquista.setData_acq(data_acq);
        acquista.setMetodo_p(metodo_p);
        acquista.setNum_carta(num_carta);

        try{
            String sql
                    = " INSERT INTO acquista "
                    + "   ( id_utente,"
                    + "     id_f,"
                    + "     num_posto,"
                    + "     codice_proiezione,"
                    + "     data_acquisto,"
                    + "     metodo_p,"
                    + "     num_carta"
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, acquista.getUtente().getUsername());
            ps.setLong(i++, acquista.getFilm().getCod_film());
            ps.setString(i++, acquista.getPosto().getNum_posto());
            ps.setLong(i++, acquista.getProiezione().getCod_pro());
            ps.setString(i++, acquista.getData_acq());
            ps.setString(i++, acquista.getMetodo_p());
            ps.setString(i++, acquista.getNum_carta());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return acquista;
    }

    @Override
    public void update(Long cod_film, String num_posto, Long cod_pro, String username,
                       Long cod_film_old, String num_posto_old, Long cod_pro_old) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE acquista "
                    + " SET "
                    + "  id_f = ? , "
                    + "  num_posto = ? , "
                    + "  codice_proiezione = ? "
                    + " WHERE "
                    + "  id_utente = ? AND "
                    + "  id_f = ? AND "
                    + "  num_posto = ? AND "
                    + "  codice_proiezione = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, cod_film);
            ps.setString(i++, num_posto);
            ps.setLong(i++, cod_pro);
            ps.setString(i++, username);
            ps.setLong(i++, cod_film_old);
            ps.setString(i++, num_posto_old);
            ps.setLong(i++, cod_pro_old);

            System.out.println("cf "+cod_film+" cfo "+cod_film_old);
            System.out.println("cp "+cod_pro+" cpo "+cod_pro_old);
            System.out.println("np "+num_posto+" npo "+num_posto_old);
            System.out.println(username);

            ps.executeUpdate();



        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCookie(List<Acquista> acquistiToUpdate, Utente utente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(List<Acquista> acquistiDaEliminare) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Acquista> findLoggedAcquisti() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Acquista> findAcqByUsername(Utente utente) {
        PreparedStatement ps;
        Acquista acquista;
        ArrayList<Acquista> acquisti = new ArrayList<Acquista>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM acquista "
                    + " WHERE "
                    + "   id_utente = ? "
                    + " ORDER BY data_acquisto DESC";

            ps = conn.prepareStatement(sql);
            ps.setString(1, utente.getUsername());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                acquista = read(resultSet);
                acquisti.add(acquista);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return acquisti;
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
        try{
            acquista.setNum_carta(rs.getString("num_carta"));
        } catch (SQLException sqle) {
        }

        return acquista;
    }
}
