package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.*;

import java.util.Date;

public interface Utilizzo_abbDAO {

    public Utilizzo_abb create(
            Long cod_ut_abb,
            Abbonamento abbonamento,
            Biglietto biglietto,
            Acquista_abb acquista_abb,
            String data_utilizzo
    );

    public void update(Utilizzo_abb utilizzo_abb);
    public void delete(Utilizzo_abb utilizzo_abb);
}
