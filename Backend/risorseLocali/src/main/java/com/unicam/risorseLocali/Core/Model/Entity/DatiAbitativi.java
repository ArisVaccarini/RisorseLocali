package com.unicam.risorseLocali.Core.Model.Entity;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * Questa classe ha il compito di rappresentare i dati
 * abitivi, riferiti a un profilo utente
 */

@Entity
@Table(name = "dati_abitativi")
public class DatiAbitativi {

    @Id
    private String id = UUID.randomUUID().toString();
    private String via;
    @Column(name = "numero_civico")
    private String numeroCivico;
    private int cap;

    public DatiAbitativi() {}

    public DatiAbitativi(String via, String numeroCivico, int cap) {
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.cap = cap;
    }

    public String getId() {
        return this.id;
    }
    public String getVia() {
        return this.via;
    }
    public String getNumeroCivico() {
        return this.numeroCivico;
    }
    public int getCap() {
        return this.cap;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setVia(String via) {
        this.via = via;
    }
    public void setNumeroCivico(String numero_civico) {
        this.numeroCivico = numero_civico;
    }
    public void setCap(int cap) {
        this.cap = cap;
    }

}
