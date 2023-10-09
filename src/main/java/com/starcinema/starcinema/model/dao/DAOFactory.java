package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.dao.CookieImpl.CookieDAOFactory;
import com.starcinema.starcinema.model.dao.mySQLJDBCImpl.*;

import java.util.Map;

public abstract class DAOFactory {

    // List of DAO types supported by the factory
    public static final String MYSQLJDBCIMPL = "MySQLJDBCImpl";
    public static final String COOKIEIMPL= "CookieImpl";

    public abstract void beginTransaction();
    public abstract void commitTransaction();
    public abstract void rollbackTransaction();
    public abstract void closeTransaction();

    public abstract UtenteDAO getUtenteDAO();

    public abstract RecensioneDAO getRecensioneDAO();

    public abstract FilmDAO getFilmDAO();

    public abstract ProiezioneDAO getProiezioneDAO();

    public abstract SalaDAO getSalaDAO();

    public abstract ComposizioneDAO getComposizioneDAO();

    public abstract PostoDAO getPostoDAO();

    public abstract AcquistaDAO getAcquistaDAO();

    public abstract BigliettoDAO getBigliettoDAO();

    public abstract AbbonamentoDAO getAbbonamentoDAO();

    public abstract SingoloDAO getSingoloDAO();

    public static DAOFactory getDAOFactory(String whichFactory, Map factoryParameters) {

        if (whichFactory.equals(MYSQLJDBCIMPL)) {
            return new MySQLJDBCDAOFactory(factoryParameters);
        } else if (whichFactory.equals(COOKIEIMPL)) {
            return new CookieDAOFactory(factoryParameters);
        } else {
            return null;
        }
    }
}
