package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Utilizzo_abb {

    // N:1 (abbonamento)
    private Abbonamento abbonamento;
    // N:1 (biglietto)
    private Biglietto biglietto;
    private Date data_utilizzo;

    public Abbonamento getAbbonamento() { return abbonamento; }
    public void setAbbonamento(Abbonamento abbonamento) { this.abbonamento = abbonamento; }

    public Biglietto getBiglietto() { return biglietto; }
    public void setBiglietto(Biglietto biglietto) { this.biglietto = biglietto; }

    public Date getData_utilizzo() { return data_utilizzo; }
    public void setData_utilizzo(Date data_utilizzo) { this.data_utilizzo = data_utilizzo; }
}
