package Model;

/**
 *  Questa interfaccia rappresenta le operazioni standard che deve
 *  eseguire la mappa di un determinato comune
 */

public interface MapInterface {

    /**
     * Verifica se un POI passatogli è già presente nella mappa
     *
     * @param newPoi il punto da verificare
     * @return true se il punto è già nella mappa, false altrimenti
     */
    boolean isInMap(POI newPoi);

}
