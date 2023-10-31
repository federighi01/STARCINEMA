package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.SalaDAO;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAOMySQLJDBCImpl implements SalaDAO {
    Connection conn;

    public SalaDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Sala create(Integer num_sala, Integer capienza) {
        PreparedStatement ps;
        Sala sala = new Sala();
        sala.setNum_sala(num_sala);
        sala.setCapienza(capienza);

        try{
            String sql
                    = " INSERT INTO sala "
                    + "   ( num_sala,"
                    + "     capienza,"
                    + "   ) "
                    + " VALUES (?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, sala.getNum_sala());
            ps.setInt(i++, sala.getCapienza());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sala;
    }

    @Override
    public void update(Sala sala) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE sala "
                    + " SET "
                    + "   capienza = ?, "
                    + " WHERE "
                    + "   num_sala = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, sala.getNum_sala());
            ps.setInt(i++, sala.getCapienza());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Sala sala) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Sala findSalaByNum_sala(Integer num_sala) {
        PreparedStatement ps;
        Sala sala = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM sala "
                    + " WHERE "
                    + "   num_sala = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, num_sala);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                sala = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sala;
    }

    @Override
    public List<Sala> findSale() {
        PreparedStatement ps;
        Sala sala;
        ArrayList<Sala> sale = new ArrayList<Sala>();

        try {

            String sql
                    = " SELECT * "
                    + "   FROM sala ";

            ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                sala = read(resultSet);
                sale.add(sala);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sale;
    }

    Sala read(ResultSet rs) {

        Sala sala = new Sala();
        try {
            sala.setNum_sala(rs.getInt("num_sala"));
        } catch (SQLException sqle) {
        }
        try {
            sala.setCapienza(rs.getInt("capienza"));
        } catch (SQLException sqle) {
        }

        return sala;
    }
}
