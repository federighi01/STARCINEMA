package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.*;

import java.util.Date;
import java.util.List;

public interface AcquistaDAO {

    public Acquista create(
            Utente utente,
            Film film,
            Posto posto,
            Proiezione proiezione,
            String data_acq,
            String metodo_p,
            String num_carta
    );

    public void update(Utente utente, Long cod_film, String num_posto, Long cod_pro);

    public void delete(Acquista acquista);

    public Acquista findLoggedAcquista();

    public List<Acquista> findAcqByUsername(Utente utente);

}
