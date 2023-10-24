package com.starcinema.starcinema.model.dao.CookieImpl;

import com.starcinema.starcinema.model.dao.Acquista_abbDAO;
import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Acquista;
import com.starcinema.starcinema.model.mo.Acquista_abb;
import com.starcinema.starcinema.model.mo.Utente;
import jakarta.servlet.http.Cookie;
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
        Acquista_abb loggedAcquista_abb = new Acquista_abb();
        loggedAcquista_abb.setUtente(utente);
        loggedAcquista_abb.setAbbonamento(abbonamento);

        Cookie cookie;
        cookie = new Cookie("loggedAcquista", encode(loggedAcquista_abb));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedAcquista_abb;
    }

    @Override
    public void update(Acquista_abb loggedAcquista_abb) {
        Cookie cookie;
        cookie = new Cookie("loggedAcquista_abb", encode(loggedAcquista_abb));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void delete(Acquista_abb acquista_abb) {
        Cookie cookie;
        cookie = new Cookie("loggedAcquista_abb", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public Acquista_abb findLoggedAcquista_abb() {
        Cookie[] cookies = request.getCookies();
        Acquista_abb loggedAcquista_abb = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedAcquista_abb == null; i++) {
                if (cookies[i].getName().equals("loggedAcquista_abb")) {
                    loggedAcquista_abb = decode(cookies[i].getValue());
                }
            }
        }

        return loggedAcquista_abb;
    }

    private String encode(Acquista_abb loggedAcquista_abb) {

        String encodedLoggedAcquista_abb;
        encodedLoggedAcquista_abb = loggedAcquista_abb.getUtente().getUsername() + "#" + loggedAcquista_abb.getAbbonamento().getCod_abb();
        return encodedLoggedAcquista_abb;

    }

    private Acquista_abb decode(String encodedLoggedAcquista_abb) {

        Acquista_abb loggedAcquista_abb = new Acquista_abb();

        String[] values = encodedLoggedAcquista_abb.split("#");

        loggedAcquista_abb.getUtente().setUsername(values[0]);
        loggedAcquista_abb.getAbbonamento().setCod_abb(Long.parseLong(values[1]));

        return loggedAcquista_abb;

    }

}
