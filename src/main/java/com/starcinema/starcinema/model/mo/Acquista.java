package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Acquista {

    // N:1 (utente)
    private Utente utente;
    // N:1 (biglietto)
    private Biglietto biglietto;
    // N:1 (film)
    private Film film;
    private Date data_acq;
    private String metodo_p;

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Biglietto getBiglietto() { return biglietto; }
    public void setBiglietto(Biglietto biglietto) { this.biglietto = biglietto; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Date getData_acq() { return data_acq; }
    public void setData_acq(Date data_acq) { this.data_acq = data_acq; }

    public String getMetodo_p() { return metodo_p; }
    public void setMetodo_p(String metodo_p) { this.metodo_p = metodo_p; }
}
