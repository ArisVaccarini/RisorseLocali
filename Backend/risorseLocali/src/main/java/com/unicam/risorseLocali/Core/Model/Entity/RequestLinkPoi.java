package com.unicam.risorseLocali.Core.Model.Entity;

import jakarta.persistence.*;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi ad una modifica di un poi
 */
@Entity
@Table(name = "modifica_poi")
public class RequestLinkPoi {

    @Id
    @Column(name = "id_nuovo")
    private String idPoi;

    @Transient
    private String idReg;

    @ManyToOne
    @JoinColumn(name = "id_richiesta")
    private RichiestaPOI richiesta;

    public RequestLinkPoi() {
    }

    public RequestLinkPoi(String idPoi, RichiestaPOI richiesta) {
        this.idPoi = idPoi;
        this.idReg = richiesta.getId();
        this.richiesta = richiesta;
    }


    public String getIdReg() {
        return idReg;
    }

    public void setIdReg(String idReg) {
        this.idReg = idReg;
    }

    public String getIdElement() {
        return this.idPoi;
    }

    public void setIdElement(String idPoi) {
        this.idPoi = idPoi;
    }

    public RichiestaPOI getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(RichiestaPOI richiesta) {
        this.richiesta = richiesta;
    }

}