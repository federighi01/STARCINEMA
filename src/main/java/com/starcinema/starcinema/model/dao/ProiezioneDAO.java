package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Film;
import com.starcinema.starcinema.model.mo.Proiezione;
import com.starcinema.starcinema.model.mo.Sala;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface ProiezioneDAO {

    public Proiezione create(
            Long cod_pro,
            Film film,
            Sala sala,
            Date data_pro,
            Time ora_pro
    );

    public void update(Proiezione proiezione);
    public void delete(Proiezione proiezione);

    public Proiezione findByCod_pro(Long cod_pro);

}
