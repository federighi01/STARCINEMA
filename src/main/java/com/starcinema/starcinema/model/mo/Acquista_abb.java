package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Acquista_abb {

    private Long cod_acq_abb;
    // N:1 (utente)
    private Utente utente;
    // N:1 (abbonamento)
    private Abbonamento abbonamento;
    private String data_acq_abb;
    private Integer num_ingressi;
    private boolean deleted;

    // N:1 (utilizzo_abb)
    private Utilizzo_abb[] utilizzi_abb;


    public Long getCod_acq_abb() { return cod_acq_abb; }
    public void setCod_acq_abb(Long cod_acq_abb) { this.cod_acq_abb = cod_acq_abb; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Abbonamento getAbbonamento() { return abbonamento; }
    public void setAbbonamento(Abbonamento abbonamento) { this.abbonamento = abbonamento; }

    public String getData_acq_abb() { return data_acq_abb; }
    public void setData_acq_abb(String data_acq_abb) { this.data_acq_abb = data_acq_abb; }

    public Integer getNum_ingressi() { return num_ingressi; }
    public void setNum_ingressi(Integer num_ingressi) { this.num_ingressi = num_ingressi; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    //Array
    public Utilizzo_abb getUtilizzi_abb(int index) { return this.utilizzi_abb[index]; }
    public void setUtilizzi_abb(int index, Utilizzo_abb utilizzi_abb) { this.utilizzi_abb[index] = utilizzi_abb; }

}
