package com.starcinema.starcinema.model.mo;

import java.util.Date;

public class Film {

    private Long cod_film;
    private String titolo;
    private String regista;
    private String cast;
    private String genere;
    private Integer durata;
    private String nazione;
    private Integer anno;
    private String descrizione;
    private String trailer;
    private boolean deleted;

    // 1:N (recensione)
    private Recensione[] recensioni;
    // 1:N (acquista)
    private Acquista[] acquisti;
    // 1:N (partecipazione)

    private Proiezione[] proiezioni;

    public Long getCod_film() { return cod_film; }
    public void setCod_film(Long cod_film) { this.cod_film = cod_film; }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getRegista() { return regista; }
    public void setRegista(String regista) { this.regista = regista; }

    public String getCast() { return cast; }
    public void setCast(String cast) { this.cast = cast; }

    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }

    public Integer getDurata() { return durata; }
    public void setDurata(Integer durata) { this.durata = durata; }

    public String getNazione() { return nazione; }
    public void setNazione(String nazione) { this.nazione = nazione; }

    public Integer getAnno() { return anno; }
    public void setAnno(Integer anno) { this.anno = anno; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getTrailer() { return trailer; }
    public void setTrailer(String trailer) { this.trailer = trailer; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    //Arrays
    public Recensione getRecensioni(int index) { return this.recensioni[index]; }
    public void setRecensioni(int index, Recensione recensioni) { this.recensioni[index] = recensioni; }

    public Acquista getAcquisti(int index) { return this.acquisti[index]; }
    public void setAcquisti(int index, Acquista acquisti) { this.acquisti[index] = acquisti; }

    public Proiezione getProiezioni(int index) { return this.proiezioni[index]; }
    public void setProiezioni(int index, Proiezione proiezioni) { this.proiezioni[index] = proiezioni; }

}
