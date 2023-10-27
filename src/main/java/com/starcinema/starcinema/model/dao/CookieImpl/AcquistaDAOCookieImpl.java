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
    public Acquista create(Utente utente, Film film, Posto posto, Proiezione proiezione, String data_acq, String metodo_p) {
        Acquista loggedAcquista = new Acquista();
        loggedAcquista.setUtente(utente);
        loggedAcquista.setFilm(film);
        loggedAcquista.setPosto(posto);
        loggedAcquista.setProiezione(proiezione);

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

    @Override
    public Acquista findLoggedAcquista() {
        Cookie[] cookies = request.getCookies();
        Acquista loggedAcquista = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedAcquista == null; i++) {
                if (cookies[i].getName().equals("loggedAcquista")) {
                    loggedAcquista = decode(cookies[i].getValue());
                }
            }
        }

        return loggedAcquista;
    }

    private String encode(Acquista loggedAcquista) {

        String encodedLoggedAcquista;
        encodedLoggedAcquista = loggedAcquista.getUtente().getUsername()
                + "#" + loggedAcquista.getFilm().getCod_film() + "#" + loggedAcquista.getPosto().getNum_posto()
                + "#" + loggedAcquista.getProiezione().getCod_pro();
        return encodedLoggedAcquista;

    }

    private Acquista decode(String encodedLoggedAcquista) {

        Acquista loggedAcquista = new Acquista();

        String[] values = encodedLoggedAcquista.split("#");

        loggedAcquista.getUtente().setUsername(values[0]);
        loggedAcquista.getFilm().setCod_film(Long.parseLong(values[1]));
        loggedAcquista.getPosto().setNum_posto(values[2]);
        loggedAcquista.getProiezione().setCod_pro(Long.parseLong(values[3]));

        return loggedAcquista;

    }
}
