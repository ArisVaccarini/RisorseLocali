package com.unicam.risorseLocali.Core.Model.Entity;

import com.unicam.risorseLocali.Util.Enum.Role;
import jakarta.persistence.*;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi all account di un utente
 */
@Entity
@Table(name = "account")
public class User {

    @Id
    @Column(name = "nome_utente")
    private String nomeUtente;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anagrafica")
    private DatiAnagrafici anagrafica;
    @Enumerated(EnumType.STRING)
    private Role ruolo;

    public User() {}

    public User(String nome_utente, String email, String password, DatiAnagrafici anagrafica, Role ruolo) {
        this.nomeUtente = nome_utente;
        this.email = email;
        this.password = password;
        this.anagrafica = anagrafica;
        this.ruolo = ruolo;
    }

    public String getNomeUtente() {
        return this.nomeUtente;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public DatiAnagrafici getAnagrafica() {
        return this.anagrafica;
    }
    public Role getRuolo() { return this.ruolo; }

    public void setNomeUtente(String nome_utente) { this.nomeUtente = nome_utente; }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) { this.password = password; }
    public void setAnagrafica(DatiAnagrafici anagrafica) {
        this.anagrafica = anagrafica;
    }
    public void setRuolo(Role ruolo) { this.ruolo = ruolo; }
}
