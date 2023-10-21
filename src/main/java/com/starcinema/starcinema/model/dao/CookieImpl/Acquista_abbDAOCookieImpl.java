package com.starcinema.starcinema.model.dao.CookieImpl;

import com.starcinema.starcinema.model.dao.Acquista_abbDAO;
import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Acquista_abb;
import com.starcinema.starcinema.model.mo.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;

public class Acquista_abbDAOCookieImpl implements Acquista_abbDAO {

    HttpServletRequest request;
    HttpServletResponse response;

    public Acquista_abbDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Acquista_abb create(Utente utente, Abbonamento abbonamento, Date data_acq_abb) {
        return null;
    }

    @Override
    public void update(Acquista_abb acquista_abb) {

    }

    @Override
    public void delete(Acquista_abb acquista_abb) {

    }
}
