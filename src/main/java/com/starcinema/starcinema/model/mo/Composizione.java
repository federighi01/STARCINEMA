package com.starcinema.starcinema.model.mo;

public class Composizione {

    // N:1 (sala)
    private Sala sala;
    // N:1 (posto)
    private Posto posto;

    private boolean occupato;

    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }

    public Posto getPosto() { return posto; }
    public void setPosto(Posto posto) { this.posto = posto; }

    public boolean isOccupato() { return occupato; }
    public void setOccupato(boolean occupato) { this.occupato = occupato; }
}
