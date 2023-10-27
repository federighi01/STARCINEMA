package com.starcinema.starcinema.model.mo;

public class Abbonamento{

    private Long cod_abb;
    private Double prezzo;
    // 1:N (acquista_abb)
    private Acquista_abb[] acquisti_abb;
    // 1:N (utilizzo_abb)
    private Utilizzo_abb[] utilizzi_abb;


    public Long getCod_abb() { return cod_abb; }
    public void setCod_abb(Long cod_abb) { this.cod_abb = cod_abb; }

    public Double getPrezzo() { return prezzo; }
    public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }


    //Array
    public Acquista_abb getAcquisti_abb(int index) { return this.acquisti_abb[index]; }
    public void setAcquisti_abb(int index, Acquista_abb acquisti_abb) { this.acquisti_abb[index] = acquisti_abb; }

    public Utilizzo_abb getUtilizzi_abb(int index) { return this.utilizzi_abb[index]; }
    public void setUtilizzi_abb(int index, Utilizzo_abb utilizzi_abb) { this.utilizzi_abb[index] = utilizzi_abb; }

}
