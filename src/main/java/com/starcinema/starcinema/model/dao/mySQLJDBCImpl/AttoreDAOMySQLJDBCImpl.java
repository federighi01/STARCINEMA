package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.AttoreDAO;
import com.starcinema.starcinema.model.mo.Attore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttoreDAOMySQLJDBCImpl implements AttoreDAO {

    Connection conn;

    public AttoreDAOMySQLJDBCImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Attore create(String id_attore, String cognome, String nome) {
        PreparedStatement ps;
        Attore attore= new Attore();
        attore.setId_attore(id_attore);
        attore.setCognome(cognome);
        attore.setNome(nome);

        try{
            String sql
                    = " INSERT INTO attore "
                    + "   ( id_attore,"
                    + "     cognome,"
                    + "     nome,"
                    + "   ) "
                    + " VALUES (?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, attore.getId_attore());
            ps.setString(i++, attore.getCognome());
            ps.setString(i++, attore.getNome());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return attore;
    }

    @Override
    public void update(Attore attore) {
        PreparedStatement ps;

        try{
            String sql
                    = " UPDATE attore "
                    + " SET "
                    + "   cognome = ?, "
                    + "   nome = ?, "
                    + " WHERE "
                    + "   id_attore = ? ";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, attore.getId_attore());
            ps.setString(i++, attore.getCognome());
            ps.setString(i++, attore.getNome());
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Attore attore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Attore read(ResultSet rs) {

        Attore attore = new Attore();
        try {
            attore.setId_attore(rs.getString("id_attore"));
        } catch (SQLException sqle) {
        }
        try {
            attore.setCognome(rs.getString("cognome"));
        } catch (SQLException sqle) {
        }
        try {
            attore.setNome(rs.getString("nome"));
        } catch (SQLException sqle) {
        }

        return attore;
    }
}
