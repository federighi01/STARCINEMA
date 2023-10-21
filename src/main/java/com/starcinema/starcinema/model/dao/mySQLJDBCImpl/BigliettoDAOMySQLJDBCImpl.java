package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.BigliettoDAO;
import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Biglietto;

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
    public Biglietto create(Long cod_b, Double prezzo, String tipo) {
        PreparedStatement ps;
        Biglietto biglietto = new Biglietto();
        biglietto.setCod_b(cod_b);
        biglietto.setPrezzo(prezzo);
        biglietto.setTipo(tipo);

        try{
            String sql
                    = " INSERT INTO biglietto "
                    + "   ( cod_b,"
                    + "     prezzo,"
                    + "     tipo,"
                    + "   ) "
                    + " VALUES (?,?,?)";

            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setLong(i++, biglietto.getCod_b());
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

    Biglietto read(ResultSet rs) {

        Biglietto biglietto = new Biglietto();
        try {
            biglietto.setCod_b(rs.getLong("cod_b"));
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
