package com.unicam.risorseLocali.Core.Model.Entity;

import jakarta.persistence.*;


/**
 * Questa classe ha il compito di rappresentare i dati
 * anagrafici, riferiti a un profilo utente
 */
@Entity
@Table(name = "dati_anagrafici")
public class DatiAnagrafici {

    @Id
    private String cf;
    private String nome;
    private String cognome;
    private String telefono;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indirizzo")
    private DatiAbitativi indirizzo;

    public DatiAnagrafici() {}

    public DatiAnagrafici(String nome, String cognome, String cf, String telefono, DatiAbitativi indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return this.nome;
    }
    public String getCognome() {
        return this.cognome;
    }
    public String getCf() {
        return this.cf;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public DatiAbitativi getIndirizzo() { return this.indirizzo; }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setCf(String cf) {
        this.cf = cf;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setIndirizzo(DatiAbitativi indirizzo) { this.indirizzo = indirizzo; }
}
