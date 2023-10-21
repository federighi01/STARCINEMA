package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Biglietto;

public interface BigliettoDAO {

    public Biglietto create(
            Long cod_b,
            Double prezzo,
            String tipo
    );

    public void update(Biglietto biglietto);
    public void delete(Biglietto biglietto);

}
