package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Utilizzo_abb {

    private Long cod_ut_abb;
    // N:1 (abbonamento)
    private Abbonamento abbonamento;
    // N:1 (biglietto)
    private Biglietto biglietto;
    // N:1 (acquista_abb)
    private Acquista_abb acquista_abb;
    private String data_utilizzo;
    private boolean deleted;


    public Long getCod_ut_abb() { return cod_ut_abb; }
    public void setCod_ut_abb(Long cod_ut_abb) { this.cod_ut_abb = cod_ut_abb; }

    public Abbonamento getAbbonamento() { return abbonamento; }
    public void setAbbonamento(Abbonamento abbonamento) { this.abbonamento = abbonamento; }

    public Biglietto getBiglietto() { return biglietto; }
    public void setBiglietto(Biglietto biglietto) { this.biglietto = biglietto; }

    public Acquista_abb getAcquista_abb() { return acquista_abb; }
    public void setAcquista_abb(Acquista_abb acquista_abb) { this.acquista_abb = acquista_abb; }

    public String getData_utilizzo() { return data_utilizzo; }
    public void setData_utilizzo(String data_utilizzo) { this.data_utilizzo = data_utilizzo; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }


}
