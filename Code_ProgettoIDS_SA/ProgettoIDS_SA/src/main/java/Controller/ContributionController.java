package Controller;

import Control.GeometryPositionVerify;
import Database.DBManager;
import Model.Contribuzione;
import Model.ElementiMappa;
import Model.MappaComune;
import Model.POI;
import java.io.IOException;

/**
 * Questa classe ha il compito di gestire tutte le operazioni standard
 * riguardanti la contribuzione di contenuti da parte degli utenti
 * autorizzati
 */
public class ContributionController {
    private MappaComune mappaComune;
    private Contribuzione acccountContributore;

    public ContributionController(Contribuzione acccountContributore) {
        this.mappaComune = new MappaComune();
        this.acccountContributore = acccountContributore;
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
     * Verifica se un nuovo elemento da aggiungere,è già presente
     * nella mappa del territorio
     *
     * @param newElement il punto da verificare
     * @return true se è già presente, false altrimenti.
     */
    public boolean alreadyInMap(ElementiMappa newElement){
        return this.mappaComune.isInMap(newElement);
    }

    /**
     * Aggiunge un nuovo elemento (passatogli come parametro)
     * nella mappa del comune.
     *
     * @param newElement l'elemento da aggiungere
     * @return true se l'operazione è andata a buon fine, altrimenti false
     */
    public boolean insertElementInMap(ElementiMappa newElement){
        DBManager.getInstance().reactOnInsertElement(newElement);
        this.mappaComune.getElementiMappa().put(newElement.getId(),newElement);
        return this.acccountContributore.addNewComponent(newElement).execute();
    }

    /**
     * Modifica un elemento (passatogli come parametro)
     * della mappa del comune
     *
     * @param elemento l'elemento da modificare
     * @return true se l'operazione è andata a buon fine, altrimenti false
     */
    public boolean modifyElementInMap(ElementiMappa elemento, String idElementToModify){
        DBManager.getInstance().reactOnInsertOverrideElement(elemento,idElementToModify);
        return this.acccountContributore.modifyComponent(elemento,idElementToModify).execute();
    }

}
