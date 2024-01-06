package Model;

import java.util.ArrayList;

/**
 *  Questa interfaccia rappresenta le operazioni standard che deve
 *  eseguire la mappa di un determinato comune
 */

public interface MapInterface {

    /**
     * Verifica se l'elemento passatogli come parametro,
     * è già presente nella mappa
     *
     * @param newElement il punto da verificare
     * @return true se il punto è già nella mappa, false altrimenti
     */
    boolean isInMap(ElementiMappa newElement);

    /**
     * Restituisce una lista di elementi della mappa, con stesso
     * tipo di quello specificato
     *
     * @param elementType il tipo di elementi per cui filtrare
     * @return La lista di elementi se esistono, null altrimenti
     */
    ArrayList<? extends ElementiMappa> getTypedComponent(Class<? extends ElementiMappa> elementType);

    /**
     * Ottiene uno specifico elemento della mappa, in base all'id
     * passatogli.
     *
     * @param idElement l'id dell'elemento da cercare
     * @return l'elemento se esiste, null altrimenti
     */
    ElementiMappa obtainSpecificElement(String idElement);

}
