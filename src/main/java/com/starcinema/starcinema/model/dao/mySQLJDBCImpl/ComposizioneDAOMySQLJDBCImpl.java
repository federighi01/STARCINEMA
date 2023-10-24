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
    public Composizione create(Sala sala, Posto posto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Composizione composizione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Composizione composizione) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Composizione> findComposizioniByNum_sala(Integer num_sala) {
        PreparedStatement ps;
        Composizione composizione;
        ArrayList<Composizione> composizioni = new ArrayList<Composizione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM composizione "
                    + " WHERE "
                    + "   numero_sala = ? ";
                    //+ " AND  occupato = 'N' ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, num_sala);

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
    public List<Composizione> findCompByPosto(Integer num_sala, String num_posto) {
        PreparedStatement ps;
        Composizione composizione;
        ArrayList<Composizione> composizioni = new ArrayList<Composizione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM composizione "
                    + " WHERE "
                    + "   numero_sala = ? AND"
                    + "   numero_posto = ? ";
            //+ " AND  occupato = 'N' ";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, num_sala);
            ps.setString(2, num_posto);

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
        Sala sala = new Sala();
        Posto posto = new Posto();
        composizione.setSala(sala);
        composizione.setPosto(posto);

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
