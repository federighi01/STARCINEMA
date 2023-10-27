package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Abbonamento;
import com.starcinema.starcinema.model.mo.Acquista;
import com.starcinema.starcinema.model.mo.Acquista_abb;
import com.starcinema.starcinema.model.mo.Utente;

import java.util.Date;

public interface Acquista_abbDAO {

    public Acquista_abb create(
            Utente utente,
            Abbonamento abbonamento,
            String data_acq_abb
    );

    public void update(Acquista_abb acquista_abb);

    public void delete(Acquista_abb acquista_abb);

    public Acquista_abb findLoggedAcquista_abb();
}
