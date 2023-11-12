package com.starcinema.starcinema.model.dao.CookieImpl;

import com.starcinema.starcinema.model.dao.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class CookieDAOFactory extends DAOFactory {
    private Map factoryParameters;

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieDAOFactory(Map factoryParameters) {
        this.factoryParameters=factoryParameters;
    }

    @Override
    public void beginTransaction() {

        try {
            this.request=(HttpServletRequest) factoryParameters.get("request");
            this.response=(HttpServletResponse) factoryParameters.get("response");;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void commitTransaction() {}

    @Override
    public void rollbackTransaction() {}

    @Override
    public void closeTransaction() {}

    @Override
    public UtenteDAO getUtenteDAO() {
        return new UtenteDAOCookieImpl(request,response);
    }

    @Override
    public RecensioneDAO getRecensioneDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FilmDAO getFilmDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProiezioneDAO getProiezioneDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SalaDAO getSalaDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ComposizioneDAO getComposizioneDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PostoDAO getPostoDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AcquistaDAO getAcquistaDAO() {
        return new AcquistaDAOCookieImpl(request,response);
    }

    @Override
    public BigliettoDAO getBigliettoDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbbonamentoDAO getAbbonamentoDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Acquista_abbDAO getAcquista_abbDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Utilizzo_abbDAO getUtilizzo_abbDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
