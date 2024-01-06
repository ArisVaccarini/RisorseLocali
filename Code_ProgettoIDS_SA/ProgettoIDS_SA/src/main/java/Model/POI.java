package Model;

import Database.DBManager;
import Recources.Coordinate;
import Recources.StatusElement;

import java.util.Objects;

/**
 * Questa classe ha la responsabilità di rappresentarei punti
 * di rilievo (POI - un elemento della mappa del comune) e gestire
 * tutte le operazioni standard che li riguardano.
 */

public class POI implements ElementiMappa{

    private String id;
    private String nome;
    private String descrizione;
    private Coordinate posizione;
    private StatusElement status;

    public POI(ElementiMappa newPOI) {
        if(!(newPOI instanceof POI))
            throw new IllegalArgumentException("Elemento non valido");
        this.id = newPOI.getId();
        this.nome = newPOI.getNome();
        this.descrizione = newPOI.getDescrizione();
        this.posizione = ((POI)newPOI).getPosizione();
        this.status = newPOI.getStatus();
    }

    public POI(String nome, String descrizione, Coordinate posizione) {
        this.id = DBManager.getInstance().incrementAndSelectLastId("poi");;
        this.nome = nome;
        this.descrizione = descrizione;
        this.posizione = posizione;
        this.status = StatusElement.PENDING;
    }

    public POI(String id, String nome, String descrizione, Coordinate posizione, StatusElement status) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.posizione = posizione;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    @Override
    public StatusElement getStatus() {
        return this.status;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Coordinate getPosizione() {
        return posizione;
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
    public boolean changeStatus(StatusElement newStatus) {
        if(this.status.equals(newStatus))
            return false;
        this.status = newStatus;
        return true;
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
    public String showGeneralInformationComponent() {
        return "-- " + nome.toUpperCase() + "--\n" + descrizione + "\n";
    }

    @Override
    public String showSpecificInformationComponent() {
        return "-- " + nome.toUpperCase() + "--\n" + descrizione + "\n" + this.posizione.toString() ;
    }

}
