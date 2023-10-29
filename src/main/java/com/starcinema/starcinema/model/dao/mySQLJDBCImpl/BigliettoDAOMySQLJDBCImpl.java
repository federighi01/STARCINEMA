package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.BigliettoDAO;
import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Biglietto;
import com.starcinema.starcinema.model.mo.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BigliettoDAOMySQLJDBCImpl implements BigliettoDAO {
    Connection conn;

    public BigliettoDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Biglietto create(Long cod_b, Film film, Double prezzo, String tipo) {
        PreparedStatement ps;
        Biglietto biglietto = new Biglietto();
        biglietto.setCod_b(cod_b);
        biglietto.setFilm(film);
        biglietto.setPrezzo(prezzo);
        biglietto.setTipo(tipo);

        try{
            String sql
                    = " INSERT INTO biglietto "
                    + "   ( cod_b,"
                    + "     cod_film,"
                    + "     prezzo,"
                    + "     tipo,"
                    + "   ) "
                    + " VALUES (?,?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, biglietto.getCod_b());
            ps.setLong(i++, biglietto.getFilm().getCod_film());
            ps.setDouble(i++, biglietto.getPrezzo());
            ps.setString(i++, biglietto.getTipo());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return biglietto;
    }

    @Override
    public void update(Biglietto biglietto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Biglietto biglietto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Biglietto findBigliettoByCod_film(Long cod_film) {
        PreparedStatement ps;
        Biglietto biglietto = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM biglietto "
                    + " WHERE "
                    + "   cod_film = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_film);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                biglietto = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return biglietto;
    }

    @Override
    public Biglietto findBigliettoByCod_b(Long cod_b) {
        PreparedStatement ps;
        Biglietto biglietto = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM biglietto "
                    + " WHERE "
                    + "   cod_b = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_b);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                biglietto = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return biglietto;
    }

    Biglietto read(ResultSet rs) {

        Biglietto biglietto = new Biglietto();
        Film film = new Film();
        biglietto.setFilm(film);
        try {
            biglietto.setCod_b(rs.getLong("cod_b"));
        } catch (SQLException sqle) {
        }
        try {
            biglietto.getFilm().setCod_film(rs.getLong("cod_film"));
        } catch (SQLException sqle) {
        }
        try {
            biglietto.setPrezzo(rs.getDouble("prezzo"));
        } catch (SQLException sqle) {
        }
        try {
            biglietto.setTipo(rs.getString("tipo"));
        } catch (SQLException sqle) {
        }

        return biglietto;
    }
}
