package com.unicam.risorseLocali.Core.Model.Entity;

import com.fasterxml.jackson.annotation.*;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import jakarta.persistence.*;

/**
 * Questa classe ha il compito di rappresentare i dati
 * relativi alle richieste di inserimento/modifica di un itinerario
 */
@Entity
@Table(name = "richieste_itinerari")
public class RichiestaItinerario extends Richiesta<RichiestaItinerario>{

    @OneToOne(mappedBy = "richiesta", cascade = CascadeType.ALL)
    @JsonIgnore
    private RequestLinkItinerario linkItinerario;

    public RichiestaItinerario() {}

    public RichiestaItinerario(StatusRequest stato, String id_account, String id_nuovo, String descrizione, TypeElementRequest tipo) {
        super(stato, id_account, id_nuovo, descrizione, tipo);
    }

    public RichiestaItinerario(String id_nuovo, TypeElementRequest tipo) {
        super(id_nuovo, tipo);
    }

    public RequestLinkItinerario getLinkItinerario() {
        if(this.linkItinerario != null)
            return this.linkItinerario;
        return null;
    }

    public void setLinkItinerario(RequestLinkItinerario linkItinerario) {
        this.linkItinerario = linkItinerario;
    }

    @Override
    public String getElementLinked() {
        return (this.linkItinerario != null) ? this.linkItinerario.getIdElement() : null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}