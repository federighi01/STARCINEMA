package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Sala;

import java.util.List;

public interface SalaDAO {

    public Sala create(
            Integer num_sala,
            Integer capienza
    );

    public void update(Sala sala);
    public void delete(Sala sala);

    public Sala findSalaByNum_sala(Integer num_sala);

    public List<Sala> findSale();
}
