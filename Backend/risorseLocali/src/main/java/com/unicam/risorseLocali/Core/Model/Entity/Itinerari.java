package com.unicam.risorseLocali.Core.Model.Entity;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Util.Enum.StatusElement;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi a un itinerario
 */
@Entity
@Table(name = "itinerari")
public class Itinerari implements ElementiMappa {
    @Id
    private String id = UUID.randomUUID().toString();
    private String nome;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private StatusElement stato = StatusElement.PENDING;
    @ManyToMany()
    @JoinTable(name = "itinerari_poi",
            joinColumns = {@JoinColumn(name = "id_itinerari")}
            , inverseJoinColumns = {@JoinColumn(name = "id_poi")})
    private Set<POI> percorso = new HashSet<>();

    public Itinerari() {}

    public Itinerari(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public StatusElement getStato() {
        return this.stato;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getDescrizione() {
        return this.descrizione;
    }

    public Set<POI> getPercorso() {
        return this.percorso;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPercorso(Set<POI> percorso) {
        this.percorso = percorso;
    }
    @Override
    public void changeStatus(StatusElement newStatus) {
        this.stato = newStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerari itin = (Itinerari) o;
        if (itin.getPercorso().size() != this.percorso.size()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.percorso);
    }


    @Override
    public String toString() {
            StringBuilder percorsoStringBuilder = new StringBuilder("[");
            for (POI punto : this.percorso) {
                percorsoStringBuilder.append(punto).append(", ");
            }
            if (this.percorso.size() > 0) {
                percorsoStringBuilder.setLength(percorsoStringBuilder.length() - 2);
            }
            percorsoStringBuilder.append("]");
            return "Itinerario{" +
                    " id='" + this.id + '\'' +
                    ", nome='" + this.nome + '\'' +
                    ", descrizione='" + this.descrizione + '\'' +
                    ", percorso=" + percorsoStringBuilder.toString() +
                    ", stato=" + this.stato +
                    '}';
    }

}
