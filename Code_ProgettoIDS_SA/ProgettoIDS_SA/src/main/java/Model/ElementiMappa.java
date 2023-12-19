package Model;

/**
 * Questa interfaccia rappresenta ogni tipo di elemento presente nella mappa,
 * come POI e itineriri, e le loro operazioni standard
 */

public interface ElementiMappa {

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
