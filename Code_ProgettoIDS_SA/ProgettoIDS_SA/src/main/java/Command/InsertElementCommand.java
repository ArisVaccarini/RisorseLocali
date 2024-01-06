package Command;

import Database.DBManager;
import Model.ElementiMappa;
import Model.MappaComune;
import Model.POI;
import Recources.CommandType;
import Recources.StatusElement;
import Recources.StatusRequest;

/**
 * Questa classe è responsabile nella creazione ed esecuzione
 * di comandi di inserimento per gli elementi della mappa
 */
public class InsertElementCommand extends BaseRequestCommand{

    private final ElementiMappa idSpecificElement;

    public InsertElementCommand(StatusRequest status,String author ,String description, ElementiMappa specificElement, String tipo) {
        super(status,author,description, tipo);
        this.idSpecificElement = specificElement;
    }

    public ElementiMappa getIdSpecificElement() {
        return this.idSpecificElement;
    }

    @Override
    public CommandType getType() {
        return CommandType.INSERT_ELEMENT_COMMAND;
    }


    @Override
    public boolean execute() {
        DBManager.getInstance().reactOnUpdateStatus(this.idSpecificElement, StatusElement.VISIBLE.name());
        return true;
    }
}
