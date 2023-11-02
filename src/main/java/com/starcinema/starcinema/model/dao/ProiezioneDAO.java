package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Proiezione;
import com.starcinema.starcinema.model.mo.Sala;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface ProiezioneDAO {

    public Proiezione create(
            Film film,
            Sala sala,
            Date data_pro,
            Time ora_pro
    );

    public void update(Proiezione proiezione);
    public void delete(Proiezione proiezione);

    public List<Proiezione> findData_proByCod_film(Film film);

    public List<Proiezione> findOraByData(Long cod_film, Date data_pro);

    public Proiezione findByDataOra(Date data_pro, Time ora_pro);

    public List<Proiezione> findOraByOnlyData(Date data_pro);

    public Proiezione findPro(Long cod_pro);


    //Metodi utilizzati per SituazioneSale
    public List<Proiezione> findProByNum_sala(Integer num_sala);

    public List<Proiezione> findOraBySalaData(Integer num_sala, Date data_pro);


    //Metodi utilizzati per ModificaAcquisti
    public List<Proiezione> findSalaByCod_film(Long cod_film);

    public List<Proiezione> findProBySalaFilm(Long cod_film, Integer num_sala);

    public List<Proiezione> findProBySalaFilmData(Long cod_film, Integer num_sala, Date data_pro);

}
