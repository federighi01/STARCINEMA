package com.starcinema.starcinema.model.mo;


import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Utente {

    private String username;
    private String pw;
    private String email;
    private String tipo;
    private String cognome;
    private String nome;
    private Date data_n;
    private String luogo_n;
    private String indirizzo;
    private String tel;
    private boolean deleted;

    // 1:N (recensione)
    private Recensione[] recensioni;
    // 1:N (acquista)
    private Acquista[] acquisti;
    // 1:N (acquista_abb)
    private Acquista_abb[] acquisti_abb;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPw() { return pw; }
    public void setPw(String pw) { this.pw = pw; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Date getData_n() { return data_n; }
    public void setData_n(Date data_n) { this.data_n = data_n; }

    public String getLuogo_n() { return luogo_n; }
    public void setLuogo_n(String luogo_n) { this.luogo_n = luogo_n; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }


    //Arrays
    public Recensione getRecensioni(int index) { return this.recensioni[index]; }
    public void setRecensioni(int index, Recensione recensioni) { this.recensioni[index] = recensioni; }

    public Acquista getAcquisti(int index) { return this.acquisti[index]; }
    public void setAcquisti(int index, Acquista acquisti) { this.acquisti[index] = acquisti; }

    public Acquista_abb getAcquisti_abb(int index) { return this.acquisti_abb[index]; }
    public void setAcquisti_abb(int index, Acquista_abb acquisti_abb) { this.acquisti_abb[index] = acquisti_abb; }

}
