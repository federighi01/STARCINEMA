package com.starcinema.starcinema.model.dao.CookieImpl;

import com.starcinema.starcinema.model.dao.AcquistaDAO;
import com.starcinema.starcinema.model.mo.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;

public class AcquistaDAOCookieImpl implements AcquistaDAO {

    HttpServletRequest request;
    HttpServletResponse response;

    public AcquistaDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Acquista create(Utente utente, Biglietto biglietto, Film film, Posto posto, Date data_acq, String metodo_p) {
        Acquista loggedAcquista = new Acquista();
        loggedAcquista.setUtente(utente);
        loggedAcquista.setBiglietto(biglietto);
        loggedAcquista.setFilm(film);
        loggedAcquista.setPosto(posto);
        loggedAcquista.setData_acq(data_acq);
        loggedAcquista.setMetodo_p(metodo_p);

        Cookie cookie;
        cookie = new Cookie("loggedAcquista", encode(loggedAcquista));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedAcquista;
    }

    @Override
    public void update(Acquista loggedAcquista) {
        Cookie cookie;
        cookie = new Cookie("loggedAcquista", encode(loggedAcquista));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void delete(Acquista acquista) {
        Cookie cookie;
        cookie = new Cookie("loggedAcquista", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String encode(Acquista loggedAcquista) {

        String encodedLoggedAcquista;
        encodedLoggedAcquista = loggedAcquista.getUtente().getUsername() + "#" + loggedAcquista.getBiglietto().getCod_b()
                + "#" + loggedAcquista.getFilm().getCod_film() + "#" + loggedAcquista.getPosto().getNum_posto() + "#" + loggedAcquista.getData_acq() + "#" + loggedAcquista.getMetodo_p();
        return encodedLoggedAcquista;

    }

    private Acquista decode(String encodedLoggedAcquista) {

        Acquista loggedAcquista = new Acquista();

        String[] values = encodedLoggedAcquista.split("#");

        loggedAcquista.setUtente(values[0]);
        loggedAcquista.setBiglietto(values[1]);
        loggedAcquista.getFilm(values[2]);
        loggedAcquista.setData_acq(values[3]);
        loggedAcquista.setMetodo_p(values[4]);

        return loggedAcquista;

    }
}
