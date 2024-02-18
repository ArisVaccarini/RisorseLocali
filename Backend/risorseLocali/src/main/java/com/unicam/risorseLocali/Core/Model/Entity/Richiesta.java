package com.unicam.risorseLocali.Core.Model.Entity;

import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import com.unicam.risorseLocali.Util.Enum.TypeElementRequest;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Questa classe astratta ha il compito di rappresentare i dati
 * relativi alle richieste di inserimento/modifica sia dei poi
 * che degli itinerari
 */
@MappedSuperclass
public abstract class Richiesta<T> {

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    private StatusRequest stato;

    @Column(name = "id_account")
    private String idAccount;

    @Column(name = "id_vecchio")
    private String idOldElement;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private TypeElementRequest tipo;

    public Richiesta() {}

    public Richiesta(String idOldElement, TypeElementRequest tipo) {
        this.idOldElement = idOldElement;
        this.tipo = tipo;
    }

    public Richiesta(StatusRequest stato, String id_account, String idOldElement, String descrizione, TypeElementRequest tipo) {
        this.stato = stato;
        this.idAccount = id_account;
        this.idOldElement = idOldElement;
        this.descrizione = descrizione;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusRequest getStato() {
        return stato;
    }

    public void setStato(StatusRequest stato) {
        this.stato = stato;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getIdOldElement() {
        return idOldElement;
    }

    public void setIdOldElement(String idOldElement) {
        this.idOldElement = idOldElement;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TypeElementRequest getTipo() {
        return tipo;
    }

    public void setTipo(TypeElementRequest tipo) {
        this.tipo = tipo;
    }

    public abstract String getElementLinked();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Richiesta request = (Richiesta) o;
        return Objects.equals(id, request.id) && stato == request.stato &&
                Objects.equals(idAccount, request.idAccount) &&
                Objects.equals(descrizione, request.descrizione)
                && Objects.equals(tipo, request.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stato, idAccount, descrizione, tipo);
    }

    @Override
    public String toString() {
        return "Richiesta{" +
                "id='" + id + '\n' +
                ", stato=" + stato +
                ", id_account='" + idAccount + '\n' +
                ", id_nuovo='" + idOldElement + '\n' +
                ", descrizione='" + descrizione + '\n' +
                ", tipo='" + tipo + '\n' +
                '}' + '\n' ;
    }
}
