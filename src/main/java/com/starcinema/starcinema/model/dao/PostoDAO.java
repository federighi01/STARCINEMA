package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Posto;
import com.starcinema.starcinema.model.mo.Sala;

public interface PostoDAO {

    public Posto create(
            String num_posto
    );

    public void update(Posto posto);
    public void delete(Posto posto);
}
