package com.unicam.risorseLocali.Core.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import jakarta.persistence.*;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi alle richieste di inserimento/modifica di un poi
 */
@Entity
@Table(name = "richieste_poi")
public class RichiestaPOI extends Richiesta{

    @OneToOne(mappedBy = "richiesta", cascade = CascadeType.ALL)
    @JsonIgnore
    private RequestLinkPoi linkPoi;

    public RichiestaPOI() {}

    public RichiestaPOI(StatusRequest stato, String id_account, String id_nuovo, String descrizione, TypeElementRequest tipo) {
        super(stato, id_account, id_nuovo, descrizione, tipo);
    }

    public RichiestaPOI(String id_nuovo, TypeElementRequest tipo) {
        super(id_nuovo, tipo);
    }

    public RequestLinkPoi getLinkPoi() {
        if(this.linkPoi != null)
            return this.linkPoi;
        return null;
    }

    public void setLinkPoi(RequestLinkPoi linkPoi) {
        this.linkPoi = linkPoi;
    }

    @Override
    public String getElementLinked() {
        return (this.linkPoi != null) ? this.linkPoi.getIdElement() : null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}