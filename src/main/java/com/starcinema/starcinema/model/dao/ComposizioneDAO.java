package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Composizione;
import com.starcinema.starcinema.model.mo.Posto;
import com.starcinema.starcinema.model.mo.Sala;

public interface ComposizioneDAO {

    public Composizione create(
            Sala sala,
            Posto posto
    );

    public void update(Composizione composizione);
    public void delete(Composizione composizione);
}
