package com.starcinema.starcinema.model.mo;

public class Abbonamento extends Biglietto{

    private Double prezzo;
    private Integer num_ingressi;

    public Double getPrezzo() { return prezzo; }
    public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }

    public Integer getNum_ingressi() { return num_ingressi; }
    public void setNum_ingressi(Integer num_ingressi) { this.num_ingressi = num_ingressi; }
}
