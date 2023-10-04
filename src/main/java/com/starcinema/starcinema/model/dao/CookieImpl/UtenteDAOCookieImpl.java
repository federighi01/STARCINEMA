package com.starcinema.starcinema.model.dao.CookieImpl;

import com.starcinema.starcinema.model.dao.UtenteDAO;
import com.starcinema.starcinema.model.mo.Utente;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.Date;

public class UtenteDAOCookieImpl implements UtenteDAO {

    HttpServletRequest request;
    HttpServletResponse response;

    public UtenteDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    @Override
    public Utente create(
            String username,
            String pw,
            String email,
            String tipo,
            String cognome,
            String nome,
            Date data_n,
            String luogo_n,
            String indirizzo,
            Long tel
    ) {
        Utente loggedUtente = new Utente();
        loggedUtente.setUsername(username);
        loggedUtente.setNome(nome);
        loggedUtente.setCognome(cognome);
        loggedUtente.setTipo(tipo);

        Cookie cookie;
        cookie = new Cookie("loggedUtente", encode(loggedUtente));
        cookie.setPath("/");
        response.addCookie(cookie);

        return loggedUtente;
    }

    @Override
    public void update(Utente loggedUtente) {
        Cookie cookie;
        cookie = new Cookie("loggedUtente", encode(loggedUtente));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void delete(Utente loggedUtente) {
        Cookie cookie;
        cookie = new Cookie("loggedUtente", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public Utente findLoggedUtente() {
        Cookie[] cookies = request.getCookies();
        Utente loggedUtente = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length && loggedUtente == null; i++) {
                if (cookies[i].getName().equals("loggedUtente")) {
                    loggedUtente = decode(cookies[i].getValue());
                }
            }
        }

        return loggedUtente;
    }

    @Override
    public Utente findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String encode(Utente loggedUtente) {

        String encodedLoggedUtente;
        encodedLoggedUtente = loggedUtente.getUsername() + "#" + loggedUtente.getNome() + "#" + loggedUtente.getCognome() + "#" + loggedUtente.getTipo();
        return encodedLoggedUtente;

    }

    private Utente decode(String encodedLoggedUtente) {

        Utente loggedUtente = new Utente();

        String[] values = encodedLoggedUtente.split("#");

        loggedUtente.setUsername(values[0]);
        loggedUtente.setNome(values[1]);
        loggedUtente.setCognome(values[2]);
        loggedUtente.setTipo(values[3]);

        return loggedUtente;

    }
}
