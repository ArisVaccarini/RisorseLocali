package com.unicam.risorseLocali.Util.DTO;

import com.unicam.risorseLocali.Core.Model.Entity.DatiAnagrafici;

/**
 * Questa classe rappresenta il DTO(Data Transfer Object) degli utenti
 */
public class UserDTO {

    private String nomeUtente;
    private String email;
    private String password;
    private DatiAnagrafici anagrafica;
    private String ruolo;

    public UserDTO(String nomeUtente, String email, String password, DatiAnagrafici anagrafica, String ruolo){
        this.nomeUtente = nomeUtente;
        this.email = email;
        this.password = password;
        this.anagrafica = anagrafica;
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return nomeUtente;
    }

    public void setUsername(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DatiAnagrafici getAnagrafica() {
        return this.anagrafica;
    }

    public void setAnagrafica(DatiAnagrafici anagrafica) {
        this.anagrafica= anagrafica;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

}