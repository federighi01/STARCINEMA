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
    public Proiezione create(Long cod_pro, Film film, Sala sala, Date data_pro, Time ora_pro) {
        PreparedStatement ps;
        Proiezione proiezione = new Proiezione();
        proiezione.setCod_pro(cod_pro);
        proiezione.setFilm(film);
        proiezione.setSala(sala);
        proiezione.setData_pro(data_pro);
        proiezione.setOra_pro(ora_pro);

        try{
            String sql
                    = " INSERT INTO proiezione "
                    + "   ( cod_pro,"
                    + "     cod_film,"
                    + "     num_sala,"
                    + "     data_pro,"
                    + "     ora_pro,"
                    + "     deleted "
                    + "   ) "
                    + " VALUES (?,?,?,?,?,'N')";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, proiezione.getCod_pro());
            ps.setLong(i++, proiezione.getFilm().getCod_film());
            ps.setInt(i++, proiezione.getSala().getNum_sala());
            // Utilizza java.sql.Date per la data
            java.sql.Date dataProiezioneSQL = new java.sql.Date(proiezione.getData_pro().getTime());
            ps.setDate(i++, dataProiezioneSQL);
            //ps.setTime(i++, (Time) proiezione.getData_pro());
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
                    + " cod_pro = ?,"
                    + " cod_film = ?,"
                    + " num_sala = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            // Utilizza java.sql.Date per la data
            java.sql.Date dataProiezioneSQL = new java.sql.Date(proiezione.getData_pro().getTime());
            ps.setDate(i++, dataProiezioneSQL);
            //ps.setTime(i++, (Time) proiezione.getData_pro());
            ps.setTime(i++, proiezione.getOra_pro());
            ps.setLong(i++, proiezione.getCod_pro());
            ps.setLong(i++, proiezione.getFilm().getCod_film());
            ps.setInt(i++, proiezione.getSala().getNum_sala());
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
                    + " cod_pro = ?,"
                    + " cod_film = ?,"
                    + " num_sala = ? ";

            ps = conn.prepareStatement(sql);
            ps.setLong(1, proiezione.getCod_pro());
            ps.setLong(1, proiezione.getFilm().getCod_film());
            ps.setInt(1, proiezione.getSala().getNum_sala());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proiezione> findData_proByCod_film(Film film) {
        PreparedStatement ps;
        Proiezione proiezione;
        ArrayList<Proiezione> proiezioni = new ArrayList<Proiezione>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM proiezione "
                    + " WHERE "
                    + "    codice_film = ? "
                    + " ORDER BY data_pro, ora_pro ASC ";


            ps = conn.prepareStatement(sql);
            ps.setLong(1, film.getCod_film());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                proiezione = read(resultSet);
                proiezioni.add(proiezione);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return proiezioni;
    }

    Proiezione read(ResultSet rs) {
        Proiezione proiezione = new Proiezione();
        Film film = new Film();
        Sala sala = new Sala();
        proiezione.setFilm(film);
        proiezione.setSala(sala);
        try {
            proiezione.setCod_pro(rs.getLong("Cod_pro"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.getFilm().setCod_film(rs.getLong("Codice_film"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.getSala().setNum_sala(rs.getInt("num_sala"));
        } catch (SQLException sqle) {
        }
        try {
            proiezione.setData_pro(rs.getTimestamp("data_pro"));
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
