package com.starcinema.starcinema.model.dao.CookieImpl;

import com.starcinema.starcinema.model.dao.AcquistaDAO;
import com.starcinema.starcinema.model.mo.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class AcquistaDAOCookieImpl implements AcquistaDAO {

    HttpServletRequest request;
    HttpServletResponse response;

    public AcquistaDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Acquista create(Utente utente, Film film, Posto posto, Proiezione proiezione, String data_acq, String metodo_p, String num_carta) {
        Acquista loggedAcquista = new Acquista();
        loggedAcquista.setUtente(utente);
        loggedAcquista.setFilm(film);
        loggedAcquista.setPosto(posto);
        loggedAcquista.setProiezione(proiezione);

        System.out.println("utente"+utente.getUsername());

        List<Acquista> acquisti = findLoggedAcquisti();
        acquisti.add(loggedAcquista);
        System.out.println("loggedacq"+loggedAcquista.getUtente().getUsername());
        saveAcquistiToCookie(acquisti);
        return loggedAcquista;
    }

    @Override
    public void update(Long cod_film, String num_posto, Long cod_pro, String username,
                       Long cod_film_old, String num_posto_old, Long cod_pro_old) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void updateCookie(List<Acquista> acquistiToUpdate) {
        // Recupera l'elenco completo degli acquisti dal cookie
        List<Acquista> acquisti = findLoggedAcquisti();

        // Esegui la logica di aggiornamento
        for (Acquista acquistoToUpdate : acquistiToUpdate) {
            for (Acquista acquisto : acquisti) {
                if (acquisto.getUtente().getUsername() == acquistoToUpdate.getUtente().getUsername()) {
                    // Esegui l'aggiornamento desiderato sugli acquisti
                    acquisto.getProiezione().setCod_pro(acquistoToUpdate.getProiezione().getCod_pro());
                    acquisto.getFilm().setCod_film(acquistoToUpdate.getFilm().getCod_film());
                    acquisto.getPosto().setNum_posto(acquistoToUpdate.getPosto().getNum_posto());
                }
            }
        }

        // Salva l'array di acquisti aggiornato nei cookie
        saveAcquistiToCookie(acquisti);
    }

    /*@Override
    public void update(Acquista loggedAcquista) {
        Cookie cookie;
        cookie = new Cookie("loggedAcquista", encode(loggedAcquista));
        cookie.setPath("/");
        response.addCookie(cookie);
    }*/

    /*@Override
    public void delete(Acquista acquista) {
        List<Acquista> acquisti = findLoggedAcquisti();
        acquisti.remove(acquista);
        saveAcquistiToCookie(acquisti);
    }*/

    public void delete(List<Acquista> acquistiDaEliminare) {
        List<Acquista> acquisti = findLoggedAcquisti();
        acquisti.removeAll(acquistiDaEliminare);
        saveAcquistiToCookie(acquisti);
    }

    @Override
    public List<Acquista> findLoggedAcquisti() {
        List<Acquista> loggedAcquisti = new ArrayList<>();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loggedAcquista")) {
                    List<Acquista> loggedAcquistiFromCookie = decode(cookie.getValue());
                    loggedAcquisti.addAll(loggedAcquistiFromCookie);
                }
            }
        }

        return loggedAcquisti;
    }

    /*@Override
    public List<Acquista> findLoggedAcquisti(String username) {
        List<Acquista> loggedAcquisti = new ArrayList<>();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loggedAcquista")) {
                    List<Acquista> loggedAcquistiFromCookie = decode(cookie.getValue());
                    for (Acquista acquisto : loggedAcquistiFromCookie) {
                        if (acquisto.getUtente().getUsername().equals(username)) {
                            loggedAcquisti.add(acquisto);
                        }
                    }
                }
            }
        }

        return loggedAcquisti;
    }*/

    @Override
    public List<Acquista> findAcqByUsername(Utente utente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void saveAcquistiToCookie(List<Acquista> acquisti) {
        Cookie cookie = new Cookie("loggedAcquista", encode(acquisti));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private String encode(List<Acquista> acquisti) {
        StringBuilder encodedAcquisti = new StringBuilder();

        for (Acquista acquisto : acquisti) {
            encodedAcquisti.append(encodeSingleAcquisto(acquisto));
            encodedAcquisti.append("|"); // Separatore tra gli acquisti
        }

        return encodedAcquisti.toString();
    }

    private String encodeSingleAcquisto(Acquista loggedAcquista) {

        String encodedLoggedAcquista;
        encodedLoggedAcquista = loggedAcquista.getUtente().getUsername()
                + "#" + loggedAcquista.getFilm().getCod_film() + "#" + loggedAcquista.getPosto().getNum_posto()
                + "#" + loggedAcquista.getProiezione().getCod_pro();
        return encodedLoggedAcquista;

    }

    private List<Acquista> decode(String encodedAcquisti) {
        List<Acquista> acquisti = new ArrayList<>();

        String[] acquistiTokens = encodedAcquisti.split("\\|"); // Dividi gli acquisti utilizzando il separatore

        for (String acquistoToken : acquistiTokens) {
            Acquista acquisto = decodeSingleAcquisto(acquistoToken);
            acquisti.add(acquisto);
        }

        return acquisti;
    }

    private Acquista decodeSingleAcquisto(String encodedLoggedAcquista) {
        String[] values = encodedLoggedAcquista.split("#");

        Acquista loggedAcquista = new Acquista();
        Utente utente = new Utente();
        Film film = new Film();
        Posto posto = new Posto();
        Proiezione proiezione = new Proiezione();

        utente.setUsername(values[0]);
        film.setCod_film(Long.parseLong(values[1]));
        posto.setNum_posto(values[2]);
        proiezione.setCod_pro(Long.parseLong(values[3]));

        loggedAcquista.setUtente(utente);
        loggedAcquista.setFilm(film);
        loggedAcquista.setPosto(posto);
        loggedAcquista.setProiezione(proiezione);

        return loggedAcquista;
    }
}
