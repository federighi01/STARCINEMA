package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.RecensioneDAO;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Recensione;
import com.starcinema.starcinema.model.mo.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAOMySQLJDBCImpl implements RecensioneDAO {

    Connection conn;

    public RecensioneDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Recensione create(Utente utente, Film film, Integer voto, String commento) {
        PreparedStatement ps;
        Recensione recensione = new Recensione();
        recensione.setUtente(utente);
        recensione.setFilm(film);
        recensione.setVoto(voto);
        recensione.setCommento(commento);

        try{
            String sql
                    = " INSERT INTO recensione "
                    + "    ( cod_utente,"
                    + "     idfilm,"
                    + "     voto,"
                    + "     commento,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, recensione.getUtente().getUsername());
            ps.setLong(i++, recensione.getFilm().getCod_film());
            ps.setInt(i++, recensione.getVoto());
            ps.setString(i++, recensione.getCommento());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recensione;
    }

    @Override
    public void update(Recensione recensione) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE recensione "
                    + " SET "
                    + "   voto = ?, "
                    + "   commento = ?, "
                    + " WHERE "
                    + "   cod_rec = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, recensione.getVoto());
            ps.setString(i++, recensione.getCommento());
            ps.setLong(i++, recensione.getCod_rec());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Recensione recensione) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE recensione "
                    + " SET deleted = 'Y' "
                    + " WHERE "
                    + " cod_rec = ? ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, recensione.getCod_rec());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Recensione findByCod_rec(Long cod_rec) {
        PreparedStatement ps;
        Recensione recensione = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM recensione "
                    + " WHERE "
                    + "   cod_rec = ? ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_rec);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                recensione = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recensione;
    }
    @Override
    public List<Recensione> findRecensioni(Long cod_film) {
        PreparedStatement ps;
        Recensione recensione;
        ArrayList<Recensione> recensioni = new ArrayList<Recensione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM recensione "
                    + " WHERE "
                    + "   idfilm = ? "
                    + "   AND deleted = 'N' ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_film);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                recensione = read(resultSet);
                recensioni.add(recensione);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recensioni;
    }

    Recensione read(ResultSet rs) {
        Recensione recensione = new Recensione();
        Utente utente = new Utente();
        Film film = new Film();
        recensione.setUtente(utente);
        recensione.setFilm(film);
        try {
            recensione.setCod_rec(rs.getLong("cod_rec"));
        } catch (SQLException sqle) {
            System.out.println("Errore durante la lettura di cod_recensione: " + sqle.getMessage());
        }
        try {
            recensione.getUtente().setUsername(rs.getString("cod_utente"));
        } catch (SQLException sqle) {
        }
        try {
            recensione.getFilm().setCod_film(rs.getLong("idfilm"));
        } catch (SQLException sqle) {
        }
        try {
            recensione.setVoto(rs.getInt("voto"));
        } catch (SQLException sqle) {
        }
        try {
            recensione.setCommento(rs.getString("commento"));
        } catch (SQLException sqle) {
        }
        try {
            recensione.setDeleted(rs.getString("deleted").equals("Y"));
        } catch (SQLException sqle) {
        }
        return recensione;
    }
}
