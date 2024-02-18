package com.unicam.risorseLocali.Core.Model.Entity;

import jakarta.persistence.*;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi ad una modifica di un itinerario
 */
@Entity
@Table(name = "modifica_itinerari")
public class RequestLinkItinerario {

    @Id
    @Column(name = "id_nuovo")
    private String idItin;

    @Transient
    private String idReg;

    @ManyToOne
    @JoinColumn(name = "id_richiesta")
    private RichiestaItinerario richiesta;

    public RequestLinkItinerario() {}

    public RequestLinkItinerario(String idItin, RichiestaItinerario richiesta) {
        this.idItin = idItin;
        this.richiesta = richiesta;
        this.idReg = richiesta.getId();
    }

    public String getIdReg() {
        return idReg;
    }

    public void setIdReg(String idReg) {
        this.idReg = idReg;
    }

    public String getIdElement() {
        return this.idItin;
    }

    public void setIdElement(String idItin) {
        this.idItin = idItin;
    }

    public RichiestaItinerario getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(RichiestaItinerario richiesta) {
        this.richiesta = richiesta;
    }

}