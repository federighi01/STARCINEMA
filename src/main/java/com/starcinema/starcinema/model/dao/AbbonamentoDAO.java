package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Abbonamento;

public interface AbbonamentoDAO {

    public Abbonamento create(
            Double prezzo,
            Integer num_ingressi
    );

    public void update(Abbonamento abbonamento);
    public void delete(Abbonamento abbonamento);

}
