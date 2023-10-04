package com.starcinema.starcinema.model.dao;

import com.starcinema.starcinema.model.mo.Utente;


import java.time.LocalDate;
import java.util.Date;

public interface UtenteDAO {

    public Utente create(
            String username,
            String pw,
            String email,
            String tipo,
            String cognome,
            String nome,
            Date data_n,
            String luogo_n,
            String indirizzo,
            Long tel
    );

    public void update(Utente utente);

    public void delete(Utente utente);

    public Utente findLoggedUtente();

    public Utente findByUsername(String username);

}
