package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Biglietto;

public interface BigliettoDAO {

    public Biglietto create(
            String cod_b
    );

    public void update(Biglietto biglietto);
    public void delete(Biglietto biglietto);

}
