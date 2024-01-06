package Command;

import Recources.CommandType;

/**
 * Questa interfaccia ha il compito di fornire le funzioni standard
 * di un qualsiasi tipo di richiesta effettuata da un utente verso
 * la piattaforma
 */
public interface RequestCommand {

    /**
     * Ritorna il tipo del comando specifico della
     * richiesta
     *
     * @return il tipo di comando
     */
    CommandType getType();

    /**
     * Restituisce l'header della richiesta di comando
     * @return l'header della richiesta di comando
     */
    String showHeaderCommand();

    /**
     * Restituisce il corpo della richiesta di comando
     * @return il corpo della richiesta di comando
     */
    String showBodyCommand();

    /**
     * Esegue lo specifico comando della richiesta, con implementazione
     * fornita dalla classe da cui inverrà invocato
     */
    boolean execute();


}
