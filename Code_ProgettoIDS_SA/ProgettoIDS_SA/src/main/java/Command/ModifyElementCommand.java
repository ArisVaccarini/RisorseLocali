package Command;

import Database.DBManager;
import Model.ElementiMappa;
import Recources.CommandType;
import Recources.StatusRequest;

/**
 * Questa classe è responsabile nella creazione ed esecuzione
 * di comandi di modifica per gli elementi della mappa
 */
public class ModifyElementCommand extends  BaseRequestCommand{

    private final ElementiMappa newElement;
    private final String idElementToMofify;

    public ModifyElementCommand(StatusRequest status, String author,String description,
                                ElementiMappa idNewElement, String idElementToModify, String tipo) {
        super(status,author,description, tipo);
        this.newElement = idNewElement;
        this.idElementToMofify = idElementToModify;
    }

    @Override
    public CommandType getType() {
        return CommandType.MODIFY_ELEMENT_COMMAND;
    }

    @Override
    public boolean execute() {
        DBManager.getInstance().reactOnUpdateElement(this.newElement,this.idElementToMofify);
        DBManager.getInstance().reactOnDeleteElement(this.newElement);
        return true;
    }
}
