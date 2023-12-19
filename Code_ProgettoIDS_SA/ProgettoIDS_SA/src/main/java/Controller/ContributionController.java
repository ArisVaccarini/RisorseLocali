package Controller;

import Control.GeometryPositionVerify;
import Model.MappaComune;
import Model.POI;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Questa classe ha il compito di gestire tutte le operazioni standard
 * riguardanti la contribuzione di contenuti da parte degli utenti
 * autorizzati
 */
public class ContributionController {
    private MappaComune mappaComune;
    public ContributionController() {
        this.mappaComune = new MappaComune(new ArrayList<>(),new ArrayList<>());
    }

    public MappaComune getMappaComune() {
        return mappaComune;
    }

    /**
     * Verifica se un punto appartiene o meno al comune
     *
     * @param poi il punto da verificare
     * @return true se il punto è nel comune, false altrimenti
     * @throws IOException viene lanciata se si verificano problemi di lettura
     *                      con la risposta Http
     */
    public boolean isPointInComuneVerify(POI poi) throws IOException {
        return new GeometryPositionVerify().verifyPointInComune(poi.getPosizione().getLat(),
                poi.getPosizione().getLon());
    }

    /**
     * Verifica se un nuovo punto da aggiungere,è già presente
     * nella mappa del territorio
     *
     * @param newPoi il punto da verificare
     * @return true se è già presente, false altrimenti.
     */
    public boolean alreadyInMap(POI newPoi){
        return this.mappaComune.isInMap(newPoi);
    }

    /**
     * Aggiunge un nuovo punto (passatogli come parametro)
     * alla mappa del comune
     *
     * @param newPOI Il punto da aggiungere
     */
    public void insertNewPOI(POI newPOI){
        this.mappaComune.getListaPoi().add(newPOI);
    }
}
