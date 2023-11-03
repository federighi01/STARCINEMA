package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Composizione;
import com.starcinema.starcinema.model.mo.Posto;
import com.starcinema.starcinema.model.mo.Proiezione;
import com.starcinema.starcinema.model.mo.Sala;

import java.util.List;

public interface ComposizioneDAO {

    public Composizione create(
            Proiezione proiezione,
            Sala sala,
            Posto posto
    );

    public void update(String num_posto, Long cod_proiezione);

    public void delete(Composizione composizione);

    public List<Composizione> findComposizioniByNum_sala(Integer num_sala, Long cod_proiezione);

    public List<Composizione> findCompByPosto(Integer num_sala, String num_posto, Long cod_proiezione);

    //Metodo utilizzato in ModificaAcquisti
    public List<Composizione> findCompByCod_pro(Long cod_pro);
}
