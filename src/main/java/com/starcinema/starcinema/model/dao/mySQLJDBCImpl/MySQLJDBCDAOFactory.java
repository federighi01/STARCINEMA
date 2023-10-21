package com.starcinema.starcinema.model.dao.mySQLJDBCImpl;

import com.starcinema.starcinema.model.dao.*;
import com.starcinema.starcinema.services.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class MySQLJDBCDAOFactory extends DAOFactory{

    private Map factoryParameters;

    private Connection connection;

    public MySQLJDBCDAOFactory(Map factoryParameters) {
        this.factoryParameters=factoryParameters;
    }

    @Override
    public void beginTransaction() {
        try {
            Class.forName(Configuration.DATABASE_DRIVER);
            this.connection = DriverManager.getConnection(Configuration.DATABASE_URL);
            this.connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeTransaction() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UtenteDAO getUtenteDAO() {
        return new UtenteDAOMySQLJDBCImpl(connection);
    }

    @Override
    public RecensioneDAO getRecensioneDAO() {
        return new RecensioneDAOMySQLJDBCImpl(connection);
    }

    @Override
    public FilmDAO getFilmDAO() {
        return new FilmDAOMySQLJDBCImpl(connection);
    }

    @Override
    public ProiezioneDAO getProiezioneDAO() {
        return new ProiezioneDAOMySQLJDBCImpl(connection);
    }

    @Override
    public SalaDAO getSalaDAO() {
        return new SalaDAOMySQLJDBCImpl(connection);
    }

    @Override
    public ComposizioneDAO getComposizioneDAO() {
        return new ComposizioneDAOMySQLJDBCImpl(connection);
    }

    @Override
    public PostoDAO getPostoDAO() {
        return new PostoDAOMySQLJDBCImpl(connection);
    }

    @Override
    public AcquistaDAO getAcquistaDAO() {
        return new AcquistaDAOMySQLJDBCImpl(connection);
    }

    @Override
    public BigliettoDAO getBigliettoDAO() {
        return new BigliettoDAOMySQLJDBCImpl(connection);
    }

    @Override
    public AbbonamentoDAO getAbbonamentoDAO() {
        return new AbbonamentoDAOMySQLJDBCImpl(connection);
    }

    @Override
    public Acquista_abbDAO getAcquista_abbDAO() {
        return new Acquista_abbDAOMySQLJDBCImpl(connection);
    }

    @Override
    public Utilizzo_abbDAO getUtilizzo_abbDAO() {
        return new Utilizzo_abbDAOMySQLJDBCImpl(connection);
    }


}
