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

    private List<Acquista> acquisti; // Variabile di istanza per mantenere le transazioni



    public AcquistaDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        acquisti = findLoggedAcquisti(); // Recupera gli acquisti esistenti per l'utente
    }

    @Override
    public Acquista create(Utente utente, Film film, Posto posto, Proiezione proiezione, String data_acq, String metodo_p, String num_carta) {


        //System.out.println("size1"+findLoggedAcquisti(utente).size());
        Acquista loggedAcquista = new Acquista();
        loggedAcquista.setUtente(utente);
        loggedAcquista.setFilm(film);
        loggedAcquista.setPosto(posto);
        loggedAcquista.setProiezione(proiezione);

        acquisti.add(loggedAcquista); // Aggiungi il nuovo acquisto agli acquisti esistenti

        saveAcquistiToCookie(acquisti); // Salva l'array aggiornato nel cookie

        System.out.println("utente" + utente.getUsername());
        System.out.println("loggedacq" + loggedAcquista.getUtente().getUsername());

        return loggedAcquista;
    }


    @Override
    public void update(Long cod_film, String num_posto, Long cod_pro, String username,
                       Long cod_film_old, String num_posto_old, Long cod_pro_old) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void updateCookie(List<Acquista> acquistiToUpdate, Utente utente) {
        // Recupera l'elenco completo degli acquisti dal cookie
        List<Acquista> acquisti = findLoggedAcquisti();

        int i = 0;
        // Esegui la logica di aggiornamento
        //for (Acquista acquistoToUpdate : acquistiToUpdate) {
            for (Acquista acquisto : acquisti) {
                Acquista acquistoToUpdate = acquistiToUpdate.get(i);
                System.out.println(acquisto.getUtente().getUsername()+"  "+acquisto.getUtente().getUsername());
                System.out.println(acquisto.getProiezione().getCod_pro()+"  "+acquistoToUpdate.getProiezione().getCod_pro());
                System.out.println(acquisto.getFilm().getCod_film()+"  "+acquistoToUpdate.getFilm().getCod_film());
                System.out.println(acquisto.getPosto().getNum_posto()+"  "+acquistoToUpdate.getPosto().getNum_posto());

                if (acquisto.getUtente().getUsername().equals(acquistoToUpdate.getUtente().getUsername()) ) {

                    // Esegui l'aggiornamento desiderato sugli acquisti
                    acquisto.setProiezione(acquistoToUpdate.getProiezione());
                    acquisto.setFilm(acquistoToUpdate.getFilm());
                    acquisto.setPosto(acquistoToUpdate.getPosto());
                    i++;
                }

            }
        //}

        // Salva l'array di acquisti aggiornato nei cookie
        saveAcquistiToCookie(acquisti);
    }


    @Override
    public void delete(List<Acquista> acquistiDaEliminare) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Visualizzazione acquisti salvati nel cookie
    @Override
    public List<Acquista> findLoggedAcquisti() {
        List<Acquista> loggedAcquisti = new ArrayList<>();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loggedAcquista")) {
                    List<Acquista> loggedAcquistiFromCookie = decode(cookie.getValue());

                    // Filtra gli acquisti per l'utente corrente
                    for (Acquista acquisto : loggedAcquistiFromCookie) {

                            loggedAcquisti.add(acquisto);

                    }
                }
            }
        }

        return loggedAcquisti;
    }

    @Override
    public List<Acquista> findAcqByUsername(Utente utente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void saveAcquistiToCookie(List<Acquista> acquisti) {

        int maxAgeInSeconds = 365 * 24 * 60 * 60; // un anno in secondi

        Cookie cookie = new Cookie("loggedAcquista", encode(acquisti));
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds); // Imposta la durata del cookie
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
