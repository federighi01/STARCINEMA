package com.starcinema.starcinema.model.mo;

public class Partecipazione {

    // N:1 (film)
    private Film film;
    // N:1 (attore)
    private Attore attore;

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Attore getAttore() { return attore; }
    public void setAttore(Attore attore) { this.attore = attore; }
}
