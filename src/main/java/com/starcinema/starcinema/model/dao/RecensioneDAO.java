package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Recensione;
import com.starcinema.starcinema.model.mo.Utente;

import java.util.Date;
import java.util.List;

public interface RecensioneDAO {

    public Recensione create(
            Long cod_rec,
            Utente utente,
            Film film,
            Integer voto,
            String commento
    );

    public void update(Recensione recensione);

    public void delete(Recensione recensione);

}
