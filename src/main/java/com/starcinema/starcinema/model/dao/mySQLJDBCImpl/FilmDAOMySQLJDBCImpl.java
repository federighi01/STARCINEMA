package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.FilmDAO;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Proiezione;
import com.starcinema.starcinema.model.mo.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmDAOMySQLJDBCImpl implements FilmDAO {

    Connection conn;

    public FilmDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Film create(String titolo, String regista, String cast, String genere, Integer durata, String nazione, Integer anno, String descrizione, String trailer, String immagine) {
        PreparedStatement ps;
        Film film = new Film();
        film.setTitolo(titolo);
        film.setRegista(regista);
        film.setCast(cast);
        film.setGenere(genere);
        film.setDurata(durata);
        film.setNazione(nazione);
        film.setAnno(anno);
        film.setDescrizione(descrizione);
        film.setTrailer(trailer);

        try{
            String sql
                    = " INSERT INTO film "
                    + "   ( titolo,"
                    + "     regista,"
                    + "     cast,"
                    + "     genere,"
                    + "     durata,"
                    + "     nazione,"
                    + "     anno,"
                    + "     descrizione,"
                    + "     trailer,"
                    + "     immagine,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, film.getTitolo());
            ps.setString(i++, film.getRegista());
            ps.setString(i++, film.getCast());
            ps.setString(i++, film.getGenere());
            ps.setInt(i++, film.getDurata());
            ps.setString(i++, film.getNazione());
            ps.setInt(i++, film.getAnno());
            ps.setString(i++, film.getDescrizione());
            ps.setString(i++, film.getTrailer());
            ps.setString(i++, film.getImmagine());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return film;
    }

    @Override
    public void update(Film film) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Film film) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE film "
                    + " SET deleted='Y' "
                    + " WHERE "
                    + " cod_film=?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, film.getCod_film());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Film findByTitolo(String titolo) {
        PreparedStatement ps;
        Film film = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM film "
                    + " WHERE "
                    + "   titolo = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, titolo);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                film = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return film;
    }

    public Film findByCodfilm(Long cod_film) {
        PreparedStatement ps;
        Film film = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM film "
                    + " WHERE "
                    + "   cod_film = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_film);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                film = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return film;
    }

    @Override
    public List<Film> findFilmByData_pro(Date data_pro) {
        PreparedStatement ps;
        Film film;
        ArrayList<Film> filmsdp = new ArrayList<Film>();

        try {

            String sql
                    = " SELECT DISTINCT film.cod_film, film.titolo, film.regista, film.cast, film.genere, film.durata, film.nazione, film.anno, film.descrizione, film.trailer, film.immagine "
                    + " FROM film JOIN proiezione ON film.cod_film = proiezione.codice_film "
                    + " WHERE "
                    + "   data_pro = ? "
                    + "   AND film.deleted = 'N' "
                    + " ORDER BY film.titolo ";

            ps = conn.prepareStatement(sql);
            java.sql.Date dataProiezioneSQL = new java.sql.Date(data_pro.getTime());
            ps.setDate(1, dataProiezioneSQL);
            //ps.setTime(1,(Time) proiezione.getData_pro());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                film = read(resultSet);
                filmsdp.add(film);
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmsdp;
    }

    @Override
    public List<Film> findFilm() {
        PreparedStatement ps;
        Film film;
        ArrayList<Film> films = new ArrayList<Film>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM film "
                    + " WHERE "
                    + "   deleted = 'N' ";

            ps = conn.prepareStatement(sql);

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
    }

    Film read(ResultSet rs) {

        Film film = new Film();
        try {
            film.setCod_film(rs.getLong("cod_film"));
        } catch (SQLException sqle) {
        }
        try {
            film.setTitolo(rs.getString("titolo"));
        } catch (SQLException sqle) {
        }
        try {
            film.setRegista(rs.getString("regista"));
        } catch (SQLException sqle) {
        }
        try {
            film.setCast(rs.getString("cast"));
        } catch (SQLException sqle) {
        }
        try {
            film.setGenere(rs.getString("genere"));
        } catch (SQLException sqle) {
        }
        try {
            film.setDurata(rs.getInt("durata"));
        } catch (SQLException sqle) {
        }
        try {
            film.setNazione(rs.getString("nazione"));
        } catch (SQLException sqle) {
        }
        try {
            film.setAnno(rs.getInt("anno"));
        } catch (SQLException sqle) {
        }
        try {
            film.setDescrizione(rs.getString("descrizione"));
        } catch (SQLException sqle) {
        }
        try {
            film.setTrailer(rs.getString("trailer"));
        } catch (SQLException sqle) {
        }
        try {
            film.setImmagine(rs.getString("immagine"));
        } catch (SQLException sqle) {
        }
        try {
            film.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }
        return film;
    }
}
