package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Abbonamento;

public interface AbbonamentoDAO {

    public Abbonamento create(
            Long cod_abb,
            Double prezzo
    );

    public void update(Abbonamento abbonamento);
    public void delete(Abbonamento abbonamento);

    public Abbonamento findAbb();

    public Abbonamento findAbbByCod(Long cod_abb);

}
