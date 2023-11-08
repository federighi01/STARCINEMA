package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Proiezione;

import java.util.Date;
import java.util.List;

public interface FilmDAO {

    public Film create(
            String titolo,
            String regista,
            String cast,
            String genere,
            Integer durata,
            String nazione,
            Integer anno,
            String descrizione,
            String trailer,
            String immagine
    );

    public void update(Film film);

    public void delete(Film film);

    public Film findByTitolo(String titolo);

    public Film findByCodfilm(Long cod_film);

    public List<Film> findFilmByData_pro(Date data_pro);

    public List<Film> findFilm();

}
