package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Acquista {

    // N:1 (utente)
    private Utente utente;
    // N:1 (film)
    private Film film;
    // N:1 (posto)
    private Posto posto;
    // N:1 (proiezione)
    private Proiezione proiezione;
    private String data_acq;
    private String metodo_p;
    private String num_carta;

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Posto getPosto() { return posto; }
    public void setPosto(Posto posto) { this.posto = posto; }

    public Proiezione getProiezione() { return proiezione; }
    public void setProiezione(Proiezione proiezione) { this.proiezione = proiezione; }

    public String getData_acq() { return data_acq; }
    public void setData_acq(String data_acq) { this.data_acq = data_acq; }

    public String getMetodo_p() { return metodo_p; }
    public void setMetodo_p(String metodo_p) { this.metodo_p = metodo_p; }

    public String getNum_carta() { return num_carta; }
    public void setNum_carta(String num_carta) { this.num_carta = num_carta; }
}
