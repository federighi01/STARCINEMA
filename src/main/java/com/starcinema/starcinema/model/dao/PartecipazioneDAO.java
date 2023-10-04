package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Attore;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Partecipazione;

import java.util.List;

public interface PartecipazioneDAO {

    public Partecipazione create(
            Film film,
            Attore attore
    );

    public void update(Partecipazione partecipazione);

    public void delete(Partecipazione partecipazione);

}
