package Model;

import Database.DBManager;
import Recources.StatusElement;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Questa classe ha la responsabilità di rappresentare gli itinerari
 * e gestire tutte le operazioni standard che li riguardano.
 */
public class Itinerari implements ElementiMappa{

    private final String id;
    private String nome;
    private String descrizione;
    private ArrayList<POI> percorso;
    private StatusElement status;

    public Itinerari(ElementiMappa newItin) {
        if(!(newItin instanceof Itinerari))
            throw new IllegalArgumentException("Elemento non valido");
        this.id = newItin.getId();
        this.nome = newItin.getNome();
        this.descrizione = newItin.getDescrizione();
        this.percorso = ((Itinerari)newItin).getPercorso();
        this.status = newItin.getStatus();
    }

    public Itinerari(String id,String nome, String descrizione, ArrayList<POI> percorso, StatusElement status) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.percorso = percorso;
        this.status = status;
    }

    public Itinerari(String nome, String descrizione, ArrayList<POI> percorso) {
        this.id = DBManager.getInstance().incrementAndSelectLastId("itinerari");
        this.nome = nome;
        this.descrizione = descrizione;
        this.percorso = percorso;
        this.status = StatusElement.PENDING;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public StatusElement getStatus() {
        return this.status;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public ArrayList<POI> getPercorso() {
        return percorso;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPercorso(ArrayList<POI> percorso) {
        this.percorso = percorso;
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
        Itinerari itin = (Itinerari) o;
        if (itin.getPercorso().size() != this.percorso.size()) return false;
        for (int i = 0; i < this.percorso.size(); i++) {
            if(!(itin.getPercorso().get(i).equals(this.percorso.get(i))))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.percorso);
    }

    @Override
    public String showGeneralInformationComponent() {
        return "";
    }

    @Override
    public String showSpecificInformationComponent() {
        return "";
    }

}
