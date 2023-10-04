package com.starcinema.starcinema.model.mo;

public class Recensione {

    private Long cod_rec;

    // N:1 (utente)
    private Utente utente;
    // N:1 (film)
    private Film film;
    private Integer voto;
    private String commento;
    private boolean deleted;

    public Long getCod_rec() { return cod_rec; }
    public void setCod_rec(Long cod_rec) { this.cod_rec = cod_rec; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Integer getVoto() { return voto; }
    public void setVoto(Integer voto) { this.voto = voto; }

    public String getCommento() { return commento; }
    public void setCommento(String commento) { this.commento = commento; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deletedeleted) { this.deleted = deleted; }


}
