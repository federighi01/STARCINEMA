package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.ComposizioneDAO;
import com.starcinema.starcinema.model.mo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposizioneDAOMySQLJDBCImpl implements ComposizioneDAO {
    Connection conn;

    public ComposizioneDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Composizione create(Proiezione proiezione, Sala sala, Posto posto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(String num_posto, Long cod_proiezione) {
        PreparedStatement ps;

        try {

            String sql
                    = " UPDATE composizione "
                    + " SET "
                    + "   occupato = 'S' "
                    + " WHERE "
                    + "   numero_posto = ? AND"
                    + "   cod_proiezione = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, num_posto);
            ps.setLong(i++, cod_proiezione);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Composizione composizione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Composizione> findComposizioniByNum_sala(Integer num_sala, Long cod_proiezione) {
        PreparedStatement ps;
        Composizione composizione;
        ArrayList<Composizione> composizioni = new ArrayList<Composizione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM composizione "
                    + " WHERE "
                    + "   numero_sala = ? "
                    + " AND  cod_proiezione = ? ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, num_sala);
            ps.setLong(2, cod_proiezione);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                composizione = read(resultSet);
                composizioni.add(composizione);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return composizioni;
    }

    @Override
    public List<Composizione> findCompByPosto(Integer num_sala, String num_posto, Long cod_proiezione) {
        PreparedStatement ps;
        Composizione composizione;
        ArrayList<Composizione> composizioni = new ArrayList<Composizione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM composizione "
                    + " WHERE "
                    + "   numero_sala = ? AND"
                    + "   numero_posto = ? AND"
                    + "   cod_proiezione = ? ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, num_sala);
            ps.setString(2, num_posto);
            ps.setLong(3, cod_proiezione);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                composizione = read(resultSet);
                composizioni.add(composizione);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return composizioni;
    }

    @Override
    public List<Composizione> findCompByCod_pro(Long cod_pro) {
        PreparedStatement ps;
        Composizione composizione;
        ArrayList<Composizione> composizioni = new ArrayList<Composizione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM composizione "
                    + " WHERE "
                    + "   cod_proiezione = ? ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, cod_pro);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                composizione = read(resultSet);
                composizioni.add(composizione);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return composizioni;
    }

    Composizione read(ResultSet rs) {

        Composizione composizione = new Composizione();
        Proiezione proiezione = new Proiezione();
        Sala sala = new Sala();
        Posto posto = new Posto();
        composizione.setProiezione(proiezione);
        composizione.setSala(sala);
        composizione.setPosto(posto);

        try {
            composizione.getProiezione().setCod_pro(rs.getLong("cod_proiezione"));
        } catch (SQLException sqle) {
        }
        try {
            composizione.getSala().setNum_sala(rs.getInt("numero_sala"));
        } catch (SQLException sqle) {
        }
        try {
            composizione.getPosto().setNum_posto(rs.getString("numero_posto"));
        } catch (SQLException sqle) {
        }
        try {
            composizione.setOccupato(rs.getString("occupato").equals("S"));
        } catch (SQLException sqle) {
        }
        return composizione;
    }
}
