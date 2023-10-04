package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.FilmDAO;
import com.starcinema.starcinema.model.mo.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDAOMySQLJDBCImpl implements FilmDAO {

    Connection conn;

    public FilmDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Film create(Long cod_film, String titolo, String regista, String genere, Integer durata, String nazione, Integer anno, String descrizione) {
        PreparedStatement ps;
        Film film = new Film();
        film.setCod_film(cod_film);
        film.setTitolo(titolo);
        film.setRegista(regista);
        film.setGenere(genere);
        film.setDurata(durata);
        film.setNazione(nazione);
        film.setAnno(anno);
        film.setDescrizione(descrizione);

        try{
            String sql
                    = " INSERT INTO film "
                    + "   ( cod_film,"
                    + "     titolo,"
                    + "     regista,"
                    + "     genere,"
                    + "     durata,"
                    + "     nazione,"
                    + "     anno,"
                    + "     descrizione,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, film.getCod_film());
            ps.setString(i++, film.getTitolo());
            ps.setString(i++, film.getRegista());
            ps.setString(i++, film.getGenere());
            ps.setInt(i++, film.getDurata());
            ps.setString(i++, film.getNazione());
            ps.setInt(i++, film.getAnno());
            ps.setString(i++, film.getDescrizione());

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
            film.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }
        return film;
    }
}
