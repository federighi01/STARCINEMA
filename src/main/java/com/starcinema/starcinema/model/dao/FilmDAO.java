package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Proiezione;

import java.util.Date;
import java.util.List;

public interface FilmDAO {

    public Film create(
            Long cod_film,
            String titolo,
            String regista,
            String genere,
            Integer durata,
            String nazione,
            Integer anno,
            String descrizione,
            String trailer
    );

    public void update(Film film);

    public void delete(Film film);

    public Film findByTitolo(String titolo);

    public List<Film> findFilmByData_pro(Proiezione proiezione);

    public List<Film> findFilm();

}
