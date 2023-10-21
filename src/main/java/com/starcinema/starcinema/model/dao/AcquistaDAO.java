package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.*;

import java.util.Date;
import java.util.List;

public interface AcquistaDAO {

    public Acquista create(
            Utente utente,
            Biglietto biglietto,
            Film film,
            Posto posto,
            Date data_acq,
            String metodo_p
    );

    public void update(Acquista acquista);
    public void delete(Acquista acquista);

}
