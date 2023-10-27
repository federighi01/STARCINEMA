package com.starcinema.starcinema.model.mo;

public class Sala {

    private Integer num_sala;
    private Integer capienza;

    // 1:N (proiezione)
    private Proiezione[] proiezioni;
    // 1:N (composizione)
    private Composizione[] composizioni;

    public Integer getNum_sala() { return num_sala; }
    public void setNum_sala(Integer num_sala) { this.num_sala = num_sala; }

    public Integer getCapienza() { return capienza; }
    public void setCapienza(Integer capienza) { this.capienza = capienza; }

    //Arrays
    public Proiezione getProiezioni(int index) { return this.proiezioni[index]; }
    public void setProiezioni(int index, Proiezione proiezioni) { this.proiezioni[index] = proiezioni; }

    public Composizione getComposizioni(int index) { return this.composizioni[index]; }
    public void setComposizioni(int index, Composizione composizioni) { this.composizioni[index] = composizioni; }
}
