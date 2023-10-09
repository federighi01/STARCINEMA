package com.starcinema.starcinema.model.mo;

import java.sql.Time;
import java.util.Date;

public class Proiezione {

    private Long cod_pro;
    // N:1 (film)
    private Film film;
    // N:1 (sala)
    private Sala sala;
    private Date data_pro;
    private Time ora_pro;
    private boolean deleted;


    public Long getCod_pro() { return cod_pro; }
    public void setCod_pro(Long cod_pro) { this.cod_pro = cod_pro; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }

    public Date getData_pro() { return data_pro; }
    public void setData_pro(Date data_pro) { this.data_pro = data_pro; }
    public Time getOra_pro() { return ora_pro; }
    public void setOra_pro(Time ora_pro) { this.ora_pro = ora_pro; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
