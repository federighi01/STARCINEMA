package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Biglietto;
import com.starcinema.starcinema.model.mo.Film;

public interface BigliettoDAO {

    public Biglietto create(
            Long cod_b,
            Film film,
            Double prezzo,
            String tipo
    );

    public void update(Biglietto biglietto);
    public void delete(Biglietto biglietto);

    public Biglietto findBigliettoByCod_film(Long cod_film);

    public Biglietto findBigliettoByCod_b(Long cod_b);

}
