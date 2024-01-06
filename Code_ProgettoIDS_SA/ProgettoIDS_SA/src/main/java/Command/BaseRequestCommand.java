package Command;

import Database.DBManager;
import Recources.CommandType;
import Recources.StatusRequest;

/**
 * Questa classe astratta ha il compito di rappresentare
 * una base di richiesta di comando, provvedendo a tutte le funzioni
 * standard offerte da tutti i tipi di richiesta
 */
public abstract class BaseRequestCommand implements RequestCommand {
    private String idReq;
    private StatusRequest status;
    private String usernameAuthor;
    private String description;

    public BaseRequestCommand(StatusRequest status, String usernameAuthor, String description, String tipo) {
        this.idReq = DBManager.getInstance().incrementAndSelectLastId(tipo);
        this.status = status;
        this.usernameAuthor = usernameAuthor;
        this.description = description;
    }

    public String getIdReq() {
        return idReq;
    }

    public StatusRequest getStatus() {
        return status;
    }

    public String getUsernameAuthor() {
        return usernameAuthor;
    }

    public String getDescription() {
        return description;
    }

    public String showHeaderCommand() {
        return "ID Richiesta -> " + this.getIdReq() + " " +
                "| Status -> " + this.getStatus().toString();
    }

    public String showBodyCommand(){
        return "Utente -> " + this.getUsernameAuthor() + "\n" +
                "Descrizione -> " + this.getDescription();
    }


    public abstract CommandType getType();
    public abstract boolean execute();
}
