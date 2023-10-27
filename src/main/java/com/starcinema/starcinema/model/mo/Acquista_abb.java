package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Acquista_abb {

    // N:1 (utente)
    private Utente utente;
    // N:1 (abbonamento)
    private Abbonamento abbonamento;
    private String data_acq_abb;

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Abbonamento getAbbonamento() { return abbonamento; }
    public void setAbbonamento(Abbonamento abbonamento) { this.abbonamento = abbonamento; }

    public String getData_acq_abb() { return data_acq_abb; }
    public void setData_acq_abb(String data_acq_abb) { this.data_acq_abb = data_acq_abb; }


}
