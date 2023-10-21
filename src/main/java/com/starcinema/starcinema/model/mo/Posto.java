package com.starcinema.starcinema.model.mo;

public class Posto {

    private String num_posto;

    // 1:N (composizioni)
    private Composizione[] composizioni;

    // 1:N (acquista)
    private Acquista[] acquisti;

    public String getNum_posto() { return num_posto; }
    public void setNum_posto(String num_posto) { this.num_posto = num_posto; }

    public Composizione getComposizioni(int index) { return this.composizioni[index]; }
    public void setComposizioni(int index, Composizione composizioni) { this.composizioni[index] = composizioni; }

    public Acquista getAcquisti(int index) { return this.acquisti[index]; }
    public void setAcquisti(int index, Acquista acquisti) { this.acquisti[index] = acquisti; }
}
