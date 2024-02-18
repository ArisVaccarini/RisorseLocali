package com.unicam.risorseLocali.Core.Model.Entity;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Util.Enum.StatusElement;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;


import java.util.Objects;
import java.util.UUID;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi a un poi
 */
@Entity
@Table(name = "poi")
public class POI implements ElementiMappa{

    @Id
    private String id = UUID.randomUUID().toString();
    private String nome;
    private String descrizione;
    @Embedded
    private Coordinate posizione;
    @Enumerated(EnumType.STRING)
    private StatusElement stato = StatusElement.PENDING;

    public POI() {}

    public POI(String id) {
        this.id = id;
    }

    public POI(String nome, String descrizione, Coordinate posizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.posizione = posizione;
    }

    public String getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getDescrizione() {
        return this.descrizione;
    }
    public Coordinate getPosizione() {
        return this.posizione;
    }
    public StatusElement getStato() {
        return this.stato;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setPosizione(Coordinate posizione) {
        this.posizione = posizione;
    }
    @Override
    public void changeStatus(StatusElement newStatus) {
       this.stato = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        POI poi = (POI) o;
        return this.posizione.equals(poi.getPosizione());
    }
    @Override
    public int hashCode() {
        return Objects.hash(posizione);
    }

    @Override
    public String toString() {
        return "POI{" +
                "id='" + this.id + '\'' +
                ", nome='" + this.nome + '\'' +
                ", descrizione='" + this.descrizione + '\'' +
                ", posizione=" + this.posizione.toString() +
                ", stato=" + this.stato.name() +
                '}';
    }
}
