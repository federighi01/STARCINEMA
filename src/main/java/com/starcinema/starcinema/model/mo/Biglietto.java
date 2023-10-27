package com.starcinema.starcinema.model.mo;

public class Biglietto {

    private Long cod_b;
    // N:1 (film)
    private Film film;
    private Double prezzo;
    private String tipo;

    // 1:N (utilizzo_abb)
    private Utilizzo_abb[] utilizzi_abb;

    public Long getCod_b() { return cod_b; }
    public void setCod_b(Long cod_b) { this.cod_b = cod_b; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Double getPrezzo() { return prezzo; }
    public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    //Array
    public Utilizzo_abb getUtilizzi_abb(int index) { return this.utilizzi_abb[index]; }
    public void setUtilizzi_abb(int index, Utilizzo_abb utilizzi_abb) { this.utilizzi_abb[index] = utilizzi_abb; }

}
