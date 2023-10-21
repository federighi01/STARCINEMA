package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Composizione;
import com.starcinema.starcinema.model.mo.Posto;
import com.starcinema.starcinema.model.mo.Sala;

import java.util.List;

public interface ComposizioneDAO {

    public Composizione create(
            Sala sala,
            Posto posto
    );

    public void update(Composizione composizione);
    public void delete(Composizione composizione);

    public List<Composizione> findComposizioniByNum_sala(Integer num_sala);
}
