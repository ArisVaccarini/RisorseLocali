package Database;

import Command.BaseRequestCommand;
import Model.ElementiMappa;

import java.sql.Connection;
import java.util.HashMap;

public interface DBMediator {
    Connection getConnection();

    /**
     * Si occupa di selezionare tutti i POI presenti all'interno
     * della mappa del comune di riferimento.
     *
     * @return un hashmap contenente tutti i punti.
     */
    HashMap<String, ElementiMappa> selectAllPOI();

    /**
     * Si occupa di selezionare tutti gli itinerari presenti all'interno
     * della mappa del comune di riferimento.
     *
     * @return un hashmap contenente tutti gli itinerari.
     */
    HashMap<String, ElementiMappa> selectAllItinerari();

    /**
     * Inserisce l'elemento passatogli nei parametri all'interno dei poi
     * o degli itinerari in base al proprio tipo.
     *
     * @param elementMappa      L'elemento da inserire.
     */
    void reactOnInsertElement(ElementiMappa elementMappa);

    /**
     * Aggiorna l'elemento.
     *
     * @param elementoNuovo         L'elemento nuovo.
     * @param idElementoToModify    L'elemento vecchio da aggiornare con il nuovo.
     */
    void reactOnUpdateElement(ElementiMappa elementoNuovo, String idElementoToModify);

    /**
     * Elimina un poi o un itinerario.
     *
     * @param elementoNuovo         L'elemento da eliminare.
     */
    void reactOnDeleteElement(ElementiMappa elementoNuovo);

    /**
     * Inserisce la richiesta in base al suo tipo(inserimento o modifica) e in base al tipo
     * di punto al quale si riferisce la richiesta(poi o itinerario).
     *
     * @param richiesta             La richiesta.
     * @param idNewElem             L'id del nuovo elemento.
     * @param idElemToModify        L'id del vecchio elemento.
     */
    void reactOnInsertNewRequest(BaseRequestCommand richiesta, ElementiMappa idNewElem, String idElemToModify);

    /**
     * Inserisce l'elemento modificato in stato di "Pending" associato
     * all'elemento che dovrà essere sovrascritto da quest'ultimo.
     *
     * @param elementiMappa Il nuovo elemento modificato.
     * @param idElemenToModify L'id del vecchio elemento(quello da modificare).
     */
    void reactOnInsertOverrideElement(ElementiMappa elementiMappa, String idElemenToModify);

    /**
     * Aggiorna lo stato di un elemento.
     *
     * @param idElement         L'id dell'elemento del quale aggiornare lo stato.
     * @param status            Il nuovo stato dell'elemento.
     */
    void reactOnUpdateStatus(ElementiMappa idElement, String status);

}
