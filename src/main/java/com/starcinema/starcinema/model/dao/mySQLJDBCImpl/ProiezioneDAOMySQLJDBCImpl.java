package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.ProiezioneDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProiezioneDAOMySQLJDBCImpl implements ProiezioneDAO {

    Connection conn;

    public ProiezioneDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Proiezione create(Film film, Sala sala, Date data_pro, Time ora_pro) {
        PreparedStatement ps;
        Proiezione proiezione = new Proiezione();
        proiezione.setFilm(film);
        proiezione.setSala(sala);
        proiezione.setData_pro(data_pro);
        proiezione.setOra_pro(ora_pro);

        try{
            String sql
                    = " INSERT INTO proiezione "
                    + "   ( cod_film,"
                    + "     num_sala,"
                    + "     data_pro,"
                    + "     ora_pro,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, proiezione.getFilm().getCod_film());
            ps.setInt(i++, proiezione.getSala().getNum_sala());
            ps.setTime(i++, (Time) proiezione.getData_pro());
            ps.setTime(i++, proiezione.getOra_pro());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return proiezione;
    }

    @Override
    public void update(Proiezione proiezione) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE proiezione "
                    + " SET "
                    + "   data_pro = ?, "
                    + "   ora_pro = ?, "
                    + " WHERE "
                    + " cod_film = ?,"
                    + " num_sala = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setTime(1, (Time) proiezione.getData_pro());
            ps.setTime(1, proiezione.getOra_pro());
            ps.setLong(1, proiezione.getFilm().getCod_film());
            ps.setInt(1, proiezione.getSala().getNum_sala());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Proiezione proiezione) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE proiezione "
                    + " SET deleted='Y' "
                    + " WHERE "
                    + " cod_film = ?,"
                    + " num_sala = ? ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, proiezione.getFilm().getCod_film());
            ps.setInt(1, proiezione.getSala().getNum_sala());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  /*  @Override
    public List<Film> findFilmByData_pro(Proiezione proiezione, Date data_pro) {
        PreparedStatement ps;
        Film film;
        ArrayList<Film> films = new ArrayList<Film>();

        try {

            String sql
                    = " SELECT DISTINCT film.cod_film, film.titolo, film.regista, film.genere, film.durata, film.nazione, film.anno, film.descrizione "
                    + " FROM film JOIN proiezione ON film.cod_film = proiezione.codice_film "
                    + " WHERE "
                    + "   proiezione.data_pro = ? "
                    + "   AND film.deleted = 'N' "
                    + " ORDER BY film.titolo ";

            ps = conn.prepareStatement(sql);
            ps.setTime(1, (Time) proiezione.getData_pro());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                film = read(resultSet);
                films.add(film);
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return films;
    }*/

    Proiezione read(ResultSet rs) {
        Proiezione proiezione = new Proiezione();
        Film film = new Film();
        Sala sala = new Sala();
        proiezione.setFilm(film);
        proiezione.setSala(sala);
        try {
            proiezione.getFilm().setCod_film(rs.getLong("Cod_film"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.getSala().setNum_sala(rs.getInt("num_sala"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.setData_pro(rs.getDate("data_pro"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.setOra_pro(rs.getTime("ora_pro"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }
        return proiezione;
    }
}
