package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Acquista;
import com.starcinema.starcinema.model.mo.Biglietto;
import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Utente;

import java.util.Date;
import java.util.List;

public interface AcquistaDAO {

    public Acquista create(
            Utente utente,
            Biglietto biglietto,
            Film film,
            Date data_acq,
            String metodo_p
    );

    public void update(Acquista acquista);
    public void delete(Acquista acquista);

}
