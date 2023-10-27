package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.PostoDAO;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Posto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostoDAOMySQLJDBCImpl implements PostoDAO {
    Connection conn;

    public PostoDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Posto create(String num_posto) {
        PreparedStatement ps;
        Posto posto= new Posto();
        posto.setNum_posto(num_posto);

        try{
            String sql
                    = " INSERT INTO posto "
                    + "   ( num_posto,"
                    + "   ) "
                    + " VALUES (?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, posto.getNum_posto());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posto;
    }

    @Override
    public void update(Posto posto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Posto posto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Posto findPosto(String num_posto) {
        PreparedStatement ps;
        Posto posto = null;

        try {

            String sql
                    = " SELECT * "
                    + "   FROM posto "
                    + " WHERE "
                    + "   num_posto = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, num_posto);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                posto = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posto;
    }

    Posto read(ResultSet rs) {

        Posto posto = new Posto();
        try {
            posto.setNum_posto(rs.getString("num_posto"));
        } catch (SQLException sqle) {
        }

        return posto;
    }
}
