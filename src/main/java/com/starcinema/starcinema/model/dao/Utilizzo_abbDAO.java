package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.*;

import java.util.Date;

public interface Utilizzo_abbDAO {

    public Utilizzo_abb create(
            Abbonamento abbonamento,
            Biglietto biglietto,
            String data_utilizzo
    );

    public void update(Utilizzo_abb utilizzo_abb);
    public void delete(Utilizzo_abb utilizzo_abb);
}
