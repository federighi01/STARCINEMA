package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.RecensioneDAO;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Recensione;
import com.starcinema.starcinema.model.mo.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneDAOMySQLJDBCImpl implements RecensioneDAO {

    Connection conn;

    public RecensioneDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Recensione create(Long cod_rec, Utente utente, Film film, Integer voto, String commento) {
        PreparedStatement ps;
        Recensione recensione = new Recensione();
        recensione.setCod_rec(cod_rec);
        recensione.setUtente(utente);
        recensione.setFilm(film);
        recensione.setVoto(voto);
        recensione.setCommento(commento);

        try{
            String sql
                    = " INSERT INTO recensione "
                    + "   ( cod_rec,"
                    + "     username,"
                    + "     ISAN,"
                    + "     voto,"
                    + "     commento,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, recensione.getCod_rec());
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
                    + " SET deleted='Y' "
                    + " WHERE "
                    + " cod_rec=?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, recensione.getCod_rec());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        }
        try {
            recensione.getUtente().setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            recensione.getFilm().setCod_film(rs.getLong("cod_film"));
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
