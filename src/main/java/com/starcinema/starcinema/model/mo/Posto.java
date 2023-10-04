package com.starcinema.starcinema.model.mo;

public class Posto {

    private String num_posto;

    // N:1 (Composizione)
    private Composizione composizione;

    public String getNum_posto() { return num_posto; }
    public void setNum_posto(String num_posto) { this.num_posto = num_posto; }

    public Composizione getComposizione() { return composizione; }
    public void setComposizione(Composizione composizione) { this.composizione = composizione; }
}
