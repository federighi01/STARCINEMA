package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Attore {

    private String id_attore;
    private String cognome;
    private String nome;

    // 1:N (partecipazione)
    private Partecipazione[] partecipazioni;

    public String getId_attore() { return id_attore; }
    public void setId_attore(String id_attore) { this.id_attore = id_attore; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    //Array
    public Partecipazione getPartecipazioni(int index) { return this.partecipazioni[index]; }
    public void setPartecipazioni(int index, Partecipazione partecipazioni) { this.partecipazioni[index] = partecipazioni; }

}
