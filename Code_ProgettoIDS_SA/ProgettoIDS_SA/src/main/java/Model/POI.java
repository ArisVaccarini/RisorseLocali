package Model;

import Recources.Coordinate;

/**
 * Questa classe ha la responsabilità i punti di rilievo (POI - un elemento
 * della mappa del comune) e gestire tutte le operazioni che lo riguardano.
 */

public class POI implements ElementiMappa {

    private final String nome;
    private final String descrizione;
    private final Coordinate posizione;


    public POI(String nome, String descrizione,Coordinate posizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.posizione = posizione;

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

    @Override
    public String showGeneralInformationComponent() {
        return "-- " + nome.toUpperCase() + "--\n" + descrizione + "\n";
    }

    @Override
    public String showSpecificInformationComponent() {
        return "-- " + nome.toUpperCase() + "--\n" + descrizione + "\n" + this.posizione.toString() ;
    }
}
