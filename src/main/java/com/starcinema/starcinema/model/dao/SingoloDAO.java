package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.dao.BigliettoDAO;
import com.starcinema.starcinema.model.mo.Singolo;

public interface SingoloDAO extends BigliettoDAO {

    public Singolo create(
            Double prezzo,
            String tipo
    );

    public void update(Singolo singolo);
    public void delete(Singolo singolo);
}
