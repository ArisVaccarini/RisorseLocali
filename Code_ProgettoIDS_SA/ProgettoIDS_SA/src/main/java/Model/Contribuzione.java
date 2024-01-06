package Model;

import Command.RequestCommand;

/**
 * Questa interfaccia ha il compito di fornire agli Account che la
 * implementano, il servizio di contribuzione
 */

public interface Contribuzione extends Account {

    /**
     * Crea un nuovo comando di inserimento aggiungere alla
     * mappa nuovi elementi
     *
     * @param elemento il nuovo elemento da aggiungere
     * @return il comando di inserimento
     */
    RequestCommand addNewComponent(ElementiMappa elemento);

    /**
     * Crea un nuovo comando di modifica per modificare un elemento
     * esistente con uno nuovo
     *
     * @param elemento il nuovo elemento
     * @param idElementoToModify l'id dell'elemento da modificare
     * @return il comando di modifica
     */
    RequestCommand modifyComponent(ElementiMappa elemento, String idElementoToModify);

}
