package com.starcinema.starcinema.model.mo;

public class Biglietto {

    private String cod_b;

    // 1:N (acquista)
    private Acquista[] acquisti;

    public String getCod_b() { return cod_b; }
    public void setCod_b(String cod_b) { this.cod_b = cod_b; }

    //Array
    public Acquista getAcquisti(int index) { return this.acquisti[index]; }
    public void setAcquisti(int index, Acquista acquisti) { this.acquisti[index] = acquisti; }
}
