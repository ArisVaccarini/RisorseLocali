package Model;

import Recources.StatusElement;

/**
 * Questa interfaccia rappresenta ogni tipo di elemento presente nella mappa,
 * come POI e itinerari, e le loro operazioni standard
 */

public interface ElementiMappa {

    String getId();
    StatusElement getStatus();
    String getNome();
    String getDescrizione();

    /**
     * Cambia lo stato dello specifico elemento della
     * mappa
     * @param newStatus il nuovo stato
     */
    boolean changeStatus(StatusElement newStatus);

    /**
     * Visualizza le informazioni essenziali di un elemento presente all'interno
     * della mappa.
     *
     * @return Le informazioni essenziali dell'elemento, escluse le aggiuntive
     */
    String showGeneralInformationComponent();

    /**
     * Visualizza le informazioni specifiche di un elemento presente all'interno
     * della mappa.
     *
     * @return Le informazioni specifiche dell'elemento, escluse le aggiuntive
     */
    String showSpecificInformationComponent();

}
