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

    public void update(Long cod_film, String num_posto, Long cod_pro, String username,
                       Long cod_film_old, String num_posto_old, Long cod_pro_old);

    public void updateCookie(List<Acquista> acquistiToUpdate, Utente utente);

    public void delete(List<Acquista> acquistiDaEliminare);

    public List<Acquista> findLoggedAcquisti(Utente utente);

    public List<Acquista> findAcqByUsername(Utente utente);

}
