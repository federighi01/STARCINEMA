package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Attore;

import java.util.List;

public interface AttoreDAO {

    public Attore create(
            String id_attore,
            String cognome,
            String nome
    );
    public void update(Attore attore);
    public void delete(Attore attore);

}
