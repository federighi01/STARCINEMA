package com.starcinema.starcinema.model.mo;

public class Singolo extends Biglietto{

    private Double prezzo;
    private String tipo;

    public Double getPrezzo() { return prezzo; }
    public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
